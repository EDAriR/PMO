package com.syntrontech.pmo.mv.syncare_service.repository;

import com.syntrontech.cip.model.redis.RedisUser;
import com.syntrontech.redis.RedisHashRepository;

public interface RedisUserRepository extends RedisHashRepository<String, RedisUser> {

}
