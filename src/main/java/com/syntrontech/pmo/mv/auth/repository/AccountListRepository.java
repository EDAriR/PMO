package com.syntrontech.pmo.mv.auth.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.syntrontech.auth.model.AccountList;

public interface AccountListRepository extends CrudRepository<AccountList, String>{
	Optional<AccountList> findByAccount(String account);
	List<AccountList> findByUserId(String userId);
}
