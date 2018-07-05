package com.syntrontech.pmo.util; 

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateTime {
	
	private static final Logger logger = LoggerFactory.getLogger(DateTime.class);
	
	public static String format(Date date, String zone, String pattern){
		return date.toInstant().atZone(ZoneId.of(zone)).format(DateTimeFormatter.ofPattern(pattern));
	}
	
	public static Date parseDateTime(String dateTime, String zone, String pattern){
		try{
			Instant localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(pattern))
												 .atZone(ZoneId.of(zone))
												 .toInstant();
			return Date.from(localDateTime);
		}catch(DateTimeParseException e){
			logger.debug("can't parse time[{}] in the format of the zone[{}] and dateTime pattern[{}]", dateTime, zone, pattern);
			return null;
		}
	}
	
	public static Date parseDate(String date, String zone, String pattern){
		try{
			Instant localDateTime = LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern))
											 .atStartOfDay(ZoneId.of(zone))
											 .toInstant();
			return Date.from(localDateTime);
		}catch(DateTimeParseException e){
			logger.debug("can't parse time[{}] in the format of the zone[{}] and dateTime pattern[{}]", date, zone, pattern);
			return null;
		}
	}
}
