package com.syntrontech.pmo.mv.syncare_service.model.redis;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.syntrontech.redis.RedisHashModel;
import com.syntrontech.redis.annotation.RedisId;

public class RedisQuestionnairQuestions implements RedisHashModel<RedisQuestionnairQuestions>{

	@RedisId
	private Long questionnairSeq;
	
	private List<RedisQuestion> questions;

	public Long getQuestionnairSeq() {
		return questionnairSeq;
	}

	public void setQuestionnairSeq(Long questionnairSeq) {
		this.questionnairSeq = questionnairSeq;
	}

	public List<RedisQuestion> getQuestions() {
		return questions;
	}

	public void setQuestions(List<RedisQuestion> questions) {
		this.questions = questions;
	}

	@Override
	public List<Field> getFields() {
		return Arrays.asList(this.getClass().getDeclaredFields());
	}

	@Override
	public String getKey() {
		return this.getClass().getSimpleName()+":"+questionnairSeq;
	}

	@Override
	public Map<String, String> getValues() {
		return getFields().stream()
		  		  		  .peek(field -> field.setAccessible(true))
		  		  		  .collect(Collectors.toMap(field -> field.getName(), field -> {
		  		  			  try {
		  		  				  if(Objects.nonNull(field.get(this))){
		  		  					  if(field.get(this) instanceof List){
		  		  						  List<?> listValue = (List<?>) field.get(this);
		  		  						  return new ObjectMapper().writeValueAsString(listValue);
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
	public RedisQuestionnairQuestions transfer(Map<String, String> obj) {
		RedisQuestionnairQuestions questionnairQuestions = new RedisQuestionnairQuestions();
		questionnairQuestions.setQuestionnairSeq(Long.valueOf(obj.get("questionnairSeq")));
		ObjectMapper mapper = new ObjectMapper();
		try {
			CollectionType collectionType = TypeFactory.defaultInstance()
														.constructCollectionType(List.class, RedisQuestion.class);
			questionnairQuestions.setQuestions(mapper.readValue(obj.get("questions"), collectionType));
		} catch (IOException e) {
			questionnairQuestions.setQuestions(new ArrayList<RedisQuestion>());
			e.printStackTrace();
		}
		
		return questionnairQuestions;
	}
}
