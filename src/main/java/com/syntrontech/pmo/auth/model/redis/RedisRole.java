package com.syntrontech.pmo.auth.model.redis;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.syntrontech.auth.model.common.ModelStatus;
import com.syntrontech.redis.RedisHashModel;
import com.syntrontech.redis.annotation.RedisId;

public class RedisRole implements RedisHashModel<RedisRole>{

	@RedisId
	private String redisId;
	
	private String id;
	
	private String name;
	
	private String tenantId;
	
	private ModelStatus status;
	
	private String[] permissionIds;

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

	public String[] getPermissionIds() {
		return permissionIds;
	}

	public void setPermissionIds(String[] permissionIds) {
		this.permissionIds = permissionIds;
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
	public RedisRole transfer(Map<String, String> obj) {
		RedisRole redisRole = new RedisRole();
		redisRole.setRedisId(obj.get("redisId"));
		redisRole.setId(obj.get("id"));
		redisRole.setName(obj.get("name"));
		redisRole.setTenantId(obj.get("tenantId"));
		redisRole.setStatus(ModelStatus.valueOf(obj.get("status")));
		ObjectMapper mapper = new ObjectMapper();
		try {
			redisRole.setPermissionIds(mapper.readValue(obj.get("permissionIds"), String[].class));
		} catch (IOException e) {
			redisRole.setPermissionIds(new String[0]);
			e.printStackTrace();
		}
		return redisRole;
	}
	
	@Override
	public String toString(){
		return "RedisRole {"
				+"redisId="+redisId
				+", id="+id
				+", name="+name
				+", tenantId="+tenantId
				+", status="+status
				+", permissionIds="+Arrays.asList(permissionIds)
				+"}";
	}
}
