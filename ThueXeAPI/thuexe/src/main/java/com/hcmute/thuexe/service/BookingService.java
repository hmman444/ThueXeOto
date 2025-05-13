package com.hcmute.thuexe.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hcmute.thuexe.dto.request.BookingRequest;
import com.hcmute.thuexe.dto.request.BookingUpdateRequest;
import com.hcmute.thuexe.dto.request.CancelBookingRequest;
import com.hcmute.thuexe.dto.response.BookingDetailResponse;
import com.hcmute.thuexe.dto.response.BookingHistoryResponse;
import com.hcmute.thuexe.dto.response.BookingPreviewResponse;
import com.hcmute.thuexe.model.Booking;
import com.hcmute.thuexe.model.Car;
import com.hcmute.thuexe.model.User;
import com.hcmute.thuexe.repository.BookingRepository;
import com.hcmute.thuexe.repository.CarRepository;
import com.hcmute.thuexe.repository.UserRepository;
import java.time.Duration;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookingService {

    private final CarRepository carRepository;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;

    public BookingPreviewResponse previewBooking(BookingRequest request) {
        Car car = carRepository.findById(request.getCarId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy xe"));

        LocalDateTime start = request.getStartDate();
        LocalDateTime end = request.getEndDate();

        if (!end.isAfter(start)) {
            throw new RuntimeException("Ngày thuê không hợp lệ");
        }

        // Tính số phút giữa 2 thời điểm
        long minutes = Duration.between(start, end).toMinutes();
        // Tính số ngày dưới dạng số thực (VD: 3.5 ngày)
        double days = minutes / 1440.0;

        double pricePerDay = car.getPrice();
        double insuranceFee = request.isInsuranceSelected() ? 90_000 : 0;
        double deliveryFee = request.isDeliverySelected() ? 30_000 : 0;
        double driverRequired = request.getDriverRequired() ? (pricePerDay*20)/100 : 0;
        double total = (pricePerDay + insuranceFee) * days + deliveryFee + driverRequired;
        double deposit = total * 0.4;
        double payLater = total - deposit;

        return new BookingPreviewResponse(
            pricePerDay, insuranceFee, deliveryFee, days,
            total, 0, total,
            deposit, payLater, driverRequired,
            "Bạn cần thanh toán giữ chỗ và cung cấp giấy tờ hợp lệ khi nhận xe"
        );
    }

    @Transactional
    public Long confirmBooking(BookingRequest request, Long userId) {
        Car car = carRepository.findById(request.getCarId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy xe"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

        LocalDateTime start = request.getStartDate();
        LocalDateTime end = request.getEndDate();

        if (!end.isAfter(start)) {
            throw new RuntimeException("Ngày thuê không hợp lệ");
        }

        // Tính số phút giữa 2 thời điểm
        long minutes = Duration.between(start, end).toMinutes();
        // Tính số ngày dưới dạng số thực (VD: 3.5 ngày)
        double days = minutes / 1440.0;

        double pricePerDay = car.getPrice();
        double insuranceFee = request.isInsuranceSelected() ? 90_000 : 0;
        double deliveryFee = request.isDeliverySelected() ? 30_000 : 0;
        double driverFee = request.getDriverRequired() ? (pricePerDay * 0.2) : 0;

        // Tổng chi phí
        double total = (pricePerDay + insuranceFee) * days + deliveryFee + driverFee;

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setCar(car);
        booking.setStartDate(start);
        booking.setEndDate(end);
        booking.setPickupLocation(request.getPickupLocation());
        booking.setDropoffLocation(request.getDropoffLocation());
        booking.setInsuranceSelected(request.isInsuranceSelected());
        booking.setDeliverySelected(request.isDeliverySelected());
        booking.setDriverRequired(request.getDriverRequired());
        booking.setTotalPrice(total);
        booking.setStatus("Pending");
        booking.setCreatedAt(LocalDateTime.now());
        booking.setUpdatedAt(LocalDateTime.now());

        bookingRepository.save(booking);
        return booking.getBookingId();
    }

    /**
     * Lấy Booking theo ownerId (Chủ xe)
     */
    public List<BookingHistoryResponse> getMyBookingsByOwnerId(Long ownerId) {
        List<Booking> bookings = bookingRepository.findByCarOwnerId(ownerId);

        return bookings.stream()
                .map(booking -> new BookingHistoryResponse(
                        booking.getBookingId(),
                        booking.getCar().getName(),
                        booking.getCar().getImageUrl(),
                        booking.getStartDate(),
                        booking.getEndDate(),
                        booking.getTotalPrice(),
                        booking.getPickupLocation(),
                        booking.getDropoffLocation(),
                        booking.getStatus(),
                        booking.isInsuranceSelected(),
                        booking.isDeliverySelected(),
                        booking.getDriverRequired(),
                        booking.getCancelReason()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public void cancelBooking(CancelBookingRequest request, Long userId) {
        Booking booking = bookingRepository.findById(request.getBookingId())
            .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn"));

        if (!booking.getUser().getUserId().equals(userId)) {
            throw new RuntimeException("Bạn không có quyền hủy đơn này");
        }

        LocalDateTime now = LocalDateTime.now();
        long hoursUntilStart = ChronoUnit.HOURS.between(now, booking.getStartDate());

        if (hoursUntilStart < 24) {
            booking.setStatus("cancelled");
        } else {
            booking.setStatus("cancelled");
        }

        booking.setUpdatedAt(LocalDateTime.now());
        booking.setCancelReason(request.getReason());
        bookingRepository.save(booking);
    }

    public BookingDetailResponse getBookingDetail(Long bookingId, Long userId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy booking"));

        BookingDetailResponse response = new BookingDetailResponse();
        response.setBookingId(booking.getBookingId());
        response.setStartDate(booking.getStartDate());
        response.setEndDate(booking.getEndDate());
        response.setTotalPrice(booking.getTotalPrice());
        response.setPickupLocation(booking.getPickupLocation());
        response.setDropoffLocation(booking.getDropoffLocation());
        response.setStatus(booking.getStatus());
        response.setCreatedAt(booking.getCreatedAt());
        response.setUpdatedAt(booking.getUpdatedAt());
        response.setCancelReason(booking.getCancelReason());
        response.setInsuranceSelected(booking.isInsuranceSelected());
        response.setDeliverySelected(booking.isDeliverySelected());

        if (booking.getCar() != null) {
            response.setCarName(booking.getCar().getName());
            response.setCarImageUrl(booking.getCar().getImageUrl());
            response.setCarPrice(booking.getCar().getPrice());

            // Owner Information
            User owner = booking.getCar().getOwner();
            if (owner != null) {
                response.setOwnerId(owner.getUserId());
                response.setOwnerName(owner.getName());
                response.setOwnerPhone(owner.getPhone());
                response.setOwnerImageUrl(owner.getImageUrl());
            }
        }

        // Renter Information
        User renter = booking.getUser();
        if (renter != null) {
            response.setRenterId(renter.getUserId());
            response.setRenterName(renter.getName());
            response.setRenterPhone(renter.getPhone());
            response.setRenterImageUrl(renter.getImageUrl());
        }

        return response;
    }


    public String updateBooking(BookingUpdateRequest request, Long userId) {
        Booking booking = bookingRepository.findById(request.getBookingId())
                .orElseThrow(() -> new RuntimeException("Booking không tồn tại"));

        if (!booking.getUser().getUserId().equals(userId)) {
            throw new RuntimeException("Bạn không có quyền cập nhật booking này");
        }

        if (request.getStartDate() != null) booking.setStartDate(request.getStartDate());
        if (request.getEndDate() != null) booking.setEndDate(request.getEndDate());
        if (request.getPickupLocation() != null) booking.setPickupLocation(request.getPickupLocation());
        if (request.getDropoffLocation() != null) booking.setDropoffLocation(request.getDropoffLocation());
        if (request.getInsuranceSelected() != null) booking.setInsuranceSelected(request.getInsuranceSelected());
        if (request.getDeliverySelected() != null) booking.setDeliverySelected(request.getDeliverySelected());

        booking.setUpdatedAt(LocalDateTime.now());
        bookingRepository.save(booking);

        return "Booking đã được cập nhật thành công";
    }

    /**
     * Lấy Booking theo userId (Người thuê xe)
     */
    public List<BookingHistoryResponse> getBookingHistoryByUserId(Long userId) {
        List<Booking> bookings = bookingRepository.findByUser_UserId(userId);

        return bookings.stream()
                .map(booking -> new BookingHistoryResponse(
                        booking.getBookingId(),
                        booking.getCar().getName(),
                        booking.getCar().getImageUrl(),
                        booking.getStartDate(),
                        booking.getEndDate(),
                        booking.getTotalPrice(),
                        booking.getPickupLocation(),
                        booking.getDropoffLocation(),
                        booking.getStatus(),
                        booking.isInsuranceSelected(),
                        booking.isDeliverySelected(),
                        booking.getDriverRequired(),
                        booking.getCancelReason()
                ))
                .collect(Collectors.toList());
    }

}
