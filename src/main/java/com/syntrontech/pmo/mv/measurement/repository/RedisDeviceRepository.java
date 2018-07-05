package com.syntrontech.pmo.mv.measurement.repository;

import com.syntrontech.measurement.model.RedisDevice;
import com.syntrontech.redis.RedisHashRepository;

public interface RedisDeviceRepository extends RedisHashRepository<String, RedisDevice> {

}
