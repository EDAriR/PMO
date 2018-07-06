package com.syntrontech.pmo.util;

import java.util.Optional;


public interface RedisUserService {
	
	Optional<RedisUser> findRedisUserByUserId(String userId);
}
