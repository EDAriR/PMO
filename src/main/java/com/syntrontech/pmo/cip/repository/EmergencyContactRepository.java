package com.syntrontech.pmo.cip.repository;

import com.syntrontech.pmo.cip.model.EmergencyContact;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface EmergencyContactRepository extends PagingAndSortingRepository<EmergencyContact, Long> ,JpaSpecificationExecutor<EmergencyContact>{

}
