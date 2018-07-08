package com.syntrontech.pmo.cip.repository;

import com.syntrontech.pmo.auth.model.redis.RedisUser;
import com.syntrontech.redis.RedisHashRepository;

public interface RedisUserRepository extends RedisHashRepository<String, RedisUser> {

}
