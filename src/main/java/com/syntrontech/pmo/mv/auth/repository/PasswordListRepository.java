package com.syntrontech.pmo.mv.auth.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.syntrontech.auth.model.PasswordList;

public interface PasswordListRepository extends CrudRepository<PasswordList, Long>{
	
	List<PasswordList> findByUserId(String userId);
	List<PasswordList> findByUserIdAndAccount(String userId, String account);
}
