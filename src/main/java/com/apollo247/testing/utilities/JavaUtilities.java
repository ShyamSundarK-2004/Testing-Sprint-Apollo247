package com.apollo247.testing.utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtilities {

	// java utlitility

	// Generate random number
	public int getRandomNumber(int range) {
		Random randomNumber = new Random();
		int randomNum = randomNumber.nextInt(range);
		return randomNum;
	}

	// Generate current date
	public String getCurrentDate(String dateFormat) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(date);
	}

	// Counting days (add days to current date)
	public String getFutureDate(int days) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		sdf.format(date);
		Calendar cal = sdf.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String requiredDays = sdf.format(cal.getTime());
		return requiredDays;
	}
	
}