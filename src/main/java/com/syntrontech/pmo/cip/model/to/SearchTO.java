package com.syntrontech.pmo.cip.model.to;

import java.util.Collection;

public class SearchTO <T>{
	
	private Collection<T> content;
	
	private Integer totalElements;

	public Collection<T> getContent() {
		return content;
	}

	public void setContent(Collection<T> content) {
		this.content = content;
	}

	public Integer getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Integer totalElements) {
		this.totalElements = totalElements;
	}
	
}
