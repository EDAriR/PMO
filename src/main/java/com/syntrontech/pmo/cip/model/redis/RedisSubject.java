package com.syntrontech.pmo.cip.model.redis;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;
import java.util.stream.Collectors;

import javax.xml.bind.DatatypeConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.syntrontech.cip.model.common.GenderType;
import com.syntrontech.cip.model.common.ModelStatus;
import com.syntrontech.redis.RedisHashModel;
import com.syntrontech.redis.annotation.RedisId;

public class RedisSubject implements RedisHashModel<RedisSubject>{
	
	@RedisId
	private String redisId;
	
	private Long sequence;
	
	private String id;
	
	private String name;
	
	private GenderType gender;
	
	private Date birthday;
		
	private String userId;
	
	private String unitId;
	
	private String unitName;

	private String tenantId;
	
	private ModelStatus status;
	
	public String getRedisId() {
		return redisId;
	}

	public void setRedisId(String redisId) {
		this.redisId = redisId;
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

	public ModelStatus getStatus() {
		return status;
	}

	public void setStatus(ModelStatus status) {
		this.status = status;
	}

	@Override
	public List<Field> getFields() {
		return Arrays.asList(this.getClass().getDeclaredFields());
	}

	@Override
	public String getKey() {
		return this.getClass().getSimpleName()+":"+this.redisId;
	}

	@Override
	public Map<String, String> getValues() {
		return getFields().stream()
				.peek(field -> field.setAccessible(true))
				.collect(Collectors.toMap(field -> field.getName(), field -> {
					try {
						if(Objects.nonNull(field.get(this))){
							if(field.get(this) instanceof String[]){
								String[] strArray = (String[]) field.get(this);
								return new ObjectMapper().writeValueAsString(strArray);
							}else if(field.get(this) instanceof Date){
								 Date date = (Date) field.get(this);
								 TimeZone tz = TimeZone.getTimeZone("UTC");
								 DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
								 df.setTimeZone(tz);
								 return df.format(date);
							}else if(field.get(this) instanceof Long){
								Long longNum = (Long)field.get(this);
								return String.valueOf(longNum);
							}
							return field.get(this).toString();
						}
						return "";
					} catch (Exception e) {
						e.printStackTrace();
						return "";
					}
				}));
	}

	@Override
	public RedisSubject transfer(Map<String, String> obj) {
		RedisSubject redisSubject = new RedisSubject();
		redisSubject.setRedisId(obj.get("redisId"));
		redisSubject.setSequence(Long.parseLong(obj.get("sequence")));
		redisSubject.setId(obj.get("id"));
		redisSubject.setName(obj.get("name"));
		redisSubject.setGender(GenderType.valueOf(obj.get("gender")));
		redisSubject.setBirthday(DatatypeConverter.parseDate(obj.get("birthday")).getTime());
		redisSubject.setUserId(obj.get("userId"));
		redisSubject.setUnitId(obj.get("unitId"));
		redisSubject.setUnitName(obj.get("unitName"));
		redisSubject.setTenantId(obj.get("tenantId"));
		redisSubject.setStatus(ModelStatus.valueOf(obj.get("status")));
		return redisSubject;
	}
	
	@Override
	public String toString(){
		return "RedisSubject {redisId="+redisId+", sequence="+sequence+", id="+id+", name="+name
				+", tenantId="+tenantId+", gender="+gender.name()+", birthday="+birthday
				+", userId="+userId+", unitId="+unitId+", unitName="+unitName+", status="+status+"}";
	}

}
