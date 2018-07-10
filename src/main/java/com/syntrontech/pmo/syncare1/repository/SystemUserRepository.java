package com.syntrontech.pmo.syncare1.repository;

import com.syntrontech.pmo.syncare1.model.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface SystemUserRepository extends JpaRepository<SystemUser, Long> {
	public Optional<SystemUser> getAccount(String account);

	public List<SystemUser> queryByAccountAndNames(String account, String name);

	public List<SystemUser> queryByAccountAndNamesIgnoreMyself(Integer id, String account, String name);
	
	public int updateRecluseByIdNos(List<String> userAccountList);
	
	public List<SystemUser> queryUsersRecluseCount();
}
