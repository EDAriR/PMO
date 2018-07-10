package com.syntrontech.pmo.cip.model.to;

public class AmountByAgeReportTO {
	
	private String unitName;
	//	30 歲以下血壓量測人數 不包含 30
	private Integer under30Number;
	private Integer under30AbnormalBloodPressureNumber;
	//	30 歲以下血壓量測人數率%
	private Double under30Rate;
	private Double under30AbnormalBloodPressureRate;
	//	30_39 歲血壓量測人數
	private Integer year30To39Number;
	private Integer year30To39AbnormalBloodPressureNumber;
	//	30_39 歲血壓量測人數率%
	private Double year30To39Rate;
	private Double year30To39AbnormalBloodPressureRate;
	//	40_49 歲血壓量測人數
	private Integer year40To49Number;
	private Integer year40To49AbnormalBloodPressureNumber;
	//	40_49 歲血壓量測人數率%
	private Double year40To49Rate;
	private Double year40To49AbnormalBloodPressureRate;
	//	50_59 歲血壓量測人數
	private Integer year50To59Number;
	private Integer year50To59AbnormalBloodPressureNumber;
	//	50_59 歲血壓量測人數率%
	private Double year50To59Rate;
	private Double year50To59AbnormalBloodPressureRate;
	//	60_69 歲血壓量測人數
	private Integer year60To69Number;
	private Integer year60To69AbnormalBloodPressureNumber;
	//	60_69 歲血壓量測人數率%
	private Double year60To69Rate;
	private Double year60To69AbnormalBloodPressureRate;
	//	70 歲以上血壓量測人數 包含 70
	private Integer over70Number;
	private Integer over70AbnormalBloodPressureNumber;
	//	70 歲以上血壓量測人數率%
	private Double over70Rate;
	private Double over70AbnormalBloodPressureRate;
	//	65 歲以上血壓量測人數
	private Integer over65Number;
	private Integer over65AbnormalBloodPressureNumber;
	//	65 歲以上血壓量測人數率% 
	private Double over65Rate;
	private Double over65AbnormalBloodPressureRate;
	//	 65_74 歲獨居老人量測人數
	private Integer year65To74Number;
	//	 65_74 歲獨居老人量測人數率
	private Double year65To74Rate;
	//	 75_84 歲獨居老人量測人數
	private Integer year75To84Number;
	//	 75_84 歲獨居老人量測人數率
	private Double year75To84Rate;
	//	 85 歲以上獨居老人量測人數
	private Integer over85Number;
	//	 85 歲以上獨居老人量測人數率
	private Double over85Rate;
	
	public String getUnitName() {
		return unitName;
	}

	public Integer getUnder30Number() {
		return under30Number;
	}

	public void setUnder30Number(Integer under30Number) {
		this.under30Number = under30Number;
	}

	public Integer getUnder30AbnormalBloodPressureNumber() {
		return under30AbnormalBloodPressureNumber;
	}

	public void setUnder30AbnormalBloodPressureNumber(Integer under30AbnormalBloodPressureNumber) {
		this.under30AbnormalBloodPressureNumber = under30AbnormalBloodPressureNumber;
	}

	public Double getUnder30Rate() {
		return under30Rate;
	}

	public void setUnder30Rate(Double under30Rate) {
		this.under30Rate = under30Rate;
	}

	public Double getUnder30AbnormalBloodPressureRate() {
		return under30AbnormalBloodPressureRate;
	}

	public void setUnder30AbnormalBloodPressureRate(Double under30AbnormalBloodPressureRate) {
		this.under30AbnormalBloodPressureRate = under30AbnormalBloodPressureRate;
	}

	public Integer getYear30To39Number() {
		return year30To39Number;
	}

	public void setYear30To39Number(Integer year30To39Number) {
		this.year30To39Number = year30To39Number;
	}

	public Integer getYear30To39AbnormalBloodPressureNumber() {
		return year30To39AbnormalBloodPressureNumber;
	}

	public void setYear30To39AbnormalBloodPressureNumber(Integer year30To39AbnormalBloodPressureNumber) {
		this.year30To39AbnormalBloodPressureNumber = year30To39AbnormalBloodPressureNumber;
	}

	public Double getYear30To39Rate() {
		return year30To39Rate;
	}

	public void setYear30To39Rate(Double year30To39Rate) {
		this.year30To39Rate = year30To39Rate;
	}

	public Double getYear30To39AbnormalBloodPressureRate() {
		return year30To39AbnormalBloodPressureRate;
	}

	public void setYear30To39AbnormalBloodPressureRate(Double year30To39AbnormalBloodPressureRate) {
		this.year30To39AbnormalBloodPressureRate = year30To39AbnormalBloodPressureRate;
	}

	public Integer getYear40To49Number() {
		return year40To49Number;
	}

	public void setYear40To49Number(Integer year40To49Number) {
		this.year40To49Number = year40To49Number;
	}

	public Integer getYear40To49AbnormalBloodPressureNumber() {
		return year40To49AbnormalBloodPressureNumber;
	}

	public void setYear40To49AbnormalBloodPressureNumber(Integer year40To49AbnormalBloodPressureNumber) {
		this.year40To49AbnormalBloodPressureNumber = year40To49AbnormalBloodPressureNumber;
	}

	public Double getYear40To49Rate() {
		return year40To49Rate;
	}

	public void setYear40To49Rate(Double year40To49Rate) {
		this.year40To49Rate = year40To49Rate;
	}

