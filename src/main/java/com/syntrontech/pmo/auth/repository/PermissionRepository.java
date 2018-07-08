package com.syntrontech.pmo.auth.repository;

import java.util.Optional;

import com.syntrontech.pmo.auth.model.Permission;
import org.springframework.data.repository.CrudRepository;


public interface PermissionRepository extends CrudRepository<Permission, Long> {
	
	Optional<Permission> findById(String id);
	
}
