package com.syntrontech.pmo.solr;

import java.util.List;

public class SolrSearchModel<T> {
	
	private List<T> content;
	
	private Integer totalnumFound;
	
	public List<T> getContent() {
		return content;
	}
	public void setContent(List<T> content) {
		this.content = content;
	}
	public Integer getTotalnumFound() {
		return totalnumFound;
	}
	public void setTotalnumFound(Integer totalnumFound) {
		this.totalnumFound = totalnumFound;
	}
	
}
