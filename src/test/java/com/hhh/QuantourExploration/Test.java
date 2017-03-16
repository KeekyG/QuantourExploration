package com.hhh.QuantourExploration;

import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.GridLayout;
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
		CompareFuncTest compareFuncTest = new CompareFuncTest("2103", "2220", day1, day2);
		
		JFrame frame = new JFrame("Differences between " + "2103" + " and " + "2220");
//		
//		JPanel lastValue = compareFuncTest.drawLastValue();
//		JPanel logValue = compareFuncTest.drawLogValue();
//        frame.getContentPane().add(logValue);
//        frame.getContentPane().add(lastValue);
//        frame.setLayout(new GridLayout(1, 1));
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		
		MarketThermometer marketThermometer = new MarketThermometer();
		JPanel panel = marketThermometer.drawThermometer(day1);
		panel.setSize(500, 600);
		frame.add(panel);
	}
}
