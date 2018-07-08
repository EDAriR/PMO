package com.syntrontech.pmo.cip.model.redis;

import java.util.List;

import com.syntrontech.pmo.model.common.QuestionType;
import org.springframework.beans.factory.annotation.Autowired;


public class RedisQuestion{
	
	private Long sequence;
	
	private String title;
	
	private Long positionSequence;
	
	private QuestionType type;
	
	private List<RedisQuestionOption> options;

	public Long getSequence() {
		return sequence;
	}

	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getPositionSequence() {
		return positionSequence;
	}

	public void setPositionSequence(Long positionSequence) {
		this.positionSequence = positionSequence;
	}
	
	public QuestionType getType() {
		return type;
	}

	public void setType(QuestionType type) {
		this.type = type;
	}

	public List<RedisQuestionOption> getOptions() {
		return options;
	}

	public void setOptions(List<RedisQuestionOption> options) {
		this.options = options;
	}

	@Autowired
	public String toString(){
		return "RedisQuestion {"
					+"sequence="+sequence+","
					+"title="+title+","
					+"positionSequence="+positionSequence+","
					+"type="+type.name()+","
					+"options="+options
					+"}";
	}

}
