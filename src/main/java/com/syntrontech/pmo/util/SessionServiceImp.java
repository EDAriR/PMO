package com.syntrontech.pmo.util;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.syntrontech.redis.RedisHashRepositoryProxy;

import redis.clients.jedis.JedisPool;

@Component
public class SessionServiceImp implements SessionService {

	@Autowired
	private JedisPool pool;

	@Value("${session.timeout-sec}")
	private String timeoutSec;
	
	private SessionRepository getRepository() {
		return RedisHashRepositoryProxy.getRepository(pool, SessionRepository.class);
	}

	@Override
	public Optional<Session> findSessionBySessionToken(String token) {
		return getRepository().get(token);
	}

	@Override
	public void updateExpiredTime(String token) {
		if(Objects.nonNull(timeoutSec) && !timeoutSec.isEmpty()){
			getRepository().expire(token, Integer.parseInt(timeoutSec));
		}
	}
	
}
