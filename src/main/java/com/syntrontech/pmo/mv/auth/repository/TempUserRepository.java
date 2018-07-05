package com.syntrontech.pmo.mv.auth.repository;

import com.syntrontech.auth.model.redis.TempUser;
import com.syntrontech.redis.RedisHashRepository;

public interface TempUserRepository extends RedisHashRepository<String, TempUser>{

}
