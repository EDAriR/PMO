package com.syntrontech.pmo.mv.syncare_service.model.redis;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.syntrontech.redis.RedisHashModel;
import com.syntrontech.redis.annotation.RedisId;

//store in Redis
public class Session implements RedisHashModel<Session>{
	
	@RedisId
	private String id;
	
	private String userId;
	
	private String remoteAddress;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRemoteAddress() {
		return remoteAddress;
	}

	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}

	@Override
	public String getKey() {
		return this.getClass().getSimpleName()+":"+this.id;
	}

	@Override
	public List<Field> getFields() {
		return Arrays.asList(this.getClass().getDeclaredFields());
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
	public Session transfer(Map<String, String> obj) {
		Session session = new Session();
		session.setId(obj.get("id"));
		session.setUserId(obj.get("userId"));
		session.setRemoteAddress(obj.get("remoteAddress"));
		return session;
	}
	
	@Override
	public String toString(){
		return "Session {id="+id+", userId="+userId+", remoteAddress="+remoteAddress+"}";
	}
}
