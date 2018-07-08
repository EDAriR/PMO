package com.syntrontech.pmo.syncare1.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "food_calorie")
@NamedQuery(name = "CalorieMapping.findAll", query = "SELECT l FROM CalorieMapping l")
public class CalorieMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "FOOD_CALORIE_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "FOOD_ID")
	private int mappingId;

	@Column(name = "FOOD_CALORIE")
	private Double calorieValue;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMappingId() {
		return mappingId;
	}

	public void setMappingId(int mappingId) {
		this.mappingId = mappingId;
	}

	public Double getCalorieValue() {
		return calorieValue;
	}

	public void setCalorieValue(Double calorieValue) {
		this.calorieValue = calorieValue;
	}
}