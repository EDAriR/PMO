package com.syntrontech.pmo.mv.measurement.solr.model;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.syntrontech.measurement.model.Biochemistry;
import com.syntrontech.measurement.restful.to.TO;

public class SolrBiochemistry extends SolrMeasurement implements TO<Biochemistry>, SolrDoc{
	private static Map<String, String> fieldNameMap = new ConcurrentHashMap<>();

	private String id;
	private String value_s;
	private Long groupId_l;
	private Long mappingsSeq_l;
	private String mappingsProject_s;

	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue_s() {
		return value_s;
	}

	public void setValue_s(String value_s) {
		this.value_s = value_s;
	}

	public Long getGroupId_l() {
		return groupId_l;
	}

	public void setGroupId_l(Long groupId_l) {
		this.groupId_l = groupId_l;
	}

	public Long getMappingsSeq_l() {
		return mappingsSeq_l;
	}

	public void setMappingsSeq_l(Long mappingsSeq_l) {
		this.mappingsSeq_l = mappingsSeq_l;
	}

	public String getMappingsProject_s() {
		return mappingsProject_s;
	}

	public void setMappingsProject_s(String mappingsProject_s) {
		this.mappingsProject_s = mappingsProject_s;
	}

	@Override
	public TO<Biochemistry> convertFrom(Biochemistry model) {
		this.value_s = model.getValue();
		this.groupId_l = model.getGroupId();
		this.mappingsSeq_l = model.getMappingsSeq();
		this.mappingsProject_s = model.getMappingsProject().toString();

		this.sequence_l = model.getSequence();
		this.id = this.getClass().getSimpleName() + model.getSequence();
		this.recordTime_dt = Instant.ofEpochMilli(model.getRecordTime().getTime()).toString();
		this.createBy_s = model.getCreateBy();
		this.tenantId_s = model.getTenantId();
		this.subjectId_s = model.getSubjectId();
		this.subjectName_s=model.getSubjectName();
		this.subjectAge_l = model.getSubjectAge().longValue();
		this.subjectGender_s = model.getSubjectGender().toString();
		this.ruleDescription_s = model.getRuleDescription();
		this.status_s = model.getStatus().toString();
		this.subjectSeq_l = model.getSubjectSeq();
		this.subjectUserId_s = model.getSubjectUserId();
		this.macAddress_s=model.getDeviceMacAddress();
		
		this.unitId_s = model.getUnitId();
		this.unitName_s=model.getUnitName();
		this.parentUnitId_s=model.getParentUnitId();
		this.parentUnitName_s=model.getParentUnitName();
		
		this.deviceId_s=model.getDeviceId();
		return this;
	}
	
	@Override
	public Map<String, String> createFieldNameMap() {
		if (fieldNameMap.isEmpty()) {
			fieldNameMap.put("value", "value_s");
			fieldNameMap.put("groupId", "groupId_l");
			fieldNameMap.put("mappingsSeq", "mappingsSeq_l");
			fieldNameMap.put("mappingsProject", "mappingsProject_s");
			fieldNameMap.putAll(findCommonFieldNameMap());
		}
		return fieldNameMap;
	}
}
