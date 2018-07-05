package com.syntrontech.pmo.mv.measurement.repository;

import org.springframework.data.repository.CrudRepository;

import com.syntrontech.measurement.model.BodyTemperature;

public interface BodyTemperatureRepository extends CrudRepository<BodyTemperature, Long>{
}
