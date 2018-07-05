package com.syntrontech.pmo.mv.measurement.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.syntrontech.measurement.model.AbnormalBloodPressure;

public interface AbnormalBloodPressureHeartBeatRepository extends CrudRepository<AbnormalBloodPressure, Long>{
	Optional<AbnormalBloodPressure> findBySequence(Long Seq);
}
