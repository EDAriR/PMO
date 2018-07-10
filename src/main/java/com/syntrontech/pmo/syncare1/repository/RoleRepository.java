package com.syntrontech.pmo.syncare1.repository;

import com.syntrontech.pmo.syncare1.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Long> {
	public Optional<Role> getByName(String name);
}
