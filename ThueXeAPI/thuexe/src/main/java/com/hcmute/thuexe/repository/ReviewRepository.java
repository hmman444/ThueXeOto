package com.hcmute.thuexe.repository;

import com.hcmute.thuexe.dto.response.ReviewDTO;
import com.hcmute.thuexe.model.Review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.car.carId = :carId")
    Double findAverageRatingByCarId(Long carId);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.car.owner.userId = :ownerId")
    Double findAverageRatingByOwnerId(@Param("ownerId") Long ownerId);

    @Query("SELECT new com.hcmute.thuexe.dto.response.ReviewDTO(r.reviewId, r.car.carId, r.rating, r.comment, r.user.userId, r.createdAt) " +
           "FROM Review r WHERE r.car.carId = :carId")
    List<ReviewDTO> findByCar_CarId_dto(Long carId);
    List<Review> findByCar_CarId(Long carId);
    List<Review> findByUser_UserId(Long userId);
}
