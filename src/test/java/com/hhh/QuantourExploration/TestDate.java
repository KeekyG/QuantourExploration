package com.hhh.QuantourExploration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {

	public static void main(String[] args) {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yy");
		String date = "11/15/10";
		Date date2 = null;
		try {
			date2 = simpleDateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(date2);

	}

}
