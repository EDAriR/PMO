package com.syntrontech.pmo.syncare1.repository;

import com.syntrontech.pmo.syncare1.model.entity.RecordReportView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface RecordReportViewRepository extends JpaRepository<RecordReportView, Long> {
	public List<RecordReportView> findByDate(Date start_date, Date end_date);

	public List<RecordReportView> findRecords(String user_account, String location_name, String locaion_id,
                                              int case_type, Date startDate, Date endDate);

	public Optional<RecordReportView> findLast(String user_account);
}
