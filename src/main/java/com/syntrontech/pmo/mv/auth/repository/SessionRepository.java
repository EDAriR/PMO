package com.syntrontech.pmo.mv.auth.repository;

import com.syntrontech.auth.model.redis.Session;
import com.syntrontech.redis.RedisHashRepository;

public interface SessionRepository extends RedisHashRepository<String, Session>{

}
