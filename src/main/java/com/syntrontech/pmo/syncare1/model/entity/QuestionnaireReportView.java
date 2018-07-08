package com.syntrontech.pmo.syncare1.model.entity;

import com.syntrontech.pmo.syncare1.model.common.Sex;
import com.syntrontech.pmo.syncare1.model.common.YN;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "QUESTIONNAIRE_REPORT_VIEW")
@NamedQuery(name = "QuestionnaireReportView.findAll", query = "SELECT r FROM QuestionnaireReportView r")
public class QuestionnaireReportView implements Serializable {
	private static final long serialVersionUID = 4610182561658886851L;

	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "question_id")
	private int questionId;

	@Column(name = "user_account")
	private String account;

	@Column(name = "user_display_name")
	private String userName;

	@Column(name = "user_birthday")
	private String birthday;

	@Column(name = "address")
	private String address;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "phone")
	private String phone;

	@Column(name = "sex")
	@Enumerated(EnumType.STRING)
	private Sex sex;

	@Column(name = "age")
	private int age;

	@Column(name = "with_high_blood_pressure")
	@Enumerated(EnumType.STRING)
	private YN withHighBloodPressure;

	@Column(name = "question_title")
	private String questionTitle;

	@Column(name = "answer_id")
	private int answerId;

	@Column(name = "answer_label")
	private String answerLabel;

	@Column(name = "ans_value")
	private String answerValue;

	@Column(name = "create_date")
	private Date answerDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public YN getWithHighBloodPressure() {
		return withHighBloodPressure;
	}

	public void setWithHighBloodPressure(YN withHighBloodPressure) {
		this.withHighBloodPressure = withHighBloodPressure;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public int getAnswerId() {
		return answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	public String getAnswerLabel() {
		return answerLabel;
	}

	public void setAnswerLabel(String answerLabel) {
		this.answerLabel = answerLabel;
	}

	public String getAnswerValue() {
		return answerValue;
	}

	public void setAnswerValue(String answerValue) {
		this.answerValue = answerValue;
	}

	public Date getAnswerDate() {
		return answerDate;
	}

	public void setAnswerDate(Date answerDate) {
		this.answerDate = answerDate;
	}

	@Override
	public String toString() {
		return "QuestionnaireReportView [id=" + id + ", account=" + account + ", username=" + userName + ", birthday="
				+ birthday + ", questionTitle=" + questionTitle + ", answerId=" + answerId + ", answerLabel="
				+ answerLabel + ", answerValue=" + answerValue + "]";
	}
}
