package com.syntrontech.pmo.mv.measurement.repository;

import com.syntrontech.measurement.model.RedisSubject;
import com.syntrontech.redis.RedisHashRepository;

public interface RedisSubjectRepository extends RedisHashRepository<String, RedisSubject> {

}
