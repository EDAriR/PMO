package com.syntrontech.pmo.cip.service;

import com.syntrontech.pmo.cip.model.redis.RedisUnit;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface RedisUnitService {

	Optional<RedisUnit> findRedisUnitByIdAndTenantId(String id, String tenantId);
	List<RedisUnit> findRedisUnitsByParentIdAndTenantId(String id, String tenantId);
	List<String> findRedisUnitDescendants(String id, String tenantId);
	Set<String> findRedisUnitDescendants(List<String> unitIds, String tenantId);
	Optional<RedisUnit> findRedisUnitByNameAndTenantId(String name, String tenantId);
}
