package com.syntrontech.pmo.cip.model.to;

public class AmountReportTO {
	
	private String unitName;
	/**用戶註冊人數*/
    private Long registeredNumber;
    
    /**血壓量測人數*/
    private Long maleBloodpressureMeasurementNumber;
    private Long femaleBloodpressureMeasurementNumber;
    private Long totalBloodpressureMeasurementNumber;
    
    /**血壓量測人次*/
    private Long bloodpressureMeasurementTimes;
    
    /**血壓異常人數*/
    private Long maleAbnormalBloodpressureNumber;
    private Long femaleAbnormalBloodpressureNumber;
    private Long totalAbnormalBloodpressureNumber;
    
    /**完成異常追蹤人數*/
    private Long maleAbnormalBloodpressureCloseNumber;
    private Long femaleAbnormalBloodpressureCloseNumber;
    private Long totalAbnormalBloodpressureCloseNumber;
    
    /**異常追蹤完成率*/
    private Double maleAbnormalBloodpressureCloseRate;
    private Double femaleAbnormalBloodpressureCloseRate;
    private Double totalAbnormalBloodpressureCloseRate;
    
    /**獨居老人量測人次*/
    private Long elderlylivingaloneBloodpressureMeasurementTimes;
    
    /**獨居老人量測人數*/
    private Long maleElderlylivingaloneBloodpressureMeasurementNumber; 
    private Long femaleElderlylivingaloneBloodpressureMeasurementNumber; 
    private Long totalElderlylivingaloneBloodpressureMeasurementNumber; 
    
    /**獨居老人接受服務比率*/
    private Double elderlylivingaloneBloodpressureMeasurementRate;
    
    /**生理量測服務率*/
    private Long measurementMoreThanFourTimes; // excel 不需要
    private Double bloodpressureMeasurementRate;
    
    
    /**生理量測控制良率*/
    private Double bloodpressureControlRate;
    private Long withHighBloodPressure;       // excel 不需要
    private Long bloodpressureControlNumber;  // excel 不需要

    
	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public Long getRegisteredNumber() {
		return registeredNumber;
	}

	public void setRegisteredNumber(Long registeredNumber) {
		this.registeredNumber = registeredNumber;
	}

	public Long getMaleBloodpressureMeasurementNumber() {
		return maleBloodpressureMeasurementNumber;
	}

	public void setMaleBloodpressureMeasurementNumber(Long maleBloodpressureMeasurementNumber) {
		this.maleBloodpressureMeasurementNumber = maleBloodpressureMeasurementNumber;
	}

	public Long getFemaleBloodpressureMeasurementNumber() {
		return femaleBloodpressureMeasurementNumber;
	}

	public void setFemaleBloodpressureMeasurementNumber(Long femaleBloodpressureMeasurementNumber) {
		this.femaleBloodpressureMeasurementNumber = femaleBloodpressureMeasurementNumber;
	}

	public Long getTotalBloodpressureMeasurementNumber() {
		return totalBloodpressureMeasurementNumber;
	}

	public void setTotalBloodpressureMeasurementNumber(Long totalBloodpressureMeasurementNumber) {
		this.totalBloodpressureMeasurementNumber = totalBloodpressureMeasurementNumber;
	}

	public Long getBloodpressureMeasurementTimes() {
		return bloodpressureMeasurementTimes;
	}

	public void setBloodpressureMeasurementTimes(Long bloodpressureMeasurementTimes) {
		this.bloodpressureMeasurementTimes = bloodpressureMeasurementTimes;
	}

	public Long getMaleAbnormalBloodpressureNumber() {
		return maleAbnormalBloodpressureNumber;
	}

	public void setMaleAbnormalBloodpressureNumber(Long maleAbnormalBloodpressureNumber) {
		this.maleAbnormalBloodpressureNumber = maleAbnormalBloodpressureNumber;
	}

	public Long getFemaleAbnormalBloodpressureNumber() {
		return femaleAbnormalBloodpressureNumber;
	}

	public void setFemaleAbnormalBloodpressureNumber(Long femaleAbnormalBloodpressureNumber) {
		this.femaleAbnormalBloodpressureNumber = femaleAbnormalBloodpressureNumber;
	}

	public Long getTotalAbnormalBloodpressureNumber() {
		return totalAbnormalBloodpressureNumber;
	}

	public void setTotalAbnormalBloodpressureNumber(Long totalAbnormalBloodpressureNumber) {
		this.totalAbnormalBloodpressureNumber = totalAbnormalBloodpressureNumber;
	}

	public Long getMaleAbnormalBloodpressureCloseNumber() {
		return maleAbnormalBloodpressureCloseNumber;
	}

	public void setMaleAbnormalBloodpressureCloseNumber(Long maleAbnormalBloodpressureCloseNumber) {
		this.maleAbnormalBloodpressureCloseNumber = maleAbnormalBloodpressureCloseNumber;
	}

	public Long getFemaleAbnormalBloodpressureCloseNumber() {
		return femaleAbnormalBloodpressureCloseNumber;
	}

	public void setFemaleAbnormalBloodpressureCloseNumber(Long femaleAbnormalBloodpressureCloseNumber) {
		this.femaleAbnormalBloodpressureCloseNumber = femaleAbnormalBloodpressureCloseNumber;
	}

