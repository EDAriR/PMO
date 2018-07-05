package com.syntrontech.pmo.mv.syncare_service.repository;

import com.syntrontech.cip.model.redis.RedisSubject;
import com.syntrontech.redis.RedisHashRepository;

public interface RedisSubjectRepository extends RedisHashRepository<String, RedisSubject> {

}
