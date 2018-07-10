package com.syntrontech.pmo.syncare1.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the ttshb_recluse_record database table.
 * 
 */
@Entity
@Table(name = "ttshb_recluse_record")
public class TtshbRecluseRecord{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "recluse_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int recluseId;

	@Column(name = "id_no")
	private String idNo;
	
	@Column(name = "downtown_name")
	private String downtownName;
	
	@Column(name = "downtown_code")
	private String downtownCode;
	
	@Column(name = "import_date")
	private Timestamp importDate = new Timestamp(new Date().getTime());



	public int getRecluseId() {
		return recluseId;
	}

	public void setRecluseId(int recluseId) {
		this.recluseId = recluseId;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getDowntownName() {
		return downtownName;
	}

	public void setDowntown_name(String downtownName) {
		this.downtownName = downtownName;
	}

	public String getDowntownCode() {
		return downtownCode;
	}

	public void setDowntownCode(String downtownCode) {
		this.downtownCode = downtownCode;
	}
	
	public Timestamp getImportDate() {
		return importDate;
	}

	public void setImportDate(Timestamp importDate) {
		this.importDate = importDate;
	}

	
	
	@Override
	public String toString() {
		return "TtshbRecluseRecord ["
				+ "recluseId=" + recluseId 
				+ ", idNo=" + idNo 
				+ ", downtownName=" + downtownName
				+ ", downtownCode=" + downtownCode
				+ ", importDate=" + importDate 
				+ "]";
	}

}