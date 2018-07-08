package com.syntrontech.pmo.auth.repository;

import com.syntrontech.pmo.auth.model.redis.RedisRole;
import com.syntrontech.redis.RedisHashRepository;

public interface RedisRoleRepository extends RedisHashRepository<String, RedisRole> {

}
