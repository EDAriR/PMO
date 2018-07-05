package com.syntrontech.pmo.mv.syncare1.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.syntrontech.syncare.annotation.Unique;
import com.syntrontech.syncare.model.ObjectConverter;
import com.syntrontech.syncare.model.transfer.TtshbRecluseRecordTO;

/**
 * The persistent class for the ttshb_recluse_record database table.
 * 
 */
@Entity
@Table(name = "ttshb_recluse_record")
public class TtshbRecluseRecord implements ObjectConverter<TtshbRecluseRecordTO>, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "recluse_id")
	@Unique
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int recluseId;

	@Column(name = "id_no")
	@Unique
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
	
	@Override
	public TtshbRecluseRecordTO convert(boolean relation) {
		TtshbRecluseRecordTO to = new TtshbRecluseRecordTO();
		to.setRecluseId(getRecluseId());
		to.setIdNo(getIdNo());
		to.setDowntownName(getDowntownName());
		to.setDowntownCode(getDowntownCode());
		to.setImportDate(new Date(getImportDate().getTime()));

		return to;
	}
}