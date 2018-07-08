package com.syntrontech.pmo.cip.model.solr;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;



public class SolrHrv extends SolrMeasurement {
	
	private static Map<String, String> fieldNameMap = new ConcurrentHashMap<>();
	
	private String id;

	private String hrvId_s;
	
	private String subjectId_s;
	private String subjectName_s;
	private String subjectGender_s;
	private String userId_s;
	private String userName_s;
	private String unitId_s;
	private String unitName_s;
	private String latitude_s;
	private String longitude_s;
	private String macAddress_s;
	private String deviceId_s;
    private String parentUnitId_s;
    private String parentUnitName_s;
    private String tenantId_s;
    private String createTime_dt;
    private String recordTime_dt;
    
	private Double rrInterval_d;
	private Double sdnn_d;
	private Double rmssd_d;
	private Double nn50_d;
	private Double pnn50_d;
	private Double sd1_d;
	private Double sd2_d;
	private Double sd1Sd2_d;
	private Double fftVlf_d;
	private Double fftLf_d;
	private Double fftHf__d;
	private Double fftTp__d;
	private Double fftNLf_d;
	private Double fftNHf_d;
	private Double fftLfHf_d;
	private Double arVlf_d;
	private Double arLf_d;
	private Double arHf_d;
	private Double arTp_d;
	private Double arNLf_d;
	private Double arNHf_d;
	private Double arLfHf_d;
	
	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public Map<String, String> createFieldNameMap() {
		return createFieldNameMapIfNotExist();
	}
	
	protected Map<String, String> createFieldNameMapIfNotExist(){
		if(fieldNameMap.isEmpty()){
			fieldNameMap.put("hrv", "hrvId_s");
			fieldNameMap.put("subjectId", "subjectId_s");
			fieldNameMap.put("subjectName", "subjectName_s");
			fieldNameMap.put("subjectGender", "subjectGender_s");
			fieldNameMap.put("userId", "userId_s");
			fieldNameMap.put("userName", "userName_s");
			fieldNameMap.put("unitId", "unitId_s");
			fieldNameMap.put("unitName", "unitName_s");
			fieldNameMap.put("latitude", "latitude_s");
			fieldNameMap.put("longitude", "longitude_s");
			fieldNameMap.put("macAddress", "macAddress_s");
			fieldNameMap.put("parentUnitId", "parentUnitId_s");
			fieldNameMap.put("parentUnitName", "parentUnitName_s");
			fieldNameMap.put("tenantId", "tenantId_s");
			fieldNameMap.put("createTime", "recordTime_dt");
			fieldNameMap.put("recordTime", "recordTime_dt");
			
			fieldNameMap.put("rrInterval", "rrInterval_d");
			fieldNameMap.put("sdnn", "sdnn_d");
			fieldNameMap.put("rmssd", "rmssd_d");
			fieldNameMap.put("nn50", "nn50_d");
			fieldNameMap.put("pnn50", "pnn50_d");
			fieldNameMap.put("sd1", "sd1_d");
			fieldNameMap.put("sd2", "sd2_d");
			fieldNameMap.put("sd1Sd2", "sd1Sd2_d");
			
			fieldNameMap.put("fftVlf", "fftVlf_d");
			fieldNameMap.put("fftLf", "fftLf_d");
			fieldNameMap.put("fftHf", "fftHf_d");
			fieldNameMap.put("fftTp", "fftTp_d");
			fieldNameMap.put("fftNLf", "fftNLf_d");
			fieldNameMap.put("fftNHf", "fftNHf_d");
			fieldNameMap.put("fftLfHf", "fftLfHf_d");
			
			fieldNameMap.put("arVlf", "arVlf_d");
			fieldNameMap.put("arLf", "arLf_d");
			fieldNameMap.put("arHf", "arHf_d");
			fieldNameMap.put("arTp", "arTp_d");
			fieldNameMap.put("arNLf", "arNLf_d");
			fieldNameMap.put("arNHf", "arNHf_d");
			fieldNameMap.put("arLfHf", "arLfHf_d");
		}
		return fieldNameMap;
	}
	
