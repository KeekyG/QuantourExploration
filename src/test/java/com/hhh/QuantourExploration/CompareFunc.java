package com.hhh.QuantourExploration;

import java.util.Date;

import javax.swing.JPanel;

/**
 * compare two different stocks and draw a pic to show their difference
 * @author KeekyG
 *
 */
public interface CompareFunc {
	
	public void drawDiff(String stock1, String stock2, Date day1, Date day2);
	public JPanel drawLastValue();
	public double getMaxValue(String stock);
	public double getMinValue(String stock);
	public JPanel drawLogValue();
}
