package com.syntrontech.pmo.syncare1.repository;

import com.syntrontech.pmo.syncare1.model.ReportUpload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportUploadRepository extends JpaRepository<ReportUpload, Long> {
	public List<ReportUpload> getReportUploadFromUserId(int userId);
	
	// 等有多筆刪除的需求出來再接此
	public void deleteReportUploadByReportIds(List<Integer> deleteIds);
}
