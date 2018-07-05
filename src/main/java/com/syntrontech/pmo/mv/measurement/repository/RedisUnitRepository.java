package com.syntrontech.pmo.mv.measurement.repository;

import com.syntrontech.measurement.model.RedisUnit;
import com.syntrontech.redis.RedisHashRepository;

public interface RedisUnitRepository extends RedisHashRepository<String, RedisUnit>{

}
