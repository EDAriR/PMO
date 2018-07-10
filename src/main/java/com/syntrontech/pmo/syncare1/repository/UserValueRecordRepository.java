package com.syntrontech.pmo.syncare1.repository;

import com.syntrontech.pmo.syncare1.model.common.Service;
import com.syntrontech.pmo.syncare1.model.SystemUser;
import com.syntrontech.pmo.syncare1.model.UserValueRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface UserValueRecordRepository extends JpaRepository<UserValueRecord, Long> {
	public List<UserValueRecord> findByRange(SystemUser user, Service.RecordType type, Date startDate, Date endDate);

	public Optional<UserValueRecord> findOneBeforeRecordDate(SystemUser user, Date baseDate);
	
	public Optional<UserValueRecord> findLast(SystemUser user, Service.RecordType type);
	
	public List<UserValueRecord> find(SystemUser user, Service.RecordType type);
	
	public List<UserValueRecord> findNotSyncPmoRecordByUserId(SystemUser user);
	
	public Optional<UserValueRecord> findRecordByUserAndBodyValueRecordId(SystemUser user, Integer bodyValueRecordId);
	
	public UserValueRecord findRecordByLatestUserAccountSerial(SystemUser user);
}
