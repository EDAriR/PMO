package com.syntrontech.pmo.auth.repository;

import java.util.List;
import java.util.Optional;

import com.syntrontech.pmo.auth.model.AccountList;
import org.springframework.data.repository.CrudRepository;


public interface AccountListRepository extends CrudRepository<AccountList, String>{
	Optional<AccountList> findByAccount(String account);
	List<AccountList> findByUserId(String userId);
}
