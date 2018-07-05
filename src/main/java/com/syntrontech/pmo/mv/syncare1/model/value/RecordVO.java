package com.syntrontech.pmo.mv.syncare1.model.value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class RecordVO implements Serializable {
	private static final long serialVersionUID = -6864925173801588695L;

	private List<String> field;
	
	private List<BigDecimal> value;

	public List<String> getField() {
		return field;
	}

	public List<BigDecimal> getValue() {
		return value;
	}

	public void setField(List<String> field) {
		this.field = field;
	}

	public void setValue(List<BigDecimal> value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "RecordVO [field=" + field + ", value=" + value + "]";
	}
}
