package com.syntrontech.pmo.mv.syncare_service.repository;

import com.syntrontech.cip.model.redis.RedisDevice;
import com.syntrontech.redis.RedisHashRepository;

public interface RedisDeviceRepository extends RedisHashRepository<String, RedisDevice> {

}