	public Long getTotalAbnormalBloodpressureCloseNumber() {
		return totalAbnormalBloodpressureCloseNumber;
	}

	public void setTotalAbnormalBloodpressureCloseNumber(Long totalAbnormalBloodpressureCloseNumber) {
		this.totalAbnormalBloodpressureCloseNumber = totalAbnormalBloodpressureCloseNumber;
	}

	public Double getMaleAbnormalBloodpressureCloseRate() {
		return maleAbnormalBloodpressureCloseRate;
	}

	public void setMaleAbnormalBloodpressureCloseRate(Double maleAbnormalBloodpressureCloseRate) {
		this.maleAbnormalBloodpressureCloseRate = maleAbnormalBloodpressureCloseRate;
	}

	public Double getFemaleAbnormalBloodpressureCloseRate() {
		return femaleAbnormalBloodpressureCloseRate;
	}

	public void setFemaleAbnormalBloodpressureCloseRate(Double femaleAbnormalBloodpressureCloseRate) {
		this.femaleAbnormalBloodpressureCloseRate = femaleAbnormalBloodpressureCloseRate;
	}

	public Double getTotalAbnormalBloodpressureCloseRate() {
		return totalAbnormalBloodpressureCloseRate;
	}

	public void setTotalAbnormalBloodpressureCloseRate(Double totalAbnormalBloodpressureCloseRate) {
		this.totalAbnormalBloodpressureCloseRate = totalAbnormalBloodpressureCloseRate;
	}

	public Double getBloodpressureControlRate() {
		return bloodpressureControlRate;
	}

	public void setBloodpressureControlRate(Double bloodpressureControlRate) {
		this.bloodpressureControlRate = bloodpressureControlRate;
	}

	public Long getWithHighBloodPressure() {
		return withHighBloodPressure;
	}

	public void setWithHighBloodPressure(Long withHighBloodPressure) {
		this.withHighBloodPressure = withHighBloodPressure;
	}

	public Long getBloodpressureControlNumber() {
		return bloodpressureControlNumber;
	}

	public void setBloodpressureControlNumber(Long bloodpressureControlNumber) {
		this.bloodpressureControlNumber = bloodpressureControlNumber;
	}

	public Long getElderlylivingaloneBloodpressureMeasurementTimes() {
		return elderlylivingaloneBloodpressureMeasurementTimes;
	}

	public void setElderlylivingaloneBloodpressureMeasurementTimes(Long elderlylivingaloneBloodpressureMeasurementTimes) {
		this.elderlylivingaloneBloodpressureMeasurementTimes = elderlylivingaloneBloodpressureMeasurementTimes;
	}

	public Long getMaleElderlylivingaloneBloodpressureMeasurementNumber() {
		return maleElderlylivingaloneBloodpressureMeasurementNumber;
	}

	public void setMaleElderlylivingaloneBloodpressureMeasurementNumber(
			Long maleElderlylivingaloneBloodpressureMeasurementNumber) {
		this.maleElderlylivingaloneBloodpressureMeasurementNumber = maleElderlylivingaloneBloodpressureMeasurementNumber;
	}

	public Long getFemaleElderlylivingaloneBloodpressureMeasurementNumber() {
		return femaleElderlylivingaloneBloodpressureMeasurementNumber;
	}

	public void setFemaleElderlylivingaloneBloodpressureMeasurementNumber(
			Long femaleElderlylivingaloneBloodpressureMeasurementNumber) {
		this.femaleElderlylivingaloneBloodpressureMeasurementNumber = femaleElderlylivingaloneBloodpressureMeasurementNumber;
	}

	public Long getTotalElderlylivingaloneBloodpressureMeasurementNumber() {
		return totalElderlylivingaloneBloodpressureMeasurementNumber;
	}

	public void setTotalElderlylivingaloneBloodpressureMeasurementNumber(
			Long totalElderlylivingaloneBloodpressureMeasurementNumber) {
		this.totalElderlylivingaloneBloodpressureMeasurementNumber = totalElderlylivingaloneBloodpressureMeasurementNumber;
	}

	public Double getElderlylivingaloneBloodpressureMeasurementRate() {
		return elderlylivingaloneBloodpressureMeasurementRate;
	}

	public void setElderlylivingaloneBloodpressureMeasurementRate(Double elderlylivingaloneBloodpressureMeasurementRate) {
		this.elderlylivingaloneBloodpressureMeasurementRate = elderlylivingaloneBloodpressureMeasurementRate;
	}

	public Long getMeasurementMoreThanFourTimes() {
		return measurementMoreThanFourTimes;
	}

	public void setMeasurementMoreThanFourTimes(Long measurementMoreThanFourTimes) {
		this.measurementMoreThanFourTimes = measurementMoreThanFourTimes;
	}

	public Double getBloodpressureMeasurementRate() {
		return bloodpressureMeasurementRate;
	}

	public void setBloodpressureMeasurementRate(Double bloodpressureMeasurementRate) {
		this.bloodpressureMeasurementRate = bloodpressureMeasurementRate;
	}
    
    
}
