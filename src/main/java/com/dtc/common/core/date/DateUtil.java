package com.dtc.common.core.date;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

//TODO 升版到 Java8 後改用 JSR-310
public class DateUtil {
	private static GregorianCalendar calendar = new GregorianCalendar();

	public static Date getStartDate(Date date) {
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));
		return (Date) calendar.getTime().clone();
	}

	public static Date getEndDate(Date date) {
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));
		return (Date) calendar.getTime().clone();
	}
}