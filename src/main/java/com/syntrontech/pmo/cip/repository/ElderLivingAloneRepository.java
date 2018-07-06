package com.syntrontech.pmo.cip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.syntrontech.cip.model.ElderLivingAloneModel;

public interface ElderLivingAloneRepository extends PagingAndSortingRepository<ElderLivingAloneModel, Long> ,JpaSpecificationExecutor<ElderLivingAloneModel>{

	List<ElderLivingAloneModel> findAll();
	List<ElderLivingAloneModel> findByUnitId(String unitId);
	List<ElderLivingAloneModel> findByTenantId(String tenantId);
}
