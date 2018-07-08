package com.syntrontech.pmo.cip.repository;

import java.util.Date;
import java.util.Optional;

import com.syntrontech.pmo.cip.model.SubjectUnitLog;
import org.springframework.data.repository.CrudRepository;


public interface SubjectUnitLogRepository extends CrudRepository<SubjectUnitLog, Long>{
	
	long countByUnitId(String unitId);
	long countByUnitIdAndCreateTimeBetween(String unitId, Date start, Date end);
	Optional<SubjectUnitLog> findBySubjectIdAndUnitIdAndTenantId(String subjectId, String unitId, String tenantId);
	
}
