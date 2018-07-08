package com.syntrontech.pmo.cip.repository;

import com.syntrontech.pmo.cip.model.redis.RedisQuestionnair;
import com.syntrontech.redis.RedisHashRepository;

public interface RedisQuestionnairRepository extends RedisHashRepository<Long, RedisQuestionnair> {

}
