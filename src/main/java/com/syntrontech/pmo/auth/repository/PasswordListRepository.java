package com.syntrontech.pmo.auth.repository;

import java.util.List;

import com.syntrontech.pmo.auth.model.PasswordList;
import org.springframework.data.repository.CrudRepository;


public interface PasswordListRepository extends CrudRepository<PasswordList, Long>{
	
	List<PasswordList> findByUserId(String userId);
	List<PasswordList> findByUserIdAndAccount(String userId, String account);
}
