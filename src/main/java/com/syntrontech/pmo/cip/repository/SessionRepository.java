package com.syntrontech.pmo.cip.repository;

import com.syntrontech.cip.model.redis.Session;
import com.syntrontech.redis.RedisHashRepository;

public interface SessionRepository extends RedisHashRepository<String, Session>{

}
