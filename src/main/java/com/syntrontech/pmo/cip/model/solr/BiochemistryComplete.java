package com.syntrontech.pmo.cip.model.solr;

public class BiochemistryComplete extends SolrMeasurement {
	
	private Long groupId_l;
	private String HbA1CValue;
	private String Total_CholesterolValue;
	private String TriglycerideValue;
	private String HDL_CholesterolValue;
	private String LDL_CholesterolValue;
	private String GOTValue;
	private String GPTValue;
	private String CreatinineValue;
	
	
	public Long getGroupId_l() {
		return groupId_l;
	}
	public void setGroupId_l(Long groupId_l) {
		this.groupId_l = groupId_l;
	}
	public String getHbA1CValue() {
		return HbA1CValue;
	}
	public void setHbA1CValue(String hbA1CValue) {
		HbA1CValue = hbA1CValue;
	}
	public String getTotal_CholesterolValue() {
		return Total_CholesterolValue;
	}
	public void setTotal_CholesterolValue(String total_CholesterolValue) {
		Total_CholesterolValue = total_CholesterolValue;
	}
	public String getTriglycerideValue() {
		return TriglycerideValue;
	}
	public void setTriglycerideValue(String triglycerideValue) {
		TriglycerideValue = triglycerideValue;
	}
	public String getHDL_CholesterolValue() {
		return HDL_CholesterolValue;
	}
	public void setHDL_CholesterolValue(String hDL_CholesterolValue) {
		HDL_CholesterolValue = hDL_CholesterolValue;
	}
	public String getLDL_CholesterolValue() {
		return LDL_CholesterolValue;
	}
	public void setLDL_CholesterolValue(String lDL_CholesterolValue) {
		LDL_CholesterolValue = lDL_CholesterolValue;
	}
	public String getGOTValue() {
		return GOTValue;
	}
	public void setGOTValue(String gOTValue) {
		GOTValue = gOTValue;
	}
	public String getGPTValue() {
		return GPTValue;
	}
	public void setGPTValue(String gPTValue) {
		GPTValue = gPTValue;
	}
	public String getCreatinineValue() {
		return CreatinineValue;
	}
	public void setCreatinineValue(String creatinineValue) {
		CreatinineValue = creatinineValue;
	}
	
	public BiochemistryComplete setUniversalValue(SolrBiochemistry sbOne) {
		
		this.sequence_l = sbOne.getSequence_l();
		this.recordTime_dt = sbOne.getRecordTime_dt();
		this.status_s = sbOne.getStatus_s();
		this.createBy_s = sbOne.getCreateBy_s();
		this.tenantId_s = sbOne.getTenantId_s();
		this.subjectSeq_l = sbOne.getSubjectSeq_l();
		this.subjectId_s = sbOne.getSubjectId_s();
		this.subjectName_s = sbOne.getSubjectName_s();
		this.subjectAge_l = sbOne.getSubjectAge_l();
		this.subjectGender_s = sbOne.getSubjectGender_s();
		this.subjectUserId_s = sbOne.getSubjectUserId_s();
		this.subjectUserName_s = sbOne.getSubjectUserName_s();
		this.ruleDescription_s = sbOne.getRuleDescription_s();
		this.unitId_s = sbOne.getUnitId_s();
		this.unitName_s = sbOne.getUnitName_s();
		this.deviceId_s = sbOne.getDeviceId_s();
		this.parentUnitId_s = sbOne.getParentUnitId_s();
		this.parentUnitName_s = sbOne.getParentUnitName_s();
		
		return this;
		
	}
	
}