	/**
	 * @return the hrvId_s
	 */
	public String getHrvId_s() {
		return hrvId_s;
	}

	/**
	 * @param hrvId_s the hrvId_s to set
	 */
	public void setHrvId_s(String hrvId_s) {
		this.hrvId_s = hrvId_s;
	}

	/**
	 * @return the subjectId_s
	 */
	public String getSubjectId_s() {
		return subjectId_s;
	}

	/**
	 * @param subjectId_s the subjectId_s to set
	 */
	public void setSubjectId_s(String subjectId_s) {
		this.subjectId_s = subjectId_s;
	}

	/**
	 * @return the subjectName_s
	 */
	public String getSubjectName_s() {
		return subjectName_s;
	}

	/**
	 * @param subjectName_s the subjectName_s to set
	 */
	public void setSubjectName_s(String subjectName_s) {
		this.subjectName_s = subjectName_s;
	}

	/**
	 * @return the subjectGender_s
	 */
	public String getSubjectGender_s() {
		return subjectGender_s;
	}

	/**
	 * @param subjectGender_s the subjectGender_s to set
	 */
	public void setSubjectGender_s(String subjectGender_s) {
		this.subjectGender_s = subjectGender_s;
	}

	/**
	 * @return the userId_s
	 */
	public String getUserId_s() {
		return userId_s;
	}

	/**
	 * @param userId_s the userId_s to set
	 */
	public void setUserId_s(String userId_s) {
		this.userId_s = userId_s;
	}

	/**
	 * @return the userName_s
	 */
	public String getUserName_s() {
		return userName_s;
	}

	/**
	 * @param userName_s the userName_s to set
	 */
	public void setUserName_s(String userName_s) {
		this.userName_s = userName_s;
	}

	/**
	 * @return the unitId_s
	 */
	public String getUnitId_s() {
		return unitId_s;
	}

	/**
	 * @param unitId_s the unitId_s to set
	 */
	public void setUnitId_s(String unitId_s) {
		this.unitId_s = unitId_s;
	}

	/**
	 * @return the unitName_s
	 */
	public String getUnitName_s() {
		return unitName_s;
	}

	/**
	 * @param unitName_s the unitName_s to set
	 */
	public void setUnitName_s(String unitName_s) {
		this.unitName_s = unitName_s;
	}

	/**
	 * @return the latitude_s
	 */
	public String getLatitude_s() {
		return latitude_s;
	}

	/**
	 * @param latitude_s the latitude_s to set
	 */
	public void setLatitude_s(String latitude_s) {
		this.latitude_s = latitude_s;
	}

	/**
	 * @return the longitude_s
	 */
	public String getLongitude_s() {
		return longitude_s;
	}

	/**
	 * @param longitude_s the longitude_s to set
	 */
	public void setLongitude_s(String longitude_s) {
		this.longitude_s = longitude_s;
	}

	/**
	 * @return the macAddress_s
	 */
	public String getMacAddress_s() {
		return macAddress_s;
	}

	/**
	 * @param macAddress_s the macAddress_s to set
	 */
	public void setMacAddress_s(String macAddress_s) {
		this.macAddress_s = macAddress_s;
	}

	/**
	 * @return the deviceId_s
	 */
	public String getDeviceId_s() {
		return deviceId_s;
	}

	/**
	 * @param deviceId_s the deviceId_s to set
	 */
	public void setDeviceId_s(String deviceId_s) {
		this.deviceId_s = deviceId_s;
	}

	/**
	 * @return the parentUnitId_s
	 */
	public String getParentUnitId_s() {
		return parentUnitId_s;
	}

	/**
	 * @param parentUnitId_s the parentUnitId_s to set
	 */
	public void setParentUnitId_s(String parentUnitId_s) {
		this.parentUnitId_s = parentUnitId_s;
	}

