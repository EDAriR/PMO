package com.syntrontech.pmo.cip.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.syntrontech.cip.model.UnitMeta;
import com.syntrontech.cip.model.common.ModelMgmtStatus;

public interface UnitMetaRepository extends CrudRepository<UnitMeta, Long>{
	
	Optional<UnitMeta> findByUnitIdAndTenantIdAndUnitStatusNot(String id, String tenantId, ModelMgmtStatus status);
	
	List<UnitMeta> findByUnitStatusNot(ModelMgmtStatus status);
}	
