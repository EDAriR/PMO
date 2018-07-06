package com.syntrontech.pmo.cip.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.syntrontech.cip.model.Device;
import com.syntrontech.cip.model.common.ModelStatus;

public interface DeviceRepository extends CrudRepository<Device, Long>{
	Optional<Device> findByIdAndTenantIdAndStatusNot(String id, String tenantId, ModelStatus status);
	Optional<Device> findByMacAddressAndTenantIdAndStatusNot(String macAddress, String tenantId, ModelStatus status);
	List<Device> findByUnitIdAndTenantIdAndStatusNot(String unitId, String tenantId, ModelStatus status);
	List<Device> findByStatusNot(ModelStatus status);
}
