package com.syntrontech.pmo.cip.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.syntrontech.pmo.auth.model.redis.RedisUnit;
import com.syntrontech.pmo.cip.repository.RedisUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.syntrontech.redis.RedisHashRepositoryProxy;

import redis.clients.jedis.JedisPool;

@Component
public class RedisUnitServiceImp implements RedisUnitService {
	
	@Autowired
	private JedisPool pool;

	private RedisUnitRepository getRepository() {
		return RedisHashRepositoryProxy.getRepository(pool, RedisUnitRepository.class);
	}
	
	private String createRedisId(String id, String tenantId){
		return tenantId + ":" + id;
	}
	
	@Override
	public Optional<RedisUnit> findRedisUnitByIdAndTenantId(String id, String tenantId) {
		return getRepository().get(createRedisId(id, tenantId));
	}

	@Override
	public List<RedisUnit> findRedisUnitsByParentIdAndTenantId(String id, String tenantId) {
		return getRepository().getAll()
							  .stream()
							  .filter(unit -> unit.getParentId().equals(id))
							  .collect(Collectors.toList());
	}
	
	//找所有子孫
	@Override
	public List<String> findRedisUnitDescendants(String id, String tenantId){
		List<RedisUnit> sons=findRedisUnitsByParentIdAndTenantId(id,tenantId);
		List<String> units = new ArrayList<String>();
		for (RedisUnit son : sons) {
			units.add(son.getId());
			if (findRedisUnitsByParentIdAndTenantId(son.getId(),son.getTenantId()).size() > 0) {
				units.addAll(findRedisUnitDescendants(son.getId(),son.getTenantId()));
			}
		}
		units.add(id);
		return units;
	}
	
	@Override
	public Set<String> findRedisUnitDescendants(List<String> unitIds,String tenantId) {
		Set<String> result=new HashSet<String>(unitIds);
		for(String unit:unitIds){
			List<String> unitSons = findRedisUnitDescendants(unit,tenantId);
			result.addAll(unitSons);
		}
		return result;
	}

	@Override
	public Optional<RedisUnit> findRedisUnitByNameAndTenantId(String name, String tenantId) {
		return getRepository().getAll()
				  			  .stream()
				  			  .filter(unit -> unit.getTenantId().equals(tenantId))
				  			  .filter(unit -> unit.getName().equals(name))
				  			  .findFirst();
	}

}
