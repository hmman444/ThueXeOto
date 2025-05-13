package com.hcmute.thuexe.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.hcmute.thuexe.dto.request.AddCarRequest;
import com.hcmute.thuexe.dto.request.SearchCarRequest;
import com.hcmute.thuexe.dto.response.CarDetailResponse;
import com.hcmute.thuexe.dto.response.CarResponse;
import com.hcmute.thuexe.dto.response.ReviewDTO;
import com.hcmute.thuexe.exception.ResourceNotFoundException;
import com.hcmute.thuexe.model.Car;
import com.hcmute.thuexe.model.User;
import com.hcmute.thuexe.repository.BookingRepository;
import com.hcmute.thuexe.repository.CarRepository;
import com.hcmute.thuexe.repository.ReviewRepository;
import com.hcmute.thuexe.repository.UserRepository;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

	@Autowired
    private BookingRepository bookingRepository;

	@Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    public void addCar(AddCarRequest request, Authentication authentication) {
        String username = authentication.getName();
        User owner = userRepository.findByAccount_Username(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Car car = new Car(
                owner,
                request.getName(),
                request.getBrand(),
                request.getDescription(),
                request.getGearType(),
                request.getSeats(),
                request.getFuelType(),
                request.getEnergyConsumption(),
                request.getHasEtc(),
                request.getLocation(),
                request.getPrice(),
                request.getImageUrl(),
                "Available",
                request.isDriverRequired(),
                LocalDateTime.now().toString()
        );

        carRepository.save(car);
    }

    public List<CarResponse> getAllCarsByOwner(Authentication authentication) {
        String username = authentication.getName();
        User owner = userRepository.findByAccount_Username(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<Car> cars = carRepository.findByOwner(owner);

        return cars.stream().map(car -> new CarResponse(
                car.getCarId(),
                car.getName(),
                car.getBrand(),
                car.getDescription(),
                car.getGearType(),
                car.getSeats(),
                car.getFuelType(),
                car.getEnergyConsumption(),
                car.getHasEtc(),
                car.getLocation(),
                car.getPrice(),
                car.getImageUrl(),
                car.getStatus(),
                car.isDriverRequired(),
                car.getCreatedAt()
        )).collect(Collectors.toList());
    }
	
	public List<Car> searchCars(SearchCarRequest searchCarRequest) {
        String location = (searchCarRequest.getLocation() != null && !searchCarRequest.getLocation().isEmpty())
                ? searchCarRequest.getLocation()
                : null;

        Integer seats = searchCarRequest.getSeats();

        String brand = (searchCarRequest.getBrand() != null && !searchCarRequest.getBrand().isEmpty())
                ? searchCarRequest.getBrand()
                : null;

        Double priceFrom = searchCarRequest.getPriceFrom();
        Double priceTo = searchCarRequest.getPriceTo();

        String gearType = (searchCarRequest.getGearType() != null && !searchCarRequest.getGearType().isEmpty())
                ? searchCarRequest.getGearType()
                : null;

        String fuelType = (searchCarRequest.getFuelType() != null && !searchCarRequest.getFuelType().isEmpty())
                ? searchCarRequest.getFuelType()
                : null;

        Boolean driverRequired = searchCarRequest.getDriverRequired();

        return carRepository.searchCars(
                location,
                seats,
                brand,
                priceFrom,
                priceTo,
                gearType,
                fuelType,
                driverRequired,
                "Available"
        );
    }

	public Double getAvgRating(Long carId) {
        return reviewRepository.findAverageRatingByCarId(carId);
    }

    public Long getTripCount(Long carId) {
        return bookingRepository.countCompletedTripsByCarId(carId);
    }

    public CarDetailResponse getCarDetail(Long carId) {
        Car car = carRepository.findById(carId)
            .orElseThrow(() -> new RuntimeException("Car not found"));

        // Đánh giá & chuyến của xe
        Double avgRating = reviewRepository.findAverageRatingByCarId(carId);
        Long tripCount = bookingRepository.countCompletedTripsByCarId(carId);

        // Thống kê của chủ xe
        Long ownerId = car.getOwner().getUserId();
        Double ownerAvgRating = reviewRepository.findAverageRatingByOwnerId(ownerId);
        Long ownerTripCount = bookingRepository.countCompletedTripsByOwnerId(ownerId);

        // Danh sách đánh giá
        List<ReviewDTO> reviews = reviewRepository.findByCar_CarId_dto(carId)
            .stream()
            .map(r -> new ReviewDTO(
                r.getReviewId(),
                carId,
                r.getRating(),
                r.getComment(),
                r.getName(),
                r.getCreatedAt(),
                r.getImageUrl()
            ))
            .collect(Collectors.toList());
    

        // Trả về DTO
        CarDetailResponse response = new CarDetailResponse();
        response.setId(car.getCarId());
        response.setName(car.getName());
        response.setBrand(car.getBrand());
        response.setDescription(car.getDescription());
        response.setGearType(car.getGearType());
        response.setSeats(car.getSeats());
        response.setFuelType(car.getFuelType());
        response.setEnergyConsumption(car.getEnergyConsumption());
        response.setHasEtc(car.getHasEtc());
        response.setLocation(car.getLocation());
        response.setPrice(car.getPrice());
        response.setImageUrl(car.getImageUrl());
        response.setDriverRequired(car.isDriverRequired());
        response.setStatus(car.getStatus());
        response.setDriverRequired(car.isDriverRequired());
        response.setCreatedAt(car.getCreatedAt());
        response.setAvgRating(avgRating != null ? avgRating : 0.0);
        response.setTripCount(tripCount != null ? tripCount : 0L);
        response.setOwnerName(car.getOwner().getName());
        response.setOwnerImage(car.getOwner().getImageUrl());
        response.setOwnerAvgRating(ownerAvgRating != null ? ownerAvgRating : 0.0);
        response.setOwnerTripCount(ownerTripCount != null ? ownerTripCount : 0L);
        response.setReviews(reviews);

        return response;
    }
}
