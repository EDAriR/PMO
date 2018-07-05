package com.syntrontech.pmo.mv.auth.repository;

import com.syntrontech.auth.model.redis.RedisRole;
import com.syntrontech.redis.RedisHashRepository;

public interface RedisRoleRepository extends RedisHashRepository<String, RedisRole> {

}
