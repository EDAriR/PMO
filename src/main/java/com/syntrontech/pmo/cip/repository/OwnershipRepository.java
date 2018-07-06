package com.syntrontech.pmo.cip.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.syntrontech.cip.model.Ownership;

public interface OwnershipRepository extends PagingAndSortingRepository<Ownership, Long> ,JpaSpecificationExecutor<Ownership>{
	
	Optional<Ownership> findByMyIdAndUserId(String myId, String userId);
	Optional<Ownership> findByMyIdAndUserIdAndTenantId(String myId, String userId, String tenantId);
	Optional<Ownership> findByOwnershipId(String ownershipId);
	Optional<Ownership> findByOwnershipIdAndTenantId(String ownershipId, String tenantId);
	List<Ownership> findByUserId(String myId);
	List<Ownership> findByUserIdAndTenantId(String myId, String tenantId);
	List<Ownership> findByMyId(String myId);
}