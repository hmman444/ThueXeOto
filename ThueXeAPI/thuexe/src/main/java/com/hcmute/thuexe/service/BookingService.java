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

        long days = ChronoUnit.DAYS.between(request.getStartDate(), request.getEndDate());
        if (days <= 0) {
            throw new RuntimeException("Phạm vi ngày không hợp lệ");
        }

        double pricePerDay = car.getPrice();
        double insuranceFee = request.isInsuranceSelected() ? 90_000 : 0;
        double deliveryFee = request.isDeliverySelected() ? 30_000 : 0;
        double driverRequired = request.getDriverRequired() ? (pricePerDay*20)/100 : 0;
        double total = (pricePerDay + insuranceFee) * days + deliveryFee + driverRequired;
        double deposit = total * 0.4;
        double payLater = total - deposit;

        return new BookingPreviewResponse(
            pricePerDay, insuranceFee, deliveryFee, (int) days,
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

        long days = ChronoUnit.DAYS.between(request.getStartDate(), request.getEndDate());
        if (days <= 0) throw new RuntimeException("Ngày thuê không hợp lệ");

        double pricePerDay = car.getPrice();
        double insuranceFee = request.isInsuranceSelected() ? 90_000 : 0;
        double deliveryFee = request.isDeliverySelected() ? 30_000 : 0;
        double driverRequired = request.getDriverRequired() ? (pricePerDay*20)/100 : 0;
        double total = (pricePerDay + insuranceFee) * days + deliveryFee + driverRequired;

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setCar(car);
        booking.setStartDate(request.getStartDate());
        booking.setEndDate(request.getEndDate());
        booking.setPickupLocation(request.getPickupLocation());
        booking.setDropoffLocation(request.getDropoffLocation());
        booking.setInsuranceSelected(request.isInsuranceSelected());
        booking.setDeliverySelected(request.isDeliverySelected());
        booking.setTotalPrice(total);
        booking.setDriverRequired(request.getDriverRequired());
        booking.setStatus("Pending");
        booking.setCreatedAt(LocalDateTime.now());
        booking.setUpdatedAt(LocalDateTime.now());

        bookingRepository.save(booking);
        return booking.getBookingId();
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

        if (!booking.getUser().getUserId().equals(userId)) {
            throw new RuntimeException("Không có quyền xem thông tin booking này");
        }

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

    public List<BookingHistoryResponse> getBookingHistoryByUserId(Long userId) {
        List<Booking> bookings = bookingRepository.findByUser_UserId(userId);

        return bookings.stream().map(booking -> new BookingHistoryResponse(
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
                booking.getDriverRequired(),
                booking.isDeliverySelected(),
                booking.getCancelReason()
        )).collect(Collectors.toList());
    }

}
