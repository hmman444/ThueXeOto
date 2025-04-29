package com.hcmute.thuexe.repository;

import com.hcmute.thuexe.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.car.carId = :carId")
    Double findAverageRatingByCarId(Long carId);
}
