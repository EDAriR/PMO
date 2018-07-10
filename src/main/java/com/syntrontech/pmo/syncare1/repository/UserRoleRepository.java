package com.syntrontech.pmo.syncare1.repository;

import com.syntrontech.pmo.syncare1.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
	public List<UserRole> queryAdminList();
}
