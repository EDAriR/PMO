package com.syntrontech.pmo.auth.repository;

import java.util.Optional;

import com.syntrontech.pmo.auth.model.LoginLog;
import org.springframework.data.repository.CrudRepository;


public interface LoginLogRepository extends CrudRepository<LoginLog, Long> {
	Optional<LoginLog> findTop1ByUserIdOrderByCreateTimeDesc(String userId);
}
