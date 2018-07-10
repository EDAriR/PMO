package com.syntrontech.pmo.syncare1.repository;

import com.syntrontech.pmo.syncare1.model.entity.SystemUser;
import com.syntrontech.pmo.syncare1.model.entity.UserPrescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserPrescriptionRepository extends JpaRepository<UserPrescription, Long> {
	public List<UserPrescription> find(SystemUser user);
}
