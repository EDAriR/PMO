package com.syntrontech.pmo.mv.auth.repository;

import com.syntrontech.pmo.mv.auth.model.redis.Otp;
import com.syntrontech.redis.RedisHashRepository;

public interface OtpRepository extends RedisHashRepository<String, Otp>{

}
