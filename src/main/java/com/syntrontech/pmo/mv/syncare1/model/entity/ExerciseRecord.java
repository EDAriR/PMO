package com.syntrontech.pmo.mv.syncare1.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.syntrontech.syncare.model.ObjectConverter;
import com.syntrontech.syncare.model.transfer.ExerciseRecordTO;

/**
 * The persistent class for the exercise_record database table.
 * 
 */
@Entity
@Table(name = "exercise_record")
@NamedQuery(name = "ExerciseRecord.findAll", query = "SELECT l FROM ExerciseRecord l")
public class ExerciseRecord implements ObjectConverter<ExerciseRecordTO>, Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "type_id")
	private int typeId;

	@Column(name = "value")
	private String value;
	
	@Column(name = "create_date")
	private Date createDate;
	
	@Column(name = "create_user")
	private String createUser;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	@Override
	public String toString() {
		return "ExerciseRecord [id=" + id + ", typeId=" + typeId + ", value=" + value + ", createDate=" + createDate
				+ ", createUser=" + createUser + "]";
	}

	@Override
	public ExerciseRecordTO convert(boolean relation) {
		ExerciseRecordTO to = new ExerciseRecordTO();
		to.setId(getId());
		to.setTypeId(getTypeId());
		to.setValue(getValue());
		to.setCreateDate(getCreateDate());
		to.setCreateUser(getCreateUser());
		return to;
	}
}