package com.syntrontech.pmo.mv.auth.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.syntrontech.auth.model.Unit;
import com.syntrontech.auth.model.common.ModelStatus;

public interface UnitRepository extends CrudRepository<Unit, Long>{
	
	Optional<Unit> findByIdAndTenantIdAndStatusNot(String id, String tenantId, ModelStatus status);
	
	List<Unit> findByParentIdAndTenantIdAndStatusNot(String parentId, String tenantId, ModelStatus status);
	
	List<Unit> findByTenantIdAndStatusNot(String tenantId, ModelStatus status);
	
	List<Unit> findByStatusNot(ModelStatus status);
}
