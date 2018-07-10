package com.syntrontech.pmo.syncare1.repository;

import com.syntrontech.pmo.syncare1.model.entity.RecordReportAbnormalView;
import com.syntrontech.pmo.syncare1.model.entity.SimpleAbnormalView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface RecordReportAbnormalViewRepository extends JpaRepository<RecordReportAbnormalView, Long> {
	public List<RecordReportAbnormalView> findByDate(Date start_date, Date end_date);

	public List<SimpleAbnormalView> findRecords(String user_account, String location_name, String locaion_id,
												int case_type, Date startDate, Date endDate);

	public Optional<RecordReportAbnormalView> findLast(String user_account);
}
