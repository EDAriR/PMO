package com.syntrontech.pmo.mv.auth.repository;

import com.syntrontech.auth.model.redis.RedisUser;
import com.syntrontech.redis.RedisHashRepository;

public interface RedisUserRepository extends RedisHashRepository<String, RedisUser> {

}
