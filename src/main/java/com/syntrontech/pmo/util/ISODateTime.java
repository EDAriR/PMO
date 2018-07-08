package com.syntrontech.pmo.util;

import java.time.Instant;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;

public class ISODateTime {
	
	public static Date parseISOString(String date){
		return DatatypeConverter.parseDate(date)
								.getTime();
	}
	
	public static String formatISOString(Date date){
		return Instant.ofEpochMilli(date.getTime())
					  .toString();
	}
}
