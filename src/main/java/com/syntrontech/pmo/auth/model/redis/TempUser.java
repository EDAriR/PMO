package com.syntrontech.pmo.auth.model.redis;

import java.io.IOException;
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
import com.syntrontech.pmo.model.common.ModelUserStatus;
import com.syntrontech.pmo.model.common.Source;
import com.syntrontech.redis.RedisHashModel;
import com.syntrontech.redis.annotation.RedisId;

public class TempUser implements RedisHashModel<TempUser>{
	
	@RedisId
	private String id;
	
	private String name;

	private String tenantId;
	
	private Source source;
	
	private String meta;
	
	private String[] unitIds;
	
	private String[] roleIds;
	
	private String[] emails;
	
	private String[] mobilePhones;
	
	private String[] cards;
	
	private ModelUserStatus status;
	
	private String[] permissionIds;
	
	private Date createTime;
	
	private String createBy;
	
	private Date updateTime;
	
	private String updateBy;
	
	private String password;
	
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

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
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

	public String[] getCards() {
		return cards;
	}

	public void setCards(String[] cards) {
		this.cards = cards;
	}

	public ModelUserStatus getStatus() {
		return status;
	}

	public void setStatus(ModelUserStatus status) {
		this.status = status;
	}

	public String[] getPermissionIds() {
		return permissionIds;
	}

	public void setPermissionIds(String[] permissionIds) {
		this.permissionIds = permissionIds;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
							if(field.get(this) instanceof Date){
								Date date = (Date) field.get(this);
								 TimeZone tz = TimeZone.getTimeZone("UTC");
								 DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
								 df.setTimeZone(tz);
								 return df.format(date);
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
	public TempUser transfer(Map<String, String> obj) {
		TempUser user = new TempUser();
		user.setId(obj.get("id"));
		user.setName(obj.get("name"));
		user.setTenantId(obj.get("tenantId"));
		user.setSource(Source.valueOf(obj.get("source")));
		user.setMeta(obj.get("meta"));
		user.setStatus(ModelUserStatus.valueOf(obj.get("status")));
		
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
		try{
			user.setCards(mapper.readValue(obj.get("cards"), String[].class));
		} catch (IOException e) {
			user.setCards(new String[0]);
			e.printStackTrace();
		}
		user.setCreateTime(DatatypeConverter.parseDate(obj.get("createTime")).getTime());
		user.setCreateBy(obj.get("createBy"));
		user.setUpdateTime(DatatypeConverter.parseDate(obj.get("updateTime")).getTime());
		user.setUpdateBy(obj.get("updateBy"));
		user.setPassword(obj.get("password"));
		return user;
	}
	
	@Override
	public String toString(){
		return "TempUser {id="+id+", name="+name+", tenantId="+tenantId+", source="+source+", meta="+meta
				+", unitIds="+Arrays.asList(unitIds)+", roleIds="+Arrays.asList(roleIds)+", emails="
				+Arrays.asList(emails)+", mobilePhones="+Arrays.asList(mobilePhones)
				+", cards="+Arrays.asList(cards)+", password="+password
				+", createTime="+createTime+", createBy="+createBy
				+", updateTime="+updateTime+", updateBy="+updateBy
				+", status="+status+", permissionIds="+Arrays.asList(permissionIds)+"}";
	}
}
