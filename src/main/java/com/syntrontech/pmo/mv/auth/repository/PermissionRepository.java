package com.syntrontech.pmo.mv.auth.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.syntrontech.auth.model.Permission;

public interface PermissionRepository extends CrudRepository<Permission, Long> {
	
	Optional<Permission> findById(String id);
	
}
