package com.syntrontech.pmo.cip.repository;

import com.syntrontech.pmo.cip.model.redis.RedisSubject;
import com.syntrontech.redis.RedisHashRepository;

public interface RedisSubjectRepository extends RedisHashRepository<String, RedisSubject> {

}
