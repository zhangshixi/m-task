package com.mtask.util;

import java.util.Calendar;
import java.util.Date;

public final class DateUtil {

	private DateUtil() {
	}
	
	public static Date getCurrentTime() {
		return Calendar.getInstance().getTime();
	}
	
}
