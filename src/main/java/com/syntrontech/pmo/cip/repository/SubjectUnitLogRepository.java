package com.syntrontech.pmo.cip.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.syntrontech.cip.model.SubjectUnitLog;

public interface SubjectUnitLogRepository extends CrudRepository<SubjectUnitLog, Long>{
	
	long countByUnitId(String unitId);
	long countByUnitIdAndCreateTimeBetween(String unitId, Date start, Date end);
	Optional<SubjectUnitLog> findBySubjectIdAndUnitIdAndTenantId(String subjectId, String unitId, String tenantId);
	
}
