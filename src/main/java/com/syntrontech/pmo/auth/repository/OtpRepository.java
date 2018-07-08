package com.syntrontech.pmo.auth.repository;

import com.syntrontech.pmo.auth.model.redis.Otp;
import com.syntrontech.redis.RedisHashRepository;

public interface OtpRepository extends RedisHashRepository<String, Otp>{

}
