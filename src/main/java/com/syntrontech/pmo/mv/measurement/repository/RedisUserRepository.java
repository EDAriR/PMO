package com.syntrontech.pmo.mv.measurement.repository;

import com.syntrontech.measurement.model.RedisUser;
import com.syntrontech.redis.RedisHashRepository;

public interface RedisUserRepository extends RedisHashRepository<String, RedisUser> {

}
