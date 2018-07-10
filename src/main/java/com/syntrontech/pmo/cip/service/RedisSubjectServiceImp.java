package com.syntrontech.pmo.cip.service;

import java.util.List;
import java.util.Optional;

import com.syntrontech.pmo.cip.exception.client.ObjectNotExistedException;
import com.syntrontech.pmo.cip.model.Subject;
import com.syntrontech.pmo.cip.model.redis.RedisSubject;
import com.syntrontech.pmo.cip.repository.RedisSubjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.syntrontech.redis.RedisHashRepositoryProxy;

import redis.clients.jedis.JedisPool;

@Component
public class RedisSubjectServiceImp implements RedisSubjectService {

	private static final Logger logger = LoggerFactory.getLogger(RedisSubjectServiceImp.class);
	
	@Autowired
	private JedisPool pool;

//	@Autowired
//	private Publisher publisher;
	
	private RedisSubjectRepository getRepository() {
		return RedisHashRepositoryProxy.getRepository(pool, RedisSubjectRepository.class);
	}
	
	private String createRedisId(String id, String userId, String tenantId){
		return tenantId + ":" + userId + ":" + id;
	}
	
	@Override
	public void update(Subject subject) {
		RedisSubject redisSubject = new RedisSubject();
		redisSubject.setRedisId(createRedisId(subject.getId(), subject.getUserId(), subject.getTenantId()));
		redisSubject.setSequence(subject.getSequence());
		redisSubject.setId(subject.getId());
		redisSubject.setName(subject.getName());
		redisSubject.setGender(subject.getGender());
		redisSubject.setBirthday(subject.getBirthday());
		redisSubject.setUserId(subject.getUserId());
		redisSubject.setUnitId(subject.getUnitId());
		redisSubject.setUnitName(subject.getUnitName());
		redisSubject.setTenantId(subject.getTenantId());
		redisSubject.setStatus(subject.getStatus());
		
		_save(redisSubject);
	}

	@Override
	public void delete(String id, String userId, String tenantId) throws ObjectNotExistedException {
		findRedisSubjectByIdAndUserIdAndTenantId(id, userId, tenantId).orElseThrow(() -> {
			logger.debug("user[{}]'s subject[{}] which is in the tenant[{}] doesn't exist in Redis ", userId, id, tenantId);
			return new ObjectNotExistedException("subjectId");
		});
		
		_delete(createRedisId(id, userId, tenantId));
		logger.trace("delete user[{}]'s subject[{}] which is in the tenant[{}] and was stored in Redis", userId, id, tenantId);
	}

	@Override
	public Optional<RedisSubject> findRedisSubjectByIdAndUserIdAndTenantId(String id, String userId, String tenantId) {
		return getRepository().get(createRedisId(id, userId, tenantId));
	}

	@Override
	public void _save(RedisSubject redisSubject) {
		getRepository().save(redisSubject);
		logger.trace("update redisSubject[{}] successfully", redisSubject.toString());
		
//		ObjectMessage message = new ObjectMessage();
//		message.setId(redisSubject.getRedisId());
//		message.setAction(ObjectMessageAction.UPDATED);
//		publisher.publish("subject", message);
	}

	@Override
	public void _delete(String redisId) {
		getRepository().delete(redisId);
		
//		ObjectMessage message = new ObjectMessage();
//		message.setId(redisId);
//		message.setAction(ObjectMessageAction.DELETED);
//		publisher.publish("subject", message);
	}

	@Override
	public List<RedisSubject> findAll() {
		return getRepository().getAll();
	}
	
}
