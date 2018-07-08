package com.syntrontech.pmo.auth.repository;

import java.util.List;
import java.util.Optional;

import com.syntrontech.pmo.auth.model.Tenant;
import com.syntrontech.pmo.model.common.ModelStatus;
import org.springframework.data.repository.CrudRepository;


public interface TenantRepository extends CrudRepository<Tenant, Long> {
	
	Optional<Tenant> findByIdAndStatusNot(String id, ModelStatus status);
	
	Optional<Tenant> findByIdAndParentIdAndStatusNot(String id, String parentId, ModelStatus status);
	
	List<Tenant> findByParentIdAndStatusNot(String parentId, ModelStatus status);
	
	List<Tenant> findByStatusNot(ModelStatus status);
}