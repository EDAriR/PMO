package com.syntrontech.pmo.cip.model.solr;

import com.syntrontech.pmo.cip.model.Subject;
import com.syntrontech.pmo.model.TO.TO;
import com.syntrontech.pmo.solr.SolrDoc;

import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;


public class SolrSubject implements TO<Subject>, SolrDoc {
	
	private String id;
	
	private String subjectId_s;
	
	private String subjectName_s;
	
	private String subjectGender_s;
	
	private String subjectBirthday_dt;
	
	private String subjectHomePhone_s;
	
	private String subjectAddress_s;
	
	private String subjectEthnicity_s;
	
	private String[] subjectPersonalHistory_ss;
	
	private String[] subjectFamilyHistory_ss;
	
	private String subjectSmoke_s;
	
	private String subjectDrink_s;
	
	private String subjectChewingAreca_s;
	
	private String subjectUserId_s;
	
	private String subjectUnitId_s;
	
	private String subjectUnitName_s;
	
	private String subjectTenantId_s;
	
	private String subjectCreateTime_dt;
	
	private String subjectUpdateTime_dt;

	private static Map<String, String> fieldNameMap;
	
	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getSubjectId_s() {
		return subjectId_s;
	}

	public void setSubjectId_s(String subjectId_s) {
		this.subjectId_s = subjectId_s;
	}

	public String getSubjectName_s() {
		return subjectName_s;
	}

	public void setSubjectName_s(String subjectName_s) {
		this.subjectName_s = subjectName_s;
	}

	public String getSubjectGender_s() {
		return subjectGender_s;
	}

	public void setSubjectGender_s(String subjectGender_s) {
		this.subjectGender_s = subjectGender_s;
	}

	public String getSubjectBirthday_dt() {
		return subjectBirthday_dt;
	}

	public void setSubjectBirthday_dt(String subjectBirthday_dt) {
		this.subjectBirthday_dt = subjectBirthday_dt;
	}

	public String getSubjectHomePhone_s() {
		return subjectHomePhone_s;
	}

	public void setSubjectHomePhone_s(String subjectHomePhone_s) {
		this.subjectHomePhone_s = subjectHomePhone_s;
	}

	public String getSubjectAddress_s() {
		return subjectAddress_s;
	}

	public void setSubjectAddress_s(String subjectAddress_s) {
		this.subjectAddress_s = subjectAddress_s;
	}

	public String getSubjectEthnicity_s() {
		return subjectEthnicity_s;
	}

	public void setSubjectEthnicity_s(String subjectEthnicity_s) {
		this.subjectEthnicity_s = subjectEthnicity_s;
	}

	public String[] getSubjectPersonalHistory_ss() {
		return subjectPersonalHistory_ss;
	}

	public void setSubjectPersonalHistory_ss(String[] subjectPersonalHistory_ss) {
		this.subjectPersonalHistory_ss = subjectPersonalHistory_ss;
	}

	public String[] getSubjectFamilyHistory_ss() {
		return subjectFamilyHistory_ss;
	}

	public void setSubjectFamilyHistory_ss(String[] subjectFamilyHistory_ss) {
		this.subjectFamilyHistory_ss = subjectFamilyHistory_ss;
	}

	public String getSubjectSmoke_s() {
		return subjectSmoke_s;
	}

	public void setSubjectSmoke_s(String subjectSmoke_s) {
		this.subjectSmoke_s = subjectSmoke_s;
	}

	public String getSubjectDrink_s() {
		return subjectDrink_s;
	}

	public void setSubjectDrink_s(String subjectDrink_s) {
		this.subjectDrink_s = subjectDrink_s;
	}

	public String getSubjectChewingAreca_s() {
		return subjectChewingAreca_s;
	}

	public void setSubjectChewingAreca_s(String subjectChewingAreca_s) {
		this.subjectChewingAreca_s = subjectChewingAreca_s;
	}

	public String getSubjectUserId_s() {
		return subjectUserId_s;
	}

	public void setSubjectUserId_s(String subjectUserId_s) {
		this.subjectUserId_s = subjectUserId_s;
	}

	public String getSubjectUnitId_s() {
		return subjectUnitId_s;
	}

