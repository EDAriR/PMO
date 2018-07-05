package com.syntrontech.pmo.mv.syncare1.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.syntrontech.syncare.model.ObjectConverter;
import com.syntrontech.syncare.model.transfer.ReportUploadTO;

@Entity
@Table(name = "report_upload")
@NamedQuery(name = "ReportUpload.findAll", query = "SELECT r FROM ReportUpload r")
public class ReportUpload implements ObjectConverter<ReportUploadTO>, Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "report_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer reportId;
	
	@Column(name = "report_name")
	private String reportName;
	
	@Column(name = "report_size")
	private Integer reportSize;
	
	@Column(name = "report_path")
	private String reportPath;

	@Column(name = "report_type")
	private String reportType;
	
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "upload_date")
	private Timestamp uploadDate = new Timestamp(new Date().getTime());
	
	
	
	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public Integer getReportSize() {
		return reportSize;
	}

	public void setReportSize(Integer reportSize) {
		this.reportSize = reportSize;
	}

	public String getReportPath() {
		return reportPath;
	}

	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Timestamp uploadDate) {
		this.uploadDate = uploadDate;
	}
	
	
	
	public static ReportUpload create(ReportUploadTO reportUploadA) {
		ReportUpload reportUpload = new ReportUpload();
		
		reportUpload.setReportPath(reportUploadA.getReportPath());
		reportUpload.setReportName(reportUploadA.getReportName());
		reportUpload.setReportSize(reportUploadA.getReportSize());
		reportUpload.setReportType(reportUploadA.getReportType());
		
		reportUpload.setUserId(reportUploadA.getUserId());
		reportUpload.setUploadDate(new Timestamp(new Date().getTime()));
		return reportUpload;
	}
	
	@Override
	public ReportUploadTO convert(boolean relation) {
		ReportUploadTO to = new ReportUploadTO();

		to.setReportId(getReportId());
		to.setReportName(getReportName());
		to.setReportSize(getReportSize());
		to.setReportPath(getReportPath());
		to.setReportType(getReportType());
		to.setUserId(getReportId());
		to.setUploadDate(getUploadDate());

		return to;
	}

	@Override
	public String toString() {
		return "ReportUpload ["
				+ "reportId=" + reportId 
				+ ", reportName=" + reportName
				+ ", reportSize=" + reportSize 
				+ ", reportType=" + reportType
				+ ", reportPath=" + reportPath
				+ ", userId=" + userId 
				+ ", uploadDate=" + uploadDate
				+ "]";
	}
}
