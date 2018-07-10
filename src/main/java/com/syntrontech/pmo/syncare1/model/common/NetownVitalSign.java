package com.syntrontech.pmo.syncare1.model.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.syntrontech.pmo.syncare1.model.ObjectConverter;
import com.syntrontech.pmo.syncare1.model.Mapping;
import com.syntrontech.pmo.syncare1.model.UserValueRecord;
import com.syntrontech.pmo.syncare1.model.UserValueRecordMapping;
import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class NetownVitalSign implements ObjectConverter<UserValueRecord> {
	public enum Type{
		/**
		 * 血壓
		 */
		BP, 
		/**
		 * 血糖
		 */
		BG, 
		/**
		 * 心電圖
		 */
		ECG, 
		/**
		 * 身體體重
		 */
		Weight;
		
		public Service.RecordType toSynCareType(){
			switch (this) {
				case BP:
					return Service.RecordType.B;
				case BG:
					return Service.RecordType.BG;
				case Weight:
					return Service.RecordType.A;
				case ECG:
				default:
					return Service.RecordType.B;
			}
		}
	}
	
	public enum Mark{
		/**
		 * 飯前血糖
		 */
		AC,
		/**
		 * 飯後血糖
		 */
		PC,
		/**
		 * ???
		 */
		PM
	}
	
	@JsonProperty("Type")
	private Type type;
	
	@JsonProperty("MeasureTime")
	private String measureTime;
	
	@JsonProperty("Mark")
	private Optional<Mark> mark = Optional.ofNullable(null);
	
	@JsonProperty("Values")
	private List<Number> values;
	
	public NetownVitalSign() {
	}

	@JsonCreator
	public NetownVitalSign(
			@JsonProperty("Type") Type type, 
			@JsonProperty("MeasureTime") String measureTime, 
			@JsonProperty("Mark") String mark,
			@JsonProperty("Values") List<Number> values) {
		this.type = type;
		this.measureTime = measureTime;
		setMark(mark);
		this.values = values;
	}

	public Type getType() {
		return type;
	}

	public String getMeasureTime() {
		return measureTime;
	}

	public String getMark() {
		return mark.isPresent()?mark.get().name():"";
	}

	public List<Number> getValues() {
		return values;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public void setMeasureTime(String measureTime) {
		this.measureTime = measureTime;
	}

	public void setMark(String mark) {
		this.mark = StringUtils.isNotBlank(mark)?
				Optional.ofNullable(Mark.valueOf(mark)):
					Optional.ofNullable(null);
	}

	public void setValues(List<Number> values) {
		this.values = values;
	}

	@Override
	public String toString() {
		return "NetownVitalSign [type=" + type + ", measureTime=" + measureTime
				+ ", mark=" + getMark() + ", values=" + values + "]";
	}

	@Override
	public UserValueRecord convert(boolean relation) {
		UserValueRecord record = new UserValueRecord();
		record.setColumnType(this.getType().toSynCareType().name());

		SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String recordDate = getMeasureTime();
		try {
			record.setRecordDate(f.parse(recordDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if(relation){
			List<UserValueRecordMapping> mappings = buildRecord(getValues());
			mappings.stream()
				.forEach(mapping -> record.addUserValueRecordMapping(mapping));
		}
		return record;
	}

	private List<UserValueRecordMapping> buildRecord(List<Number> numbers){
		switch (getType()) {
			case BG:
				return buildTypeBGRecord(numbers.get(0));
			case BP:
				return buildTypeBRecord(numbers.get(0), numbers.get(1), numbers.get(2));
			case Weight:
				return buildTypeARecord(numbers.get(0), numbers.get(1));
			case ECG:
				break;
			default:
				break;
		}
		return new ArrayList<>();
	}

	private List<UserValueRecordMapping> buildTypeARecord(Number height, Number weight) {
		UserValueRecordMapping _height = buildUserValueRecordMapping(""+height, buildMapping(3, "身高", "A"));
		UserValueRecordMapping _weight = buildUserValueRecordMapping(""+weight, buildMapping(4, "體重", "A"));
		UserValueRecordMapping _bmi = buildUserValueRecordMapping(""+getBMI(height, weight), buildMapping(5, "BMI", "A"));
		return Arrays.asList(_height, _weight, _bmi);
	}
	
	private Number getBMI(Number height, Number weight){
		int _height = height.intValue();
		int _weight = weight.intValue();
		if(_height==0){
			return 0;
		}else{
			return _weight/Math.pow((_height/100), 2);
		}
	}

	private List<UserValueRecordMapping> buildTypeBRecord(Number maxBloodPressure, Number minBloodPressureValue, Number heartbeatValue){
		UserValueRecordMapping _maxBloodPressure = buildUserValueRecordMapping(""+maxBloodPressure, buildMapping(7, "收縮壓", "B"));
		UserValueRecordMapping _minBloodPressureValue = buildUserValueRecordMapping(""+minBloodPressureValue, buildMapping(8, "舒張壓", "B"));
		UserValueRecordMapping _heartbeatValue = buildUserValueRecordMapping(""+heartbeatValue, buildMapping(9, "心跳", "B"));
		return Arrays.asList(_maxBloodPressure, _minBloodPressureValue, _heartbeatValue);
	}
	
	private List<UserValueRecordMapping> buildTypeBGRecord(Number bloodGlucose){
		UserValueRecordMapping _bloodGlucose = buildUserValueRecordMapping(""+bloodGlucose, buildMapping(136, "血糖", "BG"));
		return Arrays.asList(_bloodGlucose);
	}
	
	private Mapping buildMapping(Integer type, String name, String columnType){
		Mapping mapping = new Mapping();
		mapping.setTypeId(type);
		mapping.setTypeName(name);
		mapping.setColumnType(columnType);
		return mapping;
	}
	
	private UserValueRecordMapping buildUserValueRecordMapping(String value, Mapping mapping){
		UserValueRecordMapping recordMapping = new UserValueRecordMapping();
		recordMapping.setRecordValue(value);
		recordMapping.setMapping(mapping);
		return recordMapping;
	}
}
