package com.syntrontech.pmo.mv.syncare_service.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.syntrontech.cip.model.EmergencyContact;

public interface EmergencyContactRepository extends PagingAndSortingRepository<EmergencyContact, Long> ,JpaSpecificationExecutor<EmergencyContact>{

}
