package frameWork;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class UtilityMethods {

	public static String formatNumber(String number){
		
		double dblNum = Double.parseDouble(number);		
		
		int intNum = (int) dblNum;
	
		return String.valueOf(intNum);
		
	}
	public static void staticWait(int milliSeconds){
		try {
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static String formatLongNumber(String number){
		
		double dblNum = Double.parseDouble(number);		
		System.out.println(dblNum);
		long intNum = (long) dblNum;
		System.out.println(intNum);
		return String.valueOf(intNum);
		
	}
	
	
	public static void createFolder(String foldPath){
		File f = new File(foldPath);
		
		if (!f.exists()){
			f.mkdirs();
		}
	}
	
	public static String getCurrentTimeStamp(){
		
		Date dt = new Date();
		
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		
		
		int hour = c.get(Calendar.HOUR);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);
		int month = c.get(Calendar.MONTH)+1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		
		String timeStamp =  ""+hour+minute+second+month+day;
		
		return timeStamp;
		
		
	}
	
	
	public static String formatDate(Date dt, String dateFormat){
		
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		
		String formattedDate = sdf.format(dt);
		
		return formattedDate;
		
	}
	
	
	public static Date convertTODate(String date, String dateFormat){
		
		Date dt = null;
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		
		try {
			dt = sdf.parse(date);
		} catch (ParseException e) {
			System.out.println("given date : " + date + " is not in the format : " + dateFormat);
		}
		
		return dt;
		
	}
	
	
	public static Date getPastDate(DateInterval interval,int total ){
		Date dt = new Date();
				
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		
		switch (interval) {
		case DAY:
			c.add(Calendar.DAY_OF_MONTH, -total);
			break;
			
		case MONTH:
			c.add(Calendar.MONTH, -total);
			break;
			
		case YEAR:
			c.add(Calendar.YEAR, -total);
			break;
		
		case HOUR:
			c.add(Calendar.HOUR, -total);
			break;
		case MINUTE:
			c.add(Calendar.MINUTE, -total);
			break;
			
		default:
			c.add(Calendar.SECOND, -total);
			break;
		}
		
		dt = c.getTime();
		
		return dt;
		
	}
	
	public static Date getFutureDate(DateInterval interval,int total ){
		Date dt = new Date();
				
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		
		switch (interval) {
		case DAY:
			c.add(Calendar.DAY_OF_MONTH, total);
			break;
			
		case MONTH:
			c.add(Calendar.MONTH, total);
			break;
			
		case YEAR:
			c.add(Calendar.YEAR, total);
			break;
		
		case HOUR:
			c.add(Calendar.HOUR, total);
			break;
		case MINUTE:
			c.add(Calendar.MINUTE, total);
			break;
			
		default:
			c.add(Calendar.SECOND, total);
			break;
		}
		
		dt = c.getTime();
		
		return dt;
		
	}
	
	
	public static Date getFutureDate_businessDays(int numOfDays){
		Date dt = new Date();		
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		
		for (int i = 1; i <= numOfDays;i++){
			c.add(Calendar.DAY_OF_MONTH, 1);			
			int dayInWeek = c.get(Calendar.DAY_OF_WEEK);
			if (dayInWeek == 1 || dayInWeek == 7){
				i--;
			}
		}
		
		dt = c.getTime();
		return dt;
		
	}
	
	public static Date getPastDate_businessDays(int numOfDays){
		Date dt = new Date();		
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		
		for (int i = numOfDays; i >= 1;i--){
			c.add(Calendar.DAY_OF_MONTH, -1);			
			int dayInWeek = c.get(Calendar.DAY_OF_WEEK);
			if (dayInWeek == 1 || dayInWeek == 7){
				i++;
			}
		}
		
		dt = c.getTime();
		return dt;
		
	}
	
	public static HashMap<String, String> getDateParts(Date dt){
		
		HashMap<String, String> dateParts = new HashMap<>();
		
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		
		dateParts.put("MONTH_NUMBER", String.valueOf(c.get(Calendar.MONTH)+1) );
		dateParts.put("MONTH_NAME", c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH));
		dateParts.put("YEAR", String.valueOf(c.get(Calendar.YEAR)) );
		dateParts.put("DAY_NUMBER", String.valueOf(c.get(Calendar.DAY_OF_MONTH)) );		
		dateParts.put("DAY_NAME", c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.ENGLISH) );
		
		return dateParts;	
		
		
	}
}



