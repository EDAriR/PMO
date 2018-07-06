package com.syntrontech.pmo.util;

import java.util.Arrays;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syntrontech.autoTool.exception.client.ForbiddenException;
import com.syntrontech.autoTool.exception.client.UnauthorizedException;
import com.syntrontech.autoTool.model.UserModel;
import com.syntrontech.autoTool.service.SessionValidationService;
import com.syntrontech.pmo.cip.model.common.UserStatus;

@Service
public class ValidateServiceImp implements SessionValidationService {
	
	private static final Logger logger = LoggerFactory.getLogger(ValidateServiceImp.class);
	
	@Autowired
	private SessionService sessionService;
	
	@Autowired
	private RedisUserService redisUserService;
	
	@Override
	public UserModel getValidatedUser(Optional<String> token, String permissionId) throws ForbiddenException, UnauthorizedException {
		
		String sessionToken = token.orElseThrow(() -> {
			logger.debug("session token doesn't exist");
			return new UnauthorizedException();
		});
		Session session = sessionService.findSessionBySessionToken(sessionToken)
										.orElseThrow(() -> {
											logger.debug("session token doesn't exist");
											return new UnauthorizedException();
										});
		sessionService.updateExpiredTime(session.getId());
		
		RedisUser user = redisUserService.findRedisUserByUserId(session.getUserId())
										 .orElseThrow(() -> {
												logger.debug("userId[{}] doesn't exist", session.getUserId());
												return new ForbiddenException();
											});
		
		if(!user.getStatus().equals(UserStatus.ENABLED.name())){
			logger.debug("userId[{}]'s status wasn't enabled", user.getUserId());
			throw new ForbiddenException();
		}
		
		if(!Arrays.asList(user.getPermissionIds()).contains(permissionId)){
			logger.debug("userId[{}] doesn't have the permission[{}]", user.getUserId(), permissionId);
			throw new ForbiddenException();
		}
		
		return user;
	}

}
