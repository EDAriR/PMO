package com.syntrontech.pmo.auth.repository;

import com.syntrontech.pmo.auth.model.redis.RedisUnit;
import com.syntrontech.redis.RedisHashRepository;

public interface RedisUnitRepository extends RedisHashRepository<String, RedisUnit>{

}
