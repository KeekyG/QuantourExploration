package com.hhh.QuantourExploration;

import java.sql.Date;

public class Test {
	@SuppressWarnings("deprecation")
	public static void main(String args[]){
		Date day1 = new Date(2012, 3, 5);
		Date day2 = new Date(2012, 3, 15);
		CompareFuncTest compareFuncTest = new CompareFuncTest();
		compareFuncTest.drawDiff("SweetStar", "Wonder", day1, day2);
	}
}
