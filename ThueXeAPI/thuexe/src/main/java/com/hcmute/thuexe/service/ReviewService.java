package com.hcmute.thuexe.service;

import com.hcmute.thuexe.dto.request.ReviewRequest;
import com.hcmute.thuexe.exception.ResourceNotFoundException;
import com.hcmute.thuexe.model.Car;
import com.hcmute.thuexe.model.Review;
import com.hcmute.thuexe.model.User;
import com.hcmute.thuexe.repository.CarRepository;
import com.hcmute.thuexe.repository.ReviewRepository;
import com.hcmute.thuexe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public List<Review> getReviewsByCarId(Long carId) {
        return reviewRepository.findByCar_CarId(carId);
    }

    @Transactional
    public void addReview(Authentication authentication, ReviewRequest request) {
        String username = authentication.getName();

        User user = userRepository.findByAccount_Username(username)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy người dùng"));

        Car car = carRepository.findById(request.getCarId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy xe"));

        Review review = new Review();
        review.setUser(user);
        review.setCar(car);
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        review.setCreatedAt(LocalDateTime.now().format(DATE_FORMATTER));

        reviewRepository.save(review);
    }

    @Transactional
    public void updateReview(Authentication authentication, Long reviewId, ReviewRequest request) {
        String username = authentication.getName();
        
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found"));

        if (!review.getUser().getAccount().getUsername().equals(username)) {
            throw new RuntimeException("You are not allowed to update this review");
        }
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        review.setCreatedAt(LocalDateTime.now().format(DATE_FORMATTER));

        reviewRepository.save(review);
    }

    @Transactional
    public void deleteReview(Authentication authentication, Long reviewId) {
        String username = authentication.getName();

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found"));

        if (!review.getUser().getAccount().getUsername().equals(username)){
            throw new RuntimeException("You are not allowed to delete this review");
        }

        reviewRepository.delete(review);
    }
}
