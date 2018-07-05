package com.syntrontech.pmo.mv.syncare_service.model.redis;

import org.springframework.beans.factory.annotation.Autowired;

public class RedisQuestionOption {
	
	private Long sequence;
	
	private String title;
	
	private String score;

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

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
	@Autowired
	public String toString(){
		return "RedisAnswer {"
					+"sequence="+sequence+","
					+"title="+title+","
					+"score="+score
					+"}";
	}
}
