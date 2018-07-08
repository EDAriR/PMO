package com.syntrontech.pmo.cip.model.redis;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.syntrontech.pmo.model.common.ModelStatus;
import com.syntrontech.redis.RedisHashModel;
import com.syntrontech.redis.annotation.RedisId;

public class RedisDevice implements RedisHashModel<RedisDevice>{
	
	@RedisId
	private String redisId;
	
	private String id;
	
	private String name;
	
	private String macAddress;
	
	private String serialNumber;
	
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

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
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
	public RedisDevice transfer(Map<String, String> obj) {
		RedisDevice redisDevice = new RedisDevice();
		redisDevice.setRedisId(obj.get("redisId"));
		redisDevice.setId(obj.get("id"));
		redisDevice.setName(obj.get("name"));
		redisDevice.setMacAddress(obj.get("macAddress"));
		redisDevice.setSerialNumber(obj.get("serialNumber"));
		redisDevice.setUnitId(obj.get("unitId"));
		redisDevice.setUnitName(obj.get("unitName"));
		redisDevice.setTenantId(obj.get("tenantId"));
		redisDevice.setStatus(ModelStatus.valueOf(obj.get("status")));
		return redisDevice;
	}
	
	@Override
	public String toString(){
		return "RedisDevice {redisId="+redisId+", id="+id+", name="+name
				+", macAddress="+macAddress+", serialNumber="+serialNumber
				+", unitId="+unitId+", unitName="+unitName+", tenantId="+tenantId
				+", status="+status+"}";
	}
}
