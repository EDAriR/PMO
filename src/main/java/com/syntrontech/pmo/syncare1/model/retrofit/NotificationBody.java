package com.syntrontech.pmo.syncare1.model.retrofit;

import java.math.BigDecimal;

public class NotificationBody {

	private String userAccount;
	private long userId;
	private String friendName;
	private long timestamp;
	private BigDecimal maxBpValue;
	private BigDecimal minBpValue;

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFriendName() {
		return friendName;
	}

	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public BigDecimal getMaxBpValue() {
		return maxBpValue;
	}

	public void setMaxBpValue(BigDecimal maxBpValue) {
		this.maxBpValue = maxBpValue;
	}

	public BigDecimal getMinBpValue() {
		return minBpValue;
	}

	public void setMinBpValue(BigDecimal minBpValue) {
		this.minBpValue = minBpValue;
	}
}
