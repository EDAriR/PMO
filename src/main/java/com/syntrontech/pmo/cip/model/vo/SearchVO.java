package com.syntrontech.pmo.cip.model.vo;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

import com.syntrontech.autoTool.annotation.ParamEnumRequired;
import com.syntrontech.pmo.cip.model.common.SearchOrder;

public class SearchVO {
	
	@QueryParam("keyword")
	private String keyword;
	
	@QueryParam("index")
	@DefaultValue("1")
	private Integer index;
	
	@QueryParam("rows")
	@DefaultValue("20")
	private Integer rows;
	
	@QueryParam("orderBy")
	@DefaultValue("updateTime")
	private String orderBy;
	
	@QueryParam("order")
	@ParamEnumRequired(SearchOrder.class)
	@DefaultValue("DESC")
	private String order;
	
	@QueryParam("filter")
	private String filter;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}
	
}