	/**
	 * @return the parentUnitName_s
	 */
	public String getParentUnitName_s() {
		return parentUnitName_s;
	}

	/**
	 * @param parentUnitName_s the parentUnitName_s to set
	 */
	public void setParentUnitName_s(String parentUnitName_s) {
		this.parentUnitName_s = parentUnitName_s;
	}

	/**
	 * @return the tenantId_s
	 */
	public String getTenantId_s() {
		return tenantId_s;
	}

	/**
	 * @param tenantId_s the tenantId_s to set
	 */
	public void setTenantId_s(String tenantId_s) {
		this.tenantId_s = tenantId_s;
	}

	/**
	 * @return the createTime_dt
	 */
	public String getCreateTime_dt() {
		return createTime_dt;
	}

	/**
	 * @param createTime_dt the createTime_dt to set
	 */
	public void setCreateTime_dt(String createTime_dt) {
		this.createTime_dt = createTime_dt;
	}

	/**
	 * @return the recordTime_dt
	 */
	public String getRecordTime_dt() {
		return recordTime_dt;
	}

	/**
	 * @param recordTime_dt the recordTime_dt to set
	 */
	public void setRecordTime_dt(String recordTime_dt) {
		this.recordTime_dt = recordTime_dt;
	}

	/**
	 * @return the rrInterval_d
	 */
	public Double getRrInterval_d() {
		return rrInterval_d;
	}

	/**
	 * @param rrInterval_d the rrInterval_d to set
	 */
	public void setRrInterval_d(Double rrInterval_d) {
		this.rrInterval_d = rrInterval_d;
	}

	/**
	 * @return the sdnn_d
	 */
	public Double getSdnn_d() {
		return sdnn_d;
	}

	/**
	 * @param sdnn_d the sdnn_d to set
	 */
	public void setSdnn_d(Double sdnn_d) {
		this.sdnn_d = sdnn_d;
	}

	/**
	 * @return the rmssd_d
	 */
	public Double getRmssd_d() {
		return rmssd_d;
	}

	/**
	 * @param rmssd_d the rmssd_d to set
	 */
	public void setRmssd_d(Double rmssd_d) {
		this.rmssd_d = rmssd_d;
	}

	/**
	 * @return the nn50_d
	 */
	public Double getNn50_d() {
		return nn50_d;
	}

	/**
	 * @param nn50_d the nn50_d to set
	 */
	public void setNn50_d(Double nn50_d) {
		this.nn50_d = nn50_d;
	}

	/**
	 * @return the pnn50_d
	 */
	public Double getPnn50_d() {
		return pnn50_d;
	}

	/**
	 * @param pnn50_d the pnn50_d to set
	 */
	public void setPnn50_d(Double pnn50_d) {
		this.pnn50_d = pnn50_d;
	}

	/**
	 * @return the sd1_d
	 */
	public Double getSd1_d() {
		return sd1_d;
	}

	/**
	 * @param sd1_d the sd1_d to set
	 */
	public void setSd1_d(Double sd1_d) {
		this.sd1_d = sd1_d;
	}

	/**
	 * @return the sd2_d
	 */
	public Double getSd2_d() {
		return sd2_d;
	}

	/**
	 * @param sd2_d the sd2_d to set
	 */
	public void setSd2_d(Double sd2_d) {
		this.sd2_d = sd2_d;
	}

	/**
	 * @return the sd1Sd2_d
	 */
	public Double getSd1Sd2_d() {
		return sd1Sd2_d;
	}

	/**
	 * @param sd1Sd2_d the sd1Sd2_d to set
	 */
	public void setSd1Sd2_d(Double sd1Sd2_d) {
		this.sd1Sd2_d = sd1Sd2_d;
	}

	/**
	 * @return the fftVlf_d
	 */
	public Double getFftVlf_d() {
		return fftVlf_d;
	}

	/**
	 * @param fftVlf_d the fftVlf_d to set
	 */
	public void setFftVlf_d(Double fftVlf_d) {
		this.fftVlf_d = fftVlf_d;
	}

