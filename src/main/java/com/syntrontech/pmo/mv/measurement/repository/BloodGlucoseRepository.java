package com.syntrontech.pmo.mv.measurement.repository;

import org.springframework.data.repository.CrudRepository;

import com.syntrontech.measurement.model.BloodGlucose;

public interface BloodGlucoseRepository extends CrudRepository<BloodGlucose, Long> {
}
