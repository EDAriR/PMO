package com.syntrontech.pmo.mv.measurement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.syntrontech.measurement.model.Biochemistry;

public interface BiochemistryRepository extends CrudRepository<Biochemistry, Long>{
	
	@Query("SELECT MAX(b.groupId) from biochemistry b")
	Optional<Long> findMaxByGroupId();
}
