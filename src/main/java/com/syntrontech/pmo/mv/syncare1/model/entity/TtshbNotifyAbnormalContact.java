package com.syntrontech.pmo.mv.syncare1.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.syntrontech.syncare.annotation.Unique;
import com.syntrontech.syncare.model.ObjectConverter;
import com.syntrontech.syncare.model.transfer.TtshbNotifyAbnormalContactTO;

/**
 * The persistent class for the ttshb_notify_abnormal_contact database table.
 * 
 */
@Entity
@Table(name = "ttshb_notify_abnormal_contact")
public class TtshbNotifyAbnormalContact implements ObjectConverter<TtshbNotifyAbnormalContactTO>, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "contact_id")
	@Unique
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int contactId;
	
	@Column(name = "downtown_code")
	private String downtownCode;

	@Column(name = "downtown_name")
	private String downtownName;
	
	@Column(name = "contact_name")
	private String contactName;
	
	@Column(name = "contact_email")
	private String contactEmail;
	
	
	
	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}
	
	public String getDowntownCode() {
		return downtownCode;
	}

	public void setDowntownCode(String downtownCode) {
		this.downtownCode = downtownCode;
	}
	
	public String getDowntownName() {
		return downtownName;
	}

	public void setDowntownName(String downtownName) {
		this.downtownName = downtownName;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	
	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	
	
	@Override
	public String toString() {
		return "TtshbNotifyAbnormalContact ["
				+ "contactId=" + contactId 
				+ ", downtownCode=" + downtownCode 
				+ ", downtownName=" + downtownName
				+ ", contactName=" + contactName
				+ ", contactEmail=" + contactEmail 
				+ "]";
	}
	
	@Override
	public TtshbNotifyAbnormalContactTO convert(boolean relation) {
		TtshbNotifyAbnormalContactTO to = new TtshbNotifyAbnormalContactTO();
		to.setContactId(getContactId());
		to.setDowntownCode(getDowntownCode());
		to.setDowntownName(getDowntownName());
		to.setContactName(getContactName());
		to.setContactEmail(getContactEmail());
		
		return to;
	}
}