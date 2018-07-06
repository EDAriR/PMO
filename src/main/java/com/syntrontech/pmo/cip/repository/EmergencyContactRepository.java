package com.syntrontech.pmo.cip.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.syntrontech.cip.model.EmergencyContact;

public interface EmergencyContactRepository extends PagingAndSortingRepository<EmergencyContact, Long> ,JpaSpecificationExecutor<EmergencyContact>{

}
