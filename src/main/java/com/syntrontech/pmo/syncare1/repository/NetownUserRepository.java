package com.syntrontech.pmo.syncare1.repository;

import com.syntrontech.pmo.syncare1.model.NetownUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface NetownUserRepository extends JpaRepository<NetownUser, Long> {
	public Optional<NetownUser> getAccount(String account);

	public List<NetownUser> queryByAccountAndNames(String account, String name);
}
