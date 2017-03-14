package com.hhh.QuantourExploration;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Test {
	public static void main(String args[]){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yy");
		Date day1 = null;
		Date day2 = null;
		try{	
			day1 = simpleDateFormat.parse("01/19/10");
			day2 = simpleDateFormat.parse("01/30/10");
		}catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		CompareFuncTest compareFuncTest = new CompareFuncTest();
		compareFuncTest.drawDiff("2103", "2220", day1, day2);
	}
}
