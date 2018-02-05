package com.loe.dms.spring.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateUtil {

	public static Timestamp getCurrentTime() {
		java.util.Date dt = new java.util.Date();

		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String currentTime = sdf.format(dt);
		return Timestamp.valueOf(currentTime);
	}

	public static Date convertStringToDate(String dateString) {
		java.util.Date inDate = null;
		Date outDate = null;
		String formatteddate = null;
		DateFormat incomingDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat dbDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			inDate = incomingDateFormat.parse(dateString);
			formatteddate = dbDateFormat.format(inDate);
			outDate = Date.valueOf(formatteddate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return outDate;
	}

	public static boolean isValidDate(String dateString) {
		java.util.Date inDate = null;
		boolean isValidateDate = false;
		String formatteddate = null;
		if (dateString == null || dateString.equals("")) {
			return isValidateDate;
		}
		DateFormat incomingDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		try {
			inDate = incomingDateFormat.parse(dateString);
			formatteddate = incomingDateFormat.format(inDate);
			if (formatteddate.equals(dateString)) {
				isValidateDate = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return isValidateDate;
	}

}
