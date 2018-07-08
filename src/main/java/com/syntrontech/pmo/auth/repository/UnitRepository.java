package com.syntrontech.pmo.auth.repository;

import java.util.List;
import java.util.Optional;

import com.syntrontech.pmo.auth.model.Unit;
import com.syntrontech.pmo.model.common.ModelStatus;
import org.springframework.data.repository.CrudRepository;


public interface UnitRepository extends CrudRepository<Unit, Long>{
	
	Optional<Unit> findByIdAndTenantIdAndStatusNot(String id, String tenantId, ModelStatus status);
	
	List<Unit> findByParentIdAndTenantIdAndStatusNot(String parentId, String tenantId, ModelStatus status);
	
	List<Unit> findByTenantIdAndStatusNot(String tenantId, ModelStatus status);
	
	List<Unit> findByStatusNot(ModelStatus status);
}
