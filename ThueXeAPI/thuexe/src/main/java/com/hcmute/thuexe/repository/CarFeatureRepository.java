package com.hcmute.thuexe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcmute.thuexe.model.CarFeature;

@Repository
public interface CarFeatureRepository extends JpaRepository<CarFeature, Integer> {

}
