package com.hcmute.thuexe.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcmute.thuexe.dto.request.SearchCarRequest;
import com.hcmute.thuexe.dto.responese.CarListResponse;
import com.hcmute.thuexe.model.Car;
import com.hcmute.thuexe.payload.ApiResponse;
import com.hcmute.thuexe.service.CarService;
import com.hcmute.thuexe.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CarService carService;

    @GetMapping("/profile")
    public String getProfile(Authentication authentication) {
        String username = authentication.getName(); // Lấy username từ token đã giải mã
        return userService.getProfileByUsername(username);
    }
    
    @PostMapping("/cars/list")
    public ApiResponse<List<CarListResponse>> searchCars(@RequestBody SearchCarRequest request, Authentication authentication) {
        String username = authentication.getName();
        if (username == null || username.isEmpty()) {
            return new ApiResponse<>(false, "Vui lòng đăng nhập trước khi tìm kiếm xe");
        }

        List<Car> cars = carService.searchCars(request);
        if (cars.isEmpty()) {
            return new ApiResponse<>(false, "Không tìm thấy xe nào");
        }

        List<CarListResponse> response = cars.stream()
            .map(car -> {
                Double avgRating = carService.getAvgRating(car.getCarId());
                Long tripCount = carService.getTripCount(car.getCarId());
                return new CarListResponse(car, avgRating != null ? avgRating : 0.0, tripCount != null ? tripCount : 0L);
            })
            .toList();

        return new ApiResponse<>(true, "Tìm kiếm thành công", response);
    }
    

}

