package com.syntrontech.pmo.cip.repository;

import com.syntrontech.pmo.cip.model.redis.RedisQuestionnairQuestions;
import com.syntrontech.redis.RedisHashRepository;

public interface RedisQuestionnairQuestionsRepository extends RedisHashRepository<Long, RedisQuestionnairQuestions>{

}