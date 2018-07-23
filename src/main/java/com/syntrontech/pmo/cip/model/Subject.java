package com.syntrontech.pmo.cip.model;


import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.syntrontech.pmo.model.common.ChewingArecaType;
import com.syntrontech.pmo.model.common.DrinkType;
import com.syntrontech.pmo.model.common.EthnicityType;
import com.syntrontech.pmo.model.common.FamilyHistoryType;
import com.syntrontech.pmo.model.common.GenderType;
import com.syntrontech.pmo.model.common.ModelStatus;
import com.syntrontech.pmo.model.common.PersonalHistoryType;
import com.syntrontech.pmo.model.common.SmokeType;

@Table
@Entity(name = "subject")
public class Subject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sequence")
	private Long sequence;
	
	@Column(name = "id", nullable = false)
	private String id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "gender", nullable = false)
	@Enumerated(EnumType.STRING)
	private GenderType gender;
	
	@Column(name = "birthday", nullable = false)
	private Date birthday;
	
	@Column(name = "home_phone")
	private String homePhone;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "ethnicity")
	@Enumerated(EnumType.STRING)
	private EthnicityType ethnicity;
	
	@Column(name = "personal_history")
//	@Type(type = "com.syntrontech.cip.model.common.StringArrayType")
	private String[] personalHistory;
	
	@Column(name = "family_history")
//	@Type(type = "com.syntrontech.cip.model.common.StringArrayType")
	private String[] familyHistory;
	
	@Column(name = "smoke")
	@Enumerated(EnumType.STRING)
	private SmokeType smoke;
	
	@Column(name = "drink")
	@Enumerated(EnumType.STRING)
	private DrinkType drink;
	
	@Column(name = "chewing_areca")
	@Enumerated(EnumType.STRING)
	private ChewingArecaType chewingAreca;
	
	@Column(name = "user_id", nullable = false)
	private String userId;
	
	@Column(name = "unit_id")
	private String unitId;
	
	@Column(name = "unit_name")
	private String unitName;

	@Column(name = "tenant_id", nullable = false)
	private String tenantId;
	
	@Column(name = "createtime", nullable = false)
	private Date createTime;
	
	@Column(name = "createby", nullable = false)
	private String createBy;
	
	@Column(name = "updatetime", nullable = false)
	private Date updateTime;
	
	@Column(name = "updateby", nullable = false)
	private String updateBy;
	
	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private ModelStatus status;

	@Override
	public String toString() {
		return "Subject:[" +
				"id=" + id + ", " +
				"userId=" + userId + ", " +
				"unitId=" + unitId + ", " +
				"tenantId=" + tenantId + ", " +
				"createTime=" + createTime + ", " +
				"createBy=" + createBy + ", " +
				"status=" + status + " " +
				"]";
	}

	public Long getSequence() {
		return sequence;
	}

	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GenderType getGender() {
		return gender;
	}

	public void setGender(GenderType gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public EthnicityType getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(EthnicityType ethnicity) {
		this.ethnicity = ethnicity;
	}

	public String[] getPersonalHistory() {
		return personalHistory;
	}

	public void setPersonalHistory(List<PersonalHistoryType> personalHistory) {
		this.personalHistory = personalHistory.stream()
											  .map(p -> p.toString())
											  .toArray(String[]::new);
	}

	public String[] getFamilyHistory() {
		return familyHistory;
	}

	public void setFamilyHistory(List<FamilyHistoryType> familyHistory) {
		this.familyHistory = familyHistory.stream()
										  .map(p -> p.toString())
										  .toArray(String[]::new);
	}

	public SmokeType getSmoke() {
		return smoke;
	}

	public void setSmoke(SmokeType smoke) {
		this.smoke = smoke;
	}

	public DrinkType getDrink() {
		return drink;
	}

	public void setDrink(DrinkType drink) {
		this.drink = drink;
	}

	public ChewingArecaType getChewingAreca() {
		return chewingAreca;
	}

	public void setChewingAreca(ChewingArecaType chewingAreca) {
		this.chewingAreca = chewingAreca;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public ModelStatus getStatus() {
		return status;
	}

	public void setStatus(ModelStatus status) {
		this.status = status;
	}
	
}
