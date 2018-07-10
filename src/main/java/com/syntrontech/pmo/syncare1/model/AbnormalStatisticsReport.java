package com.syntrontech.pmo.syncare1.model;

public class AbnormalStatisticsReport extends CommonReport{

	private Integer notYetProcessed;
	private Integer normal;
	private Integer abnormal;
	private Integer rejectToTreatment;
	private Integer canNotContact;

	public Integer getNotYetProcessed() {
		return notYetProcessed;
	}
	public void setNotYetProcessed(Integer notYetProcessed) {
		this.notYetProcessed = notYetProcessed;
	}
	public Integer getNormal() {
		return normal;
	}
	public void setNormal(Integer normal) {
		this.normal = normal;
	}
	public Integer getAbnormal() {
		return abnormal;
	}
	public void setAbnormal(Integer abnormal) {
		this.abnormal = abnormal;
	}
	public Integer getRejectToTreatment() {
		return rejectToTreatment;
	}
	public void setRejectToTreatment(Integer rejectToTreatment) {
		this.rejectToTreatment = rejectToTreatment;
	}
	public Integer getCanNotContact() {
		return canNotContact;
	}
	public void setCanNotContact(Integer canNotContact) {
		this.canNotContact = canNotContact;
	}
}
