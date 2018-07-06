package com.syntrontech.pmo.util;

import java.util.Optional;


public interface SessionService {
	
	Optional<Session> findSessionBySessionToken(String token);
	
	void updateExpiredTime(String token);
	
}
