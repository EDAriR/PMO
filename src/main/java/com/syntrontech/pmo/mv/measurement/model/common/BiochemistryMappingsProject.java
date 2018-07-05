package com.syntrontech.pmo.mv.measurement.model.common;

import java.util.stream.Stream;

public enum BiochemistryMappingsProject {
	HbA1C("HbA1C"), //糖化血色素
	Total_Cholesterol("Total Cholesterol"), //膽固醇
	Triglyceride("Triglyceride"), //三酸甘油脂
	HDL_Cholesterol("HDL-Cholesterol"), //高密度脂蛋白
	LDL_Cholesterol("LDL-Cholesterol"), //低密度脂蛋白膽固醇
	GOT("GOT"), //天門冬氨酸轉胺酶
	GPT("GPT"), //丙氨酸轉胺酶
	Creatinine("Creatinine"); //肌酸酐
	private String project;
	
	BiochemistryMappingsProject(String project){
		this.project = project;
	}
	
	public String getProject(){
		return project;
	}
	
	public static BiochemistryMappingsProject convertFrom(String str){
		return Stream.of(BiochemistryMappingsProject.values())
					 .filter(bio -> bio.getProject().equals(str))
					 .findFirst()
					 .orElseThrow(() -> new UnsupportedOperationException("the string "
					 		+ "["+ str + "]" + "doesn't match the enum[BiochemistryMappingsProject]."));
	}
}
