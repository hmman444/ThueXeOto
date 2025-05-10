package com.hcmute.thuexe.repository;

import com.hcmute.thuexe.dto.response.BookingHistoryResponse;
import com.hcmute.thuexe.model.Booking;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT COUNT(b) FROM Booking b WHERE b.car.carId = :carId AND b.status = 'completed'")
    Long countCompletedTripsByCarId(Long carId);

    @Query("SELECT COUNT(b) FROM Booking b WHERE b.car.owner.userId = :ownerId AND b.status = 'completed'")
    Long countCompletedTripsByOwnerId(@Param("ownerId") Long ownerId);

    List<Booking> findByUser_UserId(Long userId);
}