	public void setSubjectUnitId_s(String subjectUnitId_s) {
		this.subjectUnitId_s = subjectUnitId_s;
	}

	public String getSubjectUnitName_s() {
		return subjectUnitName_s;
	}

	public void setSubjectUnitName_s(String subjectUnitName_s) {
		this.subjectUnitName_s = subjectUnitName_s;
	}

	public String getSubjectTenantId_s() {
		return subjectTenantId_s;
	}

	public void setSubjectTenantId_s(String subjectTenantId_s) {
		this.subjectTenantId_s = subjectTenantId_s;
	}

	public String getSubjectCreateTime_dt() {
		return subjectCreateTime_dt;
	}

	public void setSubjectCreateTime_dt(String subjectCreateTime_dt) {
		this.subjectCreateTime_dt = subjectCreateTime_dt;
	}

	public String getSubjectUpdateTime_dt() {
		return subjectUpdateTime_dt;
	}

	public void setSubjectUpdateTime_dt(String subjectUpdateTime_dt) {
		this.subjectUpdateTime_dt = subjectUpdateTime_dt;
	}

	public TO<SolrSubject> convertToTO(TO<SolrSubject> to){
		return to.convertFrom(this);
	}
	
	@Override
	public TO<Subject> convertFrom(Subject model) {
		this.id = this.getClass().getSimpleName()+model.getSequence();
		this.subjectId_s = model.getId();
		this.subjectName_s = model.getName();
		this.subjectGender_s = model.getGender().toString();
		this.subjectBirthday_dt = Instant.ofEpochMilli(model.getBirthday().getTime())
				  						 .toString();
		this.subjectHomePhone_s = model.getHomePhone();
		this.subjectAddress_s = model.getAddress();
		this.subjectEthnicity_s = model.getEthnicity().toString();
		this.subjectPersonalHistory_ss = model.getPersonalHistory();
		this.subjectFamilyHistory_ss = model.getFamilyHistory();
		this.subjectSmoke_s = model.getSmoke().toString();
		this.subjectDrink_s = model.getDrink().toString();
		this.subjectChewingAreca_s = model.getChewingAreca().toString();
		this.subjectUserId_s = model.getUserId();
		this.subjectUnitId_s = model.getUnitId();
		this.subjectUnitName_s = model.getUnitName();
		this.subjectTenantId_s = model.getTenantId();
		this.subjectCreateTime_dt = Instant.ofEpochMilli(model.getCreateTime().getTime())
				  							.toString();
		this.subjectUpdateTime_dt = Instant.ofEpochMilli(model.getUpdateTime().getTime())
				  							.toString();
		return this;
	}

	@Override
	public Map<String, String> createFieldNameMap() {
		if(Objects.isNull(fieldNameMap)){
			fieldNameMap = new ConcurrentHashMap<>();
			fieldNameMap.put("subjectId", "subjectId_s");
			fieldNameMap.put("subjectName", "subjectName_s");
			fieldNameMap.put("gender", "subjectGender_s");
			fieldNameMap.put("birthday", "subjectBirthday_dt");
			fieldNameMap.put("homePhone", "subjectHomePhone_s");
			fieldNameMap.put("address", "subjectAddress_s");
			fieldNameMap.put("ethnicity", "subjectEthnicity_s");
			fieldNameMap.put("personalHistory", "subjectPersonalHistory_ss");
			fieldNameMap.put("familyHistory", "subjectFamilyHistory_ss");
			fieldNameMap.put("smoke", "subjectSmoke_s");
			fieldNameMap.put("drink", "subjectDrink_s");
			fieldNameMap.put("chewingAreca", "subjectChewingAreca_s");
			fieldNameMap.put("userId", "subjectUserId_s");
			fieldNameMap.put("unitId", "subjectUnitId_s");
			fieldNameMap.put("unitName", "subjectUnitName_s");
			fieldNameMap.put("tenantId", "subjectTenantId_s");
			fieldNameMap.put("createTime", "subjectCreateTime_dt");
			fieldNameMap.put("updateTime", "subjectUpdateTime_dt");
		}
		return fieldNameMap;
	}
}
