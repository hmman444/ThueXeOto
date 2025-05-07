package com.hcmute.thuexe.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long promotionId;

    private String code;
    private double discountAmount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String status;

    public boolean isValid(LocalDateTime checkDate) {
        return (startDate == null || !checkDate.isBefore(startDate)) &&
               (endDate == null || !checkDate.isAfter(endDate)) &&
               "active".equalsIgnoreCase(status);
    }

    
}