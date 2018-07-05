package com.syntrontech.pmo.mv.syncare_service.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.syntrontech.cip.model.Subject;
import com.syntrontech.cip.model.common.ModelStatus;

public interface SubjectRepository extends CrudRepository<Subject, Long>{
	Optional<Subject> findByIdAndTenantIdAndStatusNot(String id, String tenantId, ModelStatus status);
	Optional<Subject> findByIdAndUserIdAndTenantIdAndStatusNot(String id, String userId, String tenantId, ModelStatus status);
	List<Subject> findByUserIdAndStatusNot(String userId, ModelStatus status);
	List<Subject> findByUnitIdAndTenantIdAndStatusNot(String unitId, String tenantId, ModelStatus status);
	List<Subject> findByStatusNot(ModelStatus status);
	long countByUnitIdAndCreateTimeBetween(String unitId, Date start, Date end);
}
