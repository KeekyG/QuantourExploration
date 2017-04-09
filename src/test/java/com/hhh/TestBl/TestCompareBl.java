package com.hhh.TestBl;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.junit.Before;
import org.junit.Test;

import bl.CompareBl;
import blService.CompareBlService;

public class TestCompareBl {
		
	private CompareBlService CompareBlTest;
	private Date day1, day2;

	@Before
	public void setUp() throws Exception {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yy/dd");
		day2 = simpleDateFormat.parse("04/10/14");
		day1 = simpleDateFormat.parse("04/1/14");			
		CompareBlTest = new CompareBl("1", "153", day1, day2);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void test() {
		JPanel panel1 = CompareBlTest.drawLastValue();
		JPanel panel2 = CompareBlTest.drawLogValue();
		JFrame frame = new JFrame("TestDraw");
		frame.add(panel1);
		frame.add(panel2);
		frame.setVisible(true);
		double Max = CompareBlTest.getMaxValue("1");
		double Min = CompareBlTest.getMinValue("1");
		assertEquals(3.4, Max, 0.0);
		assertEquals(3.1, Min, 0.0);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}

}

