package com.syntrontech.pmo.cip.service;

import com.syntrontech.pmo.cip.exception.client.ObjectNotExistedException;
import com.syntrontech.pmo.cip.model.Subject;
import com.syntrontech.pmo.cip.model.redis.RedisSubject;

import java.util.List;
import java.util.Optional;


public interface RedisSubjectService {
	
	void update(Subject subject);
	
	void delete(String id, String userId, String tenantId) throws ObjectNotExistedException;
	
	Optional<RedisSubject> findRedisSubjectByIdAndUserIdAndTenantId(String id, String userId, String tenantId);
	
	void _save(RedisSubject redisSubject);
	
	void _delete(String redisId);
	
	List<RedisSubject> findAll();
}
