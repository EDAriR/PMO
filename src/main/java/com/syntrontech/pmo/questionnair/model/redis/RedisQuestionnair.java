package com.syntrontech.pmo.questionnair.model.redis;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.syntrontech.redis.RedisHashModel;
import com.syntrontech.redis.annotation.RedisId;

public class RedisQuestionnair implements RedisHashModel<RedisQuestionnair>{

	@RedisId
	private Long sequence;
	
	private String id;
	
	private String title;
	
	private String shortTitle;
	
	private Long positionSequence;
	
	private String tenantId;
	
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	public Long getPositionSequence() {
		return positionSequence;
	}

	public void setPositionSequence(Long positionSequence) {
		this.positionSequence = positionSequence;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	@Override
	public List<Field> getFields() {
		return Arrays.asList(this.getClass().getDeclaredFields());
	}

	@Override
	public String getKey() {
		return this.getClass().getSimpleName()+":"+this.sequence;
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
	public RedisQuestionnair transfer(Map<String, String> obj) {
		RedisQuestionnair questionnair = new RedisQuestionnair();
		questionnair.setSequence(Long.valueOf(obj.get("sequence")));
		questionnair.setId(obj.get("id"));
		questionnair.setTitle(obj.get("title"));
		questionnair.setShortTitle(obj.get("shortTitle"));
		questionnair.setPositionSequence(Long.valueOf(obj.get("positionSequence")));
		questionnair.setTenantId(obj.get("tenantId"));
		return questionnair;
	}
	
	@Autowired
	public String toString(){
		return "RedisQuestionnair {"
					+"sequence="+sequence+","
					+"id="+id+","
					+"title="+title+","
					+"shortTitle="+shortTitle+","
					+"positionSequence="+positionSequence+","
					+"tenantId="+tenantId+","
					+"}";
	}
}
