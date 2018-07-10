package com.syntrontech.pmo.cip.model.to;

import com.syntrontech.pmo.model.common.OwnershipStatus;

import java.util.Date;



public class FriendTO {
	
	private Long sequence;
	private String friendId;
	private String friendName;
	private OwnershipStatus Status;
	private Date createDate;
	
	public Long getSequence() {
		return sequence;
	}
	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}
	public String getFriendId() {
		return friendId;
	}
	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}
	public String getFriendName() {
		return friendName;
	}
	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}
	public OwnershipStatus getStatus() {
		return Status;
	}
	public void setStatus(OwnershipStatus status) {
		Status = status;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
