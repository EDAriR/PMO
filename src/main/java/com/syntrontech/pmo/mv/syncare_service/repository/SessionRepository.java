package com.syntrontech.pmo.mv.syncare_service.repository;

import com.syntrontech.cip.model.redis.Session;
import com.syntrontech.redis.RedisHashRepository;

public interface SessionRepository extends RedisHashRepository<String, Session>{

}
