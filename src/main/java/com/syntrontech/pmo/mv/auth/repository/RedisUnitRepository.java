package com.syntrontech.pmo.mv.auth.repository;

import com.syntrontech.auth.model.redis.RedisUnit;
import com.syntrontech.redis.RedisHashRepository;

public interface RedisUnitRepository extends RedisHashRepository<String, RedisUnit>{

}
