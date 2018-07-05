package com.syntrontech.pmo.mv.auth.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.syntrontech.auth.model.Config;

public interface ConfigRepository extends CrudRepository<Config, Long>{
	
	Optional<Config> findById(String id);
	
}
