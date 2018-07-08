package com.syntrontech.pmo.syncare1.model.common;


public interface Service {
	public enum RecordType {
		A("身高/體重/BMI"), B("血壓/心跳"), BG("血糖"), ET("體溫"), SPO2("血氧率"), VF("內臟脂肪指數"), G("飲食"), E("運動"),
		/**
		 * 糖化血色素
		 */
		GH("糖化血色素"),
		/**
		 * 血脂肪類-膽固醇
		 */
		CHOL("膽固醇"),
		/**
		 * 血脂肪類-三酸甘油脂
		 */
		TG("三酸甘油脂"),
		/**
		 * 血脂肪類-高密度脂蛋白
		 */
		HLD("高密度脂蛋白"),
		/**
		 * 血脂肪類-低密度脂蛋白
		 */
		LDL("低密度脂蛋白"),
		/**
		 * 肝功能
		 */
		LF("肝功能"),
		/**
		 * 腎功能
		 */
		RF("腎功能");

		private String title;

		private RecordType(String title) {
			this.title = title;
		}

		public String getTitle() {
			return title;
		}

		public NetownVitalSign.Type toNetownType() {
			switch (this) {
			case A:
				return NetownVitalSign.Type.Weight;
			case B:
				return NetownVitalSign.Type.BP;
			case BG:
				return NetownVitalSign.Type.BG;
			default:
				return NetownVitalSign.Type.BP;
			}
		}
	}
}
