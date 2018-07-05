package com.syntrontech.pmo.util;

import java.util.Calendar;
import java.util.Date;

public class CalendarUtil {
	
	public static Integer getAgeFromBirthDate(Date birthdayTime, Date countTime){
		Calendar now = Calendar.getInstance();
		now.setTime(countTime);
		Calendar birthday = Calendar.getInstance();
		birthday.setTime(birthdayTime);
		
		int age = now.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
		
		if(now.get(Calendar.MONTH) < birthday.get(Calendar.MONTH)){
			age--;
		}else if(now.get(Calendar.MONTH) == birthday.get(Calendar.MONTH)){
			if(now.get(Calendar.DATE) < birthday.get(Calendar.DATE)){
				age--;
			}
		}
		
		return age;
	}
	
}
