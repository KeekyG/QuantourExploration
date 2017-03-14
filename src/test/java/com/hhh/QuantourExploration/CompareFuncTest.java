package com.hhh.QuantourExploration;

import java.awt.Dimension;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.RefineryUtilities;

import com.hhh.QuantourExploration.LineChartDemo1;

public class CompareFuncTest implements CompareFunc{

	private LineChartDemo1 lineChartDemo1;
	private CategoryDataset dataset;
	@Override
	public void drawDiff(String stock1, String stock2, Date day1, Date day2) {
		// TODO Auto-generated method stub
		lineChartDemo1 = new LineChartDemo1(stock1, stock2, day1, day2);
		dataset = lineChartDemo1.createDataset(stock1, stock2);
		JPanel lastValue = drawLastValue(stock1, stock2, day1, day2);
		JPanel maxValue = drawMaxValue(stock1, stock2, day1, day2);
		JPanel minValue = drawMinValue(stock1, stock2, day1, day2);
		JPanel logValue = drawLogValue(stock1, stock2, day1, day2);
        JFrame frame = new JFrame("Differences between " + stock1 + " and " + stock2);
        frame.getContentPane().add(lastValue);
        frame.getContentPane().add(maxValue);
        frame.getContentPane().add(minValue);
        frame.getContentPane().add(logValue);
        frame.setVisible(true);
//		lineChartDemo1.pack(); 
	}

	/**
	 * 画出day1到day2期间股票的每日收盘价的比较折线图
	 * @param stock1
	 * @param stock2
	 * @param day1
	 * @param day2
	 * @return
	 */
	private JPanel drawLastValue(String stock1, String stock2, Date day1, Date day2){
		JPanel jpanel = lineChartDemo1.createDemoPanel(dataset);
        jpanel.setPreferredSize(new Dimension(500, 270));
        return jpanel;
	}
	/**
	 * 画出day1到day2期间股票的每日最高价的比较折线图
	 * @param stock1
	 * @param stock2
	 * @param day1
	 * @param day2
	 * @return
	 */
	private JPanel drawMaxValue(String stock1, String stock2, Date day1, Date day2){
		return new JPanel();
	}
	
	/**
	 * 画出day1到day2期间股票的每日最低价的比较折线图
	 * @param stock1
	 * @param stock2
	 * @param day1
	 * @param day2
	 * @return
	 */
	private JPanel drawMinValue(String stock1, String stock2, Date day1, Date day2){
		return new JPanel();
	}
	
	/**
	 * 画出day1到day2期间股票的每日对数收益率的比较折线图
	 * @param stock1
	 * @param stock2
	 * @param day1
	 * @param day2
	 * @return
	 */
	private JPanel drawLogValue(String stock1, String stock2, Date day1, Date day2){
		return new JPanel();
	}
}
