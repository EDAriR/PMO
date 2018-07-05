package com.syntrontech.pmo.mv.auth.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.syntrontech.auth.model.LoginLog;

public interface LoginLogRepository extends CrudRepository<LoginLog, Long> {
	Optional<LoginLog> findTop1ByUserIdOrderByCreateTimeDesc(String userId);
}
