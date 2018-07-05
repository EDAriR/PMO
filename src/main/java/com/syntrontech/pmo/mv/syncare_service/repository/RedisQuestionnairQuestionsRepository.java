package com.syntrontech.pmo.mv.syncare_service.repository;

import com.syntrontech.cip.model.redis.RedisQuestionnairQuestions;
import com.syntrontech.redis.RedisHashRepository;

public interface RedisQuestionnairQuestionsRepository extends RedisHashRepository<Long, RedisQuestionnairQuestions>{

}
