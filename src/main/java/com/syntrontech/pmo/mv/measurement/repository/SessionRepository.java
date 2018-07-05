package com.syntrontech.pmo.mv.measurement.repository;

import com.syntrontech.measurement.model.Session;
import com.syntrontech.redis.RedisHashRepository;

public interface SessionRepository extends RedisHashRepository<String, Session>{

}
