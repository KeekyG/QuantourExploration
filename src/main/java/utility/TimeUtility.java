package utility;

import java.util.Date;

public class TimeUtility {

	private static long oneDay = 86400000L;
	
	public static Date getDayBefore(Date date) {
		return new Date(date.getTime() - oneDay);
	}
	
	public static Date getDayAfter(Date date) {
		return new Date(date.getTime() + oneDay);
	}
	
}
