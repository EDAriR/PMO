package com.syntrontech.pmo.cip.repository;

import java.util.List;
import java.util.Optional;

import com.syntrontech.pmo.cip.model.UnitMeta;
import com.syntrontech.pmo.model.common.ModelMgmtStatus;
import org.springframework.data.repository.CrudRepository;


public interface UnitMetaRepository extends CrudRepository<UnitMeta, Long>{
	
	Optional<UnitMeta> findByUnitIdAndTenantIdAndUnitStatusNot(String id, String tenantId, ModelMgmtStatus status);
	
	List<UnitMeta> findByUnitStatusNot(ModelMgmtStatus status);
}	