	public Double getYear40To49AbnormalBloodPressureRate() {
		return year40To49AbnormalBloodPressureRate;
	}

	public void setYear40To49AbnormalBloodPressureRate(Double year40To49AbnormalBloodPressureRate) {
		this.year40To49AbnormalBloodPressureRate = year40To49AbnormalBloodPressureRate;
	}

	public Integer getYear50To59Number() {
		return year50To59Number;
	}

	public void setYear50To59Number(Integer year50To59Number) {
		this.year50To59Number = year50To59Number;
	}

	public Integer getYear50To59AbnormalBloodPressureNumber() {
		return year50To59AbnormalBloodPressureNumber;
	}

	public void setYear50To59AbnormalBloodPressureNumber(Integer year50To59AbnormalBloodPressureNumber) {
		this.year50To59AbnormalBloodPressureNumber = year50To59AbnormalBloodPressureNumber;
	}

	public Double getYear50To59Rate() {
		return year50To59Rate;
	}

	public void setYear50To59Rate(Double year50To59Rate) {
		this.year50To59Rate = year50To59Rate;
	}

	public Double getYear50To59AbnormalBloodPressureRate() {
		return year50To59AbnormalBloodPressureRate;
	}

	public void setYear50To59AbnormalBloodPressureRate(Double year50To59AbnormalBloodPressureRate) {
		this.year50To59AbnormalBloodPressureRate = year50To59AbnormalBloodPressureRate;
	}

	public Integer getYear60To69Number() {
		return year60To69Number;
	}

	public void setYear60To69Number(Integer year60To69Number) {
		this.year60To69Number = year60To69Number;
	}

	public Integer getYear60To69AbnormalBloodPressureNumber() {
		return year60To69AbnormalBloodPressureNumber;
	}

	public void setYear60To69AbnormalBloodPressureNumber(Integer year60To69AbnormalBloodPressureNumber) {
		this.year60To69AbnormalBloodPressureNumber = year60To69AbnormalBloodPressureNumber;
	}

	public Double getYear60To69Rate() {
		return year60To69Rate;
	}

	public void setYear60To69Rate(Double year60To69Rate) {
		this.year60To69Rate = year60To69Rate;
	}

	public Double getYear60To69AbnormalBloodPressureRate() {
		return year60To69AbnormalBloodPressureRate;
	}

	public void setYear60To69AbnormalBloodPressureRate(Double year60To69AbnormalBloodPressureRate) {
		this.year60To69AbnormalBloodPressureRate = year60To69AbnormalBloodPressureRate;
	}

	public Integer getOver70Number() {
		return over70Number;
	}

	public void setOver70Number(Integer over70Number) {
		this.over70Number = over70Number;
	}

	public Integer getOver70AbnormalBloodPressureNumber() {
		return over70AbnormalBloodPressureNumber;
	}

	public void setOver70AbnormalBloodPressureNumber(Integer over70AbnormalBloodPressureNumber) {
		this.over70AbnormalBloodPressureNumber = over70AbnormalBloodPressureNumber;
	}

	public Double getOver70Rate() {
		return over70Rate;
	}

	public void setOver70Rate(Double over70Rate) {
		this.over70Rate = over70Rate;
	}

	public Double getOver70AbnormalBloodPressureRate() {
		return over70AbnormalBloodPressureRate;
	}

	public void setOver70AbnormalBloodPressureRate(Double over70AbnormalBloodPressureRate) {
		this.over70AbnormalBloodPressureRate = over70AbnormalBloodPressureRate;
	}

	public Integer getOver65Number() {
		return over65Number;
	}

	public void setOver65Number(Integer over65Number) {
		this.over65Number = over65Number;
	}

	public Integer getOver65AbnormalBloodPressureNumber() {
		return over65AbnormalBloodPressureNumber;
	}

	public void setOver65AbnormalBloodPressureNumber(Integer over65AbnormalBloodPressureNumber) {
		this.over65AbnormalBloodPressureNumber = over65AbnormalBloodPressureNumber;
	}

	public Double getOver65Rate() {
		return over65Rate;
	}

	public void setOver65Rate(Double over65Rate) {
		this.over65Rate = over65Rate;
	}

	public Double getOver65AbnormalBloodPressureRate() {
		return over65AbnormalBloodPressureRate;
	}

	public void setOver65AbnormalBloodPressureRate(Double over65AbnormalBloodPressureRate) {
		this.over65AbnormalBloodPressureRate = over65AbnormalBloodPressureRate;
	}

	public Integer getYear65To74Number() {
		return year65To74Number;
	}

	public void setYear65To74Number(Integer year65To74Number) {
		this.year65To74Number = year65To74Number;
	}

	public Double getYear65To74Rate() {
		return year65To74Rate;
	}

	public void setYear65To74Rate(Double year65To74Rate) {
		this.year65To74Rate = year65To74Rate;
	}

	public Integer getYear75To84Number() {
		return year75To84Number;
	}

	public void setYear75To84Number(Integer year75To84Number) {
		this.year75To84Number = year75To84Number;
	}

	public Double getYear75To84Rate() {
		return year75To84Rate;
	}

	public void setYear75To84Rate(Double year75To84Rate) {
		this.year75To84Rate = year75To84Rate;
	}

	public Integer getOver85Number() {
		return over85Number;
	}

	public void setOver85Number(Integer over85Number) {
		this.over85Number = over85Number;
	}

	public Double getOver85Rate() {
		return over85Rate;
	}

	public void setOver85Rate(Double over85Rate) {
		this.over85Rate = over85Rate;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
}
