package com.syntrontech.pmo.mv.syncare_service.repository;

import com.syntrontech.cip.model.redis.RedisQuestionnair;
import com.syntrontech.redis.RedisHashRepository;

public interface RedisQuestionnairRepository extends RedisHashRepository<Long, RedisQuestionnair> {

}