	/**
	 * @return the fftLf_d
	 */
	public Double getFftLf_d() {
		return fftLf_d;
	}

	/**
	 * @param fftLf_d the fftLf_d to set
	 */
	public void setFftLf_d(Double fftLf_d) {
		this.fftLf_d = fftLf_d;
	}

	/**
	 * @return the fftHf__d
	 */
	public Double getFftHf__d() {
		return fftHf__d;
	}

	/**
	 * @param fftHf__d the fftHf__d to set
	 */
	public void setFftHf__d(Double fftHf__d) {
		this.fftHf__d = fftHf__d;
	}

	/**
	 * @return the fftTp__d
	 */
	public Double getFftTp__d() {
		return fftTp__d;
	}

	/**
	 * @param fftTp__d the fftTp__d to set
	 */
	public void setFftTp__d(Double fftTp__d) {
		this.fftTp__d = fftTp__d;
	}

	/**
	 * @return the fftNLf_d
	 */
	public Double getFftNLf_d() {
		return fftNLf_d;
	}

	/**
	 * @param fftNLf_d the fftNLf_d to set
	 */
	public void setFftNLf_d(Double fftNLf_d) {
		this.fftNLf_d = fftNLf_d;
	}

	/**
	 * @return the fftNHf_d
	 */
	public Double getFftNHf_d() {
		return fftNHf_d;
	}

	/**
	 * @param fftNHf_d the fftNHf_d to set
	 */
	public void setFftNHf_d(Double fftNHf_d) {
		this.fftNHf_d = fftNHf_d;
	}

	/**
	 * @return the fftLfHf_d
	 */
	public Double getFftLfHf_d() {
		return fftLfHf_d;
	}

	/**
	 * @param fftLfHf_d the fftLfHf_d to set
	 */
	public void setFftLfHf_d(Double fftLfHf_d) {
		this.fftLfHf_d = fftLfHf_d;
	}

	/**
	 * @return the arVlf_d
	 */
	public Double getArVlf_d() {
		return arVlf_d;
	}

	/**
	 * @param arVlf_d the arVlf_d to set
	 */
	public void setArVlf_d(Double arVlf_d) {
		this.arVlf_d = arVlf_d;
	}

	/**
	 * @return the arLf_d
	 */
	public Double getArLf_d() {
		return arLf_d;
	}

	/**
	 * @param arLf_d the arLf_d to set
	 */
	public void setArLf_d(Double arLf_d) {
		this.arLf_d = arLf_d;
	}

	/**
	 * @return the arHf_d
	 */
	public Double getArHf_d() {
		return arHf_d;
	}

	/**
	 * @param arHf_d the arHf_d to set
	 */
	public void setArHf_d(Double arHf_d) {
		this.arHf_d = arHf_d;
	}

	/**
	 * @return the arTp_d
	 */
	public Double getArTp_d() {
		return arTp_d;
	}

	/**
	 * @param arTp_d the arTp_d to set
	 */
	public void setArTp_d(Double arTp_d) {
		this.arTp_d = arTp_d;
	}

	/**
	 * @return the arNLf_d
	 */
	public Double getArNLf_d() {
		return arNLf_d;
	}

	/**
	 * @param arNLf_d the arNLf_d to set
	 */
	public void setArNLf_d(Double arNLf_d) {
		this.arNLf_d = arNLf_d;
	}

	/**
	 * @return the arNHf_d
	 */
	public Double getArNHf_d() {
		return arNHf_d;
	}

	/**
	 * @param arNHf_d the arNHf_d to set
	 */
	public void setArNHf_d(Double arNHf_d) {
		this.arNHf_d = arNHf_d;
	}

	/**
	 * @return the arLfHf_d
	 */
	public Double getArLfHf_d() {
		return arLfHf_d;
	}

	/**
	 * @param arLfHf_d the arLfHf_d to set
	 */
	public void setArLfHf_d(Double arLfHf_d) {
		this.arLfHf_d = arLfHf_d;
	}
	
}
