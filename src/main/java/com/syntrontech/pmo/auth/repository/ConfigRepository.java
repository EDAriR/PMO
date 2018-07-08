package com.syntrontech.pmo.auth.repository;

import java.util.Optional;

import com.syntrontech.pmo.auth.model.Config;
import org.springframework.data.repository.CrudRepository;


public interface ConfigRepository extends CrudRepository<Config, Long>{
	
	Optional<Config> findById(String id);
	
}
