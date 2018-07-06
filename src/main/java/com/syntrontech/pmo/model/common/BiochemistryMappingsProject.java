package com.syntrontech.pmo.model.common;

import com.syntrontech.cip.solr.model.BiochemistryComplete;
import com.syntrontech.cip.solr.model.SolrBiochemistry;

public enum BiochemistryMappingsProject {
	HbA1C("HbA1C"){
		//糖化血色素
		@Override
		public BiochemistryComplete setValue(BiochemistryComplete bc, SolrBiochemistry sb) {
			bc.setHbA1CValue(sb.getValue_s());
			return bc;
		}
	}, 
	Total_Cholesterol("Total Cholesterol"){
		//膽固醇
		@Override
		public BiochemistryComplete setValue(BiochemistryComplete bc, SolrBiochemistry sb) {
			bc.setTotal_CholesterolValue(sb.getValue_s());
			return bc;
		}
	}, 
	Triglyceride("Triglyceride"){
		//三酸甘油脂
		@Override
		public BiochemistryComplete setValue(BiochemistryComplete bc, SolrBiochemistry sb) {
			bc.setTriglycerideValue(sb.getValue_s());
			return bc;
		}
	}, 
	HDL_Cholesterol("HDL-Cholesterol"){
		//高密度脂蛋白
		@Override
		public BiochemistryComplete setValue(BiochemistryComplete bc, SolrBiochemistry sb) {
			bc.setHDL_CholesterolValue(sb.getValue_s());
			return bc;
		}
	}, 
	LDL_Cholesterol("LDL-Cholesterol"){
		//低密度脂蛋白膽固醇
		@Override
		public BiochemistryComplete setValue(BiochemistryComplete bc, SolrBiochemistry sb) {
			bc.setLDL_CholesterolValue(sb.getValue_s());
			return bc;
		}
	}, 
	GOT("GOT"){
		//天門冬氨酸轉胺酶
		@Override
		public BiochemistryComplete setValue(BiochemistryComplete bc, SolrBiochemistry sb) {
			bc.setGOTValue(sb.getValue_s());
			return bc;
		}
	}, 
	GPT("GPT"){
		//丙氨酸轉胺酶
		@Override
		public BiochemistryComplete setValue(BiochemistryComplete bc, SolrBiochemistry sb) {
			bc.setGPTValue(sb.getValue_s());
			return bc;
		}
	}, 
	Creatinine("Creatinine"){
		//肌酸酐
		@Override
		public BiochemistryComplete setValue(BiochemistryComplete bc, SolrBiochemistry sb) {
			bc.setCreatinineValue(sb.getValue_s());
			return bc;
		}
	}; 
	private String project;
	
	BiochemistryMappingsProject(String project){
		this.project = project;
	}
	
	public String getProject(){
		return project;
	}

	public BiochemistryComplete setValue(BiochemistryComplete bc, SolrBiochemistry sb) {
		return bc;
	}
}
