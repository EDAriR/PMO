package com.syntrontech.pmo.mv.measurement.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.syntrontech.measurement.model.BiochemistryMappings;
import com.syntrontech.measurement.model.common.BiochemistryMappingsProject;

public interface BiochemistryMappingsRepository extends CrudRepository<BiochemistryMappings, Long>{
	Optional<BiochemistryMappings> findByProject(BiochemistryMappingsProject project);
}
