package com.hhh.QuantourExploration;

import java.awt.Dimension;
import java.sql.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.RefineryUtilities;

import com.hhh.QuantourExploration.LineChartDemo1;

public class CompareFuncTest implements CompareFunc{

	@Override
	public void drawDiff(String stock1, String stock2, Date day1, Date day2) {
		// TODO Auto-generated method stub
		LineChartDemo1 lineChartDemo1 = new LineChartDemo1(stock1, stock2, day1, day2);
		CategoryDataset dataset = lineChartDemo1.createDataset(stock1, stock2);
		JPanel jpanel = lineChartDemo1.createDemoPanel(dataset);
        jpanel.setPreferredSize(new Dimension(500, 270));
        JFrame frame = new JFrame("Differences between " + stock1 + " and " + stock2);
        frame.getContentPane().add(jpanel);
        frame.setVisible(true);
		lineChartDemo1.pack();
		RefineryUtilities.centerFrameOnScreen(lineChartDemo1);
		lineChartDemo1.setVisible(true);  
	}

	
}
