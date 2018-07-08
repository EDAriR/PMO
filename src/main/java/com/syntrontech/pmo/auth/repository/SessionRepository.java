package com.syntrontech.pmo.auth.repository;

import com.syntrontech.pmo.auth.model.redis.Session;
import com.syntrontech.redis.RedisHashRepository;

public interface SessionRepository extends RedisHashRepository<String, Session>{

}
