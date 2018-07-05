package com.syntrontech.pmo.mv.syncare_service.repository;

import java.util.List;
import java.util.Optional;

import com.syntrontech.pmo.mv.syncare_service.model.Device;
import com.syntrontech.pmo.mv.syncare_service.model.common.ModelStatus;
import org.springframework.data.repository.CrudRepository;


public interface DeviceRepository extends CrudRepository<Device, Long>{
	Optional<Device> findByIdAndTenantIdAndStatusNot(String id, String tenantId, ModelStatus status);
	Optional<Device> findByMacAddressAndTenantIdAndStatusNot(String macAddress, String tenantId, ModelStatus status);
	List<Device> findByUnitIdAndTenantIdAndStatusNot(String unitId, String tenantId, ModelStatus status);
	List<Device> findByStatusNot(ModelStatus status);
}
