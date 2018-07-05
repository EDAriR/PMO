package com.syntrontech.pmo.mv.auth.model.redis;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.syntrontech.redis.RedisHashModel;
import com.syntrontech.redis.annotation.RedisId;

//store in Redis
public class Otp implements RedisHashModel<Otp>{
	
	@RedisId
	private String userId;
	
	private String otpStr;
	
	private String userAgent;
	
	private Integer errorTimes;
	
	private Integer allowErrorTimes;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOtpStr() {
		return otpStr;
	}

	public void setOtpStr(String otpStr) {
		this.otpStr = otpStr;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public Integer getErrorTimes() {
		return errorTimes;
	}

	public void setErrorTimes(Integer errorTimes) {
		this.errorTimes = errorTimes;
	}

	public Integer getAllowErrorTimes() {
		return allowErrorTimes;
	}

	public void setAllowErrorTimes(Integer allowErrorTimes) {
		this.allowErrorTimes = allowErrorTimes;
	}

	@Override
	public List<Field> getFields() {
		return Arrays.asList(this.getClass().getDeclaredFields());
	}

	@Override
	public String getKey() {
		return this.getClass().getSimpleName()+":"+this.userId;
	}

	@Override
	public Map<String, String> getValues() {
		return getFields().stream()
				.peek(field -> field.setAccessible(true))
				.collect(Collectors.toMap(field -> field.getName(), field -> {
					try {
						if(Objects.nonNull(field.get(this))){
							if(field.get(this) instanceof Integer){
								Integer integer = (Integer)field.get(this);
								return String.valueOf(integer);
							}
							return field.get(this).toString();
						}
						return "";
					} catch (Exception e) {
						e.printStackTrace();
						return "";
					}
				}));
	}

	@Override
	public Otp transfer(Map<String, String> obj) {
		Otp otp = new Otp();
		otp.setUserId(obj.get("userId"));
		otp.setOtpStr(obj.get("otpStr"));
		otp.setUserAgent(obj.get("userAgent"));
		otp.setErrorTimes(Integer.parseInt((obj.get("errorTimes"))));
		otp.setAllowErrorTimes(Integer.parseInt(obj.get("allowErrorTimes")));
		return otp;
	}
	
	@Override
	public String toString(){
		return "RedisOtp [userId="+userId+", otpStr="+otpStr+", userAgent="+userAgent
				+", errorTimes="+errorTimes+", allowErrorTimes="+allowErrorTimes+"]";
	}
}
