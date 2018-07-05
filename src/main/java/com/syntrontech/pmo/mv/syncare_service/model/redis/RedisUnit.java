package com.syntrontech.pmo.mv.syncare_service.model.redis;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.syntrontech.cip.model.common.ModelMgmtStatus;
import com.syntrontech.redis.RedisHashModel;
import com.syntrontech.redis.annotation.RedisId;

public class RedisUnit implements RedisHashModel<RedisUnit>{
	
	@RedisId
	private String redisId;
	
	private String id;
	
	private String name;

	private String parentId;
	
	private String tenantId;
	
	private ModelMgmtStatus status;

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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public ModelMgmtStatus getStatus() {
		return status;
	}

	public void setStatus(ModelMgmtStatus status) {
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
	public RedisUnit transfer(Map<String, String> obj) {
		RedisUnit redisUnit = new RedisUnit();
		redisUnit.setRedisId(obj.get("redisId"));
		redisUnit.setId(obj.get("id"));
		redisUnit.setName(obj.get("name"));
		redisUnit.setParentId(obj.get("parentId"));
		redisUnit.setTenantId(obj.get("tenantId"));
		redisUnit.setStatus(ModelMgmtStatus.valueOf(obj.get("status")));
		return redisUnit;
	}
	
	@Override
	public String toString(){
		return "RedisUnit {redisId="+redisId+", id="+id+", name="+name
				+", tenantId="+tenantId+", parentId="+parentId+", status="+status+"}";
	}
}
