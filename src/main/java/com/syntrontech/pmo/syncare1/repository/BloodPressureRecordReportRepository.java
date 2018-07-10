package com.syntrontech.pmo.syncare1.repository;

import com.syntrontech.pmo.syncare1.model.BloodPressureRecordReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface BloodPressureRecordReportRepository extends JpaRepository<BloodPressureRecordReport, Long> {

    public List<BloodPressureRecordReport> findStatisticsReport(Map<String, List<String>> criteria);

//	public List<SimpleAbnormalView> abnormalRecords(ReportQueryParameters queryParameters);

//	public List<SimpleRecordReportView> getAllRecords(Date startDate, Date endDate);

}
