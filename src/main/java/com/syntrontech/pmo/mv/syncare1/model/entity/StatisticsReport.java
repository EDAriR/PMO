package com.syntrontech.pmo.mv.syncare1.model.entity;

/**
 * Created by Tanya on 2017/4/19.
 */
public class StatisticsReport extends CommonReport{

    private Integer totalCount;
    private Integer malePeopleCount;
    private Integer femalePeopleCount;
    private Integer totalPeopleCount;
    private Integer totalTimes;
    
    private Integer maleMeasureHighBloodPressure;
    private Integer femaleMeasureHighBloodPressure;
    private Integer totalMeasureHighBloodPressure;
    
    private Integer maleHighBloodPressureFinish;
    private Integer femaleHighBloodPressureFinish;
    private Integer totalHighBloodPressureFinish;
    
    private Double maleHighBloodPressureFinishRate;
    private Double femaleHighBloodPressureFinishRate;
    private Double totalHighBloodPressureFinishRate;
    
    private Double physiologicalControlRate;
    
    private Integer maleReclusePeopleCount;
    private Integer femaleReclusePeopleCount;
    private Integer totalReclusePeopleCount;
    
    private Integer recluseCount;
    
    private Double recluseServeRate;
    
    private Integer measurementMoreThanFourTimes;
    private Double physiologicalRate;
    
    private Integer highBloodPressureControl;
    private Integer withHighBloodPressure;
    
    
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getMalePeopleCount() {
		return malePeopleCount;
	}
	public void setMalePeopleCount(Integer malePeopleCount) {
		this.malePeopleCount = malePeopleCount;
	}
	public Integer getFemalePeopleCount() {
		return femalePeopleCount;
	}
	public void setFemalePeopleCount(Integer femalePeopleCount) {
		this.femalePeopleCount = femalePeopleCount;
	}
	public Integer getTotalPeopleCount() {
		return totalPeopleCount;
	}
	public void setTotalPeopleCount(Integer totalPeopleCount) {
		this.totalPeopleCount = totalPeopleCount;
	}
	public Integer getTotalTimes() {
		return totalTimes;
	}
	public void setTotalTimes(Integer totalTimes) {
		this.totalTimes = totalTimes;
	}
	public Integer getMaleMeasureHighBloodPressure() {
		return maleMeasureHighBloodPressure;
	}
	public void setMaleMeasureHighBloodPressure(Integer maleMeasureHighBloodPressure) {
		this.maleMeasureHighBloodPressure = maleMeasureHighBloodPressure;
	}
	public Integer getFemaleMeasureHighBloodPressure() {
		return femaleMeasureHighBloodPressure;
	}
	public void setFemaleMeasureHighBloodPressure(Integer femaleMeasureHighBloodPressure) {
		this.femaleMeasureHighBloodPressure = femaleMeasureHighBloodPressure;
	}
	public Integer getTotalMeasureHighBloodPressure() {
		return totalMeasureHighBloodPressure;
	}
	public void setTotalMeasureHighBloodPressure(Integer totalMeasureHighBloodPressure) {
		this.totalMeasureHighBloodPressure = totalMeasureHighBloodPressure;
	}
	public Integer getMaleHighBloodPressureFinish() {
		return maleHighBloodPressureFinish;
	}
	public void setMaleHighBloodPressureFinish(Integer maleHighBloodPressureFinish) {
		this.maleHighBloodPressureFinish = maleHighBloodPressureFinish;
	}
	public Integer getFemaleHighBloodPressureFinish() {
		return femaleHighBloodPressureFinish;
	}
	public void setFemaleHighBloodPressureFinish(Integer femaleHighBloodPressureFinish) {
		this.femaleHighBloodPressureFinish = femaleHighBloodPressureFinish;
	}
	public Integer getTotalHighBloodPressureFinish() {
		return totalHighBloodPressureFinish;
	}
	public void setTotalHighBloodPressureFinish(Integer totalHighBloodPressureFinish) {
		this.totalHighBloodPressureFinish = totalHighBloodPressureFinish;
	}
	
	public Double getMaleHighBloodPressureFinishRate() {
		return maleHighBloodPressureFinishRate;
	}
	public void setMaleHighBloodPressureFinishRate(Double maleHighBloodPressureFinishRate) {
		this.maleHighBloodPressureFinishRate = maleHighBloodPressureFinishRate;
	}
	public Double getFemaleHighBloodPressureFinishRate() {
		return femaleHighBloodPressureFinishRate;
	}
	public void setFemaleHighBloodPressureFinishRate(Double femaleHighBloodPressureFinishRate) {
		this.femaleHighBloodPressureFinishRate = femaleHighBloodPressureFinishRate;
	}
	public Double getTotalHighBloodPressureFinishRate() {
		return totalHighBloodPressureFinishRate;
	}
	public void setTotalHighBloodPressureFinishRate(Double totalHighBloodPressureFinishRate) {
		this.totalHighBloodPressureFinishRate = totalHighBloodPressureFinishRate;
	}
	public Double getPhysiologicalControlRate() {
		return physiologicalControlRate;
	}
	public void setPhysiologicalControlRate(Double physiologicalControlRate) {
		this.physiologicalControlRate = physiologicalControlRate;
	}
	public Integer getMaleReclusePeopleCount() {
		return maleReclusePeopleCount;
	}
	public void setMaleReclusePeopleCount(Integer maleReclusePeopleCount) {
		this.maleReclusePeopleCount = maleReclusePeopleCount;
	}
	public Integer getFemaleReclusePeopleCount() {
		return femaleReclusePeopleCount;
	}
	public void setFemaleReclusePeopleCount(Integer femaleReclusePeopleCount) {
		this.femaleReclusePeopleCount = femaleReclusePeopleCount;
	}
	public Integer getTotalReclusePeopleCount() {
		return totalReclusePeopleCount;
	}
	public void setTotalReclusePeopleCount(Integer totalReclusePeopleCount) {
		this.totalReclusePeopleCount = totalReclusePeopleCount;
	}
	public Integer getRecluseCount() {
		return recluseCount;
	}
	public void setRecluseCount(Integer recluseCount) {
		this.recluseCount = recluseCount;
	}
	public Double getRecluseServeRate() {
		return recluseServeRate;
	}
	public void setRecluseServeRate(Double recluseServeRate) {
		this.recluseServeRate = recluseServeRate;
	}
	public Integer getMeasurementMoreThanFourTimes() {
		return measurementMoreThanFourTimes;
	}
	public void setMeasurementMoreThanFourTimes(Integer measurementMoreThanFourTimes) {
		this.measurementMoreThanFourTimes = measurementMoreThanFourTimes;
	}
	public Double getPhysiologicalRate() {
		return physiologicalRate;
	}
	public void setPhysiologicalRate(Double physiologicalRate) {
		this.physiologicalRate = physiologicalRate;
	}
	public Integer getHighBloodPressureControl() {
		return highBloodPressureControl;
	}
	public void setHighBloodPressureControl(Integer highBloodPressureControl) {
		this.highBloodPressureControl = highBloodPressureControl;
	}
	public Integer getWithHighBloodPressure() {
		return withHighBloodPressure;
	}
	public void setWithHighBloodPressure(Integer withHighBloodPressure) {
		this.withHighBloodPressure = withHighBloodPressure;
	}


    

}
