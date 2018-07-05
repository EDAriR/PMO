package com.syntrontech.pmo.mv.measurement.model;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.syntrontech.autoTool.model.UserModel;
import com.syntrontech.redis.RedisHashModel;
import com.syntrontech.redis.annotation.RedisId;

public class RedisUser implements RedisHashModel<RedisUser>, UserModel{
	
	@RedisId
	private String id;
	
	private String name;

	private String tenantId;
	
	private String source;
	
	private String meta;
	
	private String[] unitIds;
	
	private String[] roleIds;
	
	private String[] emails;
	
	private String[] mobilePhones;
	
	private String status;
	
	private String[] permissionIds;

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
	
	@Override
	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getMeta() {
		return meta;
	}

	public void setMeta(String meta) {
		this.meta = meta;
	}

	public String[] getUnitIds() {
		return unitIds;
	}

	public void setUnitIds(String[] unitIds) {
		this.unitIds = unitIds;
	}

	public String[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}

	public String[] getEmails() {
		return emails;
	}

	public void setEmails(String[] emails) {
		this.emails = emails;
	}

	public String[] getMobilePhones() {
		return mobilePhones;
	}

	public void setMobilePhones(String[] mobilePhones) {
		this.mobilePhones = mobilePhones;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
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
		return this.getClass().getSimpleName()+":"+this.id;
	}

	@Override
	public Map<String, String> getValues() {
		return getFields().stream()
				.peek(field -> field.setAccessible(true))
				.collect(Collectors.toMap(field -> field.getName(), field -> {
					try {
						if(Objects.nonNull(field.get(this))){
							if(field.get(this) instanceof Integer){
								Integer integer = (Integer)field.get(this);
								return String.valueOf(integer);
							}else if(field.get(this) instanceof Long){
								Long longNum = (Long)field.get(this);
								return String.valueOf(longNum);
							}else if(field.get(this) instanceof String[]){
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
	public RedisUser transfer(Map<String, String> obj) {
		RedisUser user = new RedisUser();
		user.setId(obj.get("id"));
		user.setName(obj.get("name"));
		user.setTenantId(obj.get("tenantId"));
		user.setSource(obj.get("source"));
		user.setMeta(obj.get("meta"));
		user.setStatus(obj.get("status"));
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			user.setEmails(mapper.readValue(obj.get("emails"), String[].class));
		} catch (IOException e) {
			user.setEmails(new String[0]);
			e.printStackTrace();
		}
		try{
			user.setMobilePhones(mapper.readValue(obj.get("mobilePhones"), String[].class));
		} catch (IOException e) {
			user.setMobilePhones(new String[0]);
			e.printStackTrace();
		}
		try{
			user.setUnitIds(mapper.readValue(obj.get("unitIds"), String[].class));
		} catch (IOException e) {
			user.setUnitIds(new String[0]);
			e.printStackTrace();
		}
		try{
			user.setRoleIds(mapper.readValue(obj.get("roleIds"), String[].class));
		} catch (IOException e) {
			user.setRoleIds(new String[0]);
			e.printStackTrace();
		}
		try{
			user.setPermissionIds(mapper.readValue(obj.get("permissionIds"), String[].class));
		} catch (IOException e) {
			user.setPermissionIds(new String[0]);
			e.printStackTrace();
		}
		return user;
	}
	
	@Override
	public String toString(){
		return "RedisUser {id="+id+", name="+name+", tenantId="+tenantId+", source="+source+", meta="+meta
				+", unitIds="+unitIds+", roleIds="+roleIds+", emails="+emails+", mobilePhones="+mobilePhones
				+", status="+status+", permissionIds="+Arrays.asList(permissionIds)+"}";
	}

	@Override
	public String getUserId() {
		return id;
	}
	
}
