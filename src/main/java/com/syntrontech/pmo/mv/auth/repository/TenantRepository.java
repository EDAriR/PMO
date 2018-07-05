package com.syntrontech.pmo.mv.auth.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.syntrontech.auth.model.Tenant;
import com.syntrontech.auth.model.common.ModelStatus;

public interface TenantRepository extends CrudRepository<Tenant, Long> {
	
	Optional<Tenant> findByIdAndStatusNot(String id, ModelStatus status);
	
	Optional<Tenant> findByIdAndParentIdAndStatusNot(String id, String parentId, ModelStatus status);
	
	List<Tenant> findByParentIdAndStatusNot(String parentId, ModelStatus status);
	
	List<Tenant> findByStatusNot(ModelStatus status);
}