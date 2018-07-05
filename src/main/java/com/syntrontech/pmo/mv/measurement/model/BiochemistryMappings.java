package com.syntrontech.pmo.mv.measurement.model;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.syntrontech.measurement.model.common.BiochemistryMappingsProject;
import com.syntrontech.measurement.model.converter.BiochemistryMappingsProjectConverter;

@Table
@Entity(name = "biochemistry_mappings")
public class BiochemistryMappings {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long sequence;
	
	@Column(name = "project", nullable = false)
	@Convert(converter = BiochemistryMappingsProjectConverter.class)
	private BiochemistryMappingsProject project;
	
	@Column(name = "category", nullable = false)
	private String category;
	
	@Column(name = "unit", nullable = false)
	private String unit;

	public Long getSequence() {
		return sequence;
	}

	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}

	public BiochemistryMappingsProject getProject() {
		return project;
	}

	public void setProject(BiochemistryMappingsProject project) {
		this.project = project;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
}
