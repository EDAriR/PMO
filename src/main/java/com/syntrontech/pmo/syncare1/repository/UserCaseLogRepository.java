package com.syntrontech.pmo.syncare1.repository;

import com.syntrontech.pmo.syncare1.model.SystemUser;
import com.syntrontech.pmo.syncare1.model.UserCaseLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserCaseLogRepository extends JpaRepository<UserCaseLog, Long> {

	public List<UserCaseLog> findByUser(SystemUser user);
}
