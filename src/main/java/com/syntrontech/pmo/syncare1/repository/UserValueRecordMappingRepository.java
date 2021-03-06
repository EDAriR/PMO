package com.syntrontech.pmo.syncare1.repository;

import com.syntrontech.pmo.syncare1.model.UserValueRecord;
import com.syntrontech.pmo.syncare1.model.UserValueRecordMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserValueRecordMappingRepository extends JpaRepository<UserValueRecordMapping, Long> {
	public List<UserValueRecordMapping> findNotSyncPmoRecordMappingByBodyValueRecordId(UserValueRecord userValueRecord);
	
	public boolean modifyUserValueRecordMapping(UserValueRecord userValueRecord);
}
