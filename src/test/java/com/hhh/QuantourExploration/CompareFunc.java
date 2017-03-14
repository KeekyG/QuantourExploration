package com.hhh.QuantourExploration;

import java.util.Date;

/**
 * compare two different stocks and draw a pic to show their difference
 * @author KeekyG
 *
 */
public interface CompareFunc {
	public void drawDiff(String stock1, String stock2, Date day1, Date day2);
}
