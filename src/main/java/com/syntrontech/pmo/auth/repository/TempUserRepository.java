package com.syntrontech.pmo.auth.repository;

import com.syntrontech.pmo.auth.model.redis.TempUser;
import com.syntrontech.redis.RedisHashRepository;

public interface TempUserRepository extends RedisHashRepository<String, TempUser>{

}
