package com.hcmute.thuexe.service;

import com.hcmute.thuexe.dto.request.SearchCarRequest;
import com.hcmute.thuexe.model.Car;
import com.hcmute.thuexe.repository.BookingRepository;
import com.hcmute.thuexe.repository.CarRepository;
import com.hcmute.thuexe.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Car> searchCars(SearchCarRequest searchCarRequest) {
        return carRepository.searchCars(
                searchCarRequest.getLocation(),
                searchCarRequest.getSeats(),
                searchCarRequest.getBrand(),
                searchCarRequest.getPriceFrom(),
                searchCarRequest.getPriceTo(),
                searchCarRequest.getGearType(),
                searchCarRequest.getFuelType()
        );
    }

    public Double getAvgRating(Long carId) {
        return reviewRepository.findAverageRatingByCarId(carId);
    }

    public Long getTripCount(Long carId) {
        return bookingRepository.countCompletedTripsByCarId(carId);
    }
}
