package com.hhh.QuantourExploration;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Date;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;

import com.hhh.QuantourExploration.LineChartDemo1;

import vo.StockShareVO;

public class CompareFuncTest implements CompareFunc{

	private LineChartDemo1 lineChartDemo1;
	private ArrayList<StockShareVO> stockShareVOs1;
	private ArrayList<StockShareVO> stockShareVOs2;
	
	public CompareFuncTest(String stock1, String stock2, Date day1, Date day2) {
		// TODO Auto-generated constructor stub
		lineChartDemo1 = new LineChartDemo1();
		stockShareVOs1 = lineChartDemo1.getStockLists(stock1, day1, day2);
		stockShareVOs2 = lineChartDemo1.getStockLists(stock2, day1, day2);
	}
	@Override
	public void drawDiff(String stock1, String stock2, Date day1, Date day2) {
		// TODO Auto-generated method stub
		
		JPanel lastValue = drawLastValue();
		JPanel logValue = drawLogValue();
		String var1 = lineChartDemo1.calVariance1();
		String var2 = lineChartDemo1.calVariance2();
		JTextPane textPane = new JTextPane();
		textPane.setText("Variance of " + stock1 + "'s Log Field is " + var1 + "\nVariance of " + 
										stock2 + "'s Log Field is " + var2);
		logValue.add(textPane);
        JFrame frame = new JFrame("Differences between " + stock1 + " and " + stock2);
        frame.setLayout(new GridLayout(2, 2));
        frame.getContentPane().add(lastValue);
        frame.setSize(800, 600);
        frame.getContentPane().add(logValue);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}

	public JPanel drawLastValue(){
		CategoryDataset dataset = lineChartDemo1.createLastDataset(stockShareVOs1,stockShareVOs2);
		JPanel jpanel = createDemoPanel(dataset, "Differences on Closing Price");
        jpanel.setPreferredSize(new Dimension(500, 270));
        return jpanel;
	}
	
	/**
	 * 根据股票编号返回日期内最大值
	 * @param stock
	 * @return
	 */
	public double getMaxValue(String stock){
		if(stock.equals(stockShareVOs1.get(0).getCode())){
			return lineChartDemo1.getMax(stockShareVOs1);
		}else{
			return lineChartDemo1.getMax(stockShareVOs2);
		}
	}
	
	/**
	 * 根据股票编号返回日期内最小值
	 * @param stock
	 * @return
	 */
	public double getMinValue(String stock){
		if(stock.equals(stockShareVOs1.get(0).getCode())){
			return lineChartDemo1.getMin(stockShareVOs1);
		}else{
			return lineChartDemo1.getMin(stockShareVOs2);
		}
	}
	
	/**
	 * 返回对数收益率的表格
	 */
	public JPanel drawLogValue(){
		CategoryDataset dataset = lineChartDemo1.createLogDataset(stockShareVOs1, stockShareVOs2);
		JPanel jpanel = createDemoPanel(dataset, "Differences on Logarithmic Yield");
        jpanel.setPreferredSize(new Dimension(500, 270));
        return jpanel;
	}
	
	/**
	 * 根据dataset画表格
	 * @param categorydataset
	 * @param title
	 * @return
	 */
	 private JFreeChart createChart(CategoryDataset categorydataset, String title) {
		 

			//修改文字编码格式
	        StandardChartTheme theme = new StandardChartTheme("unicode") {  
	            public void apply(JFreeChart chart) {  
	                chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,  
	                        RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);  
	                super.apply(chart);  
	            }  
	        };
	        theme.setExtraLargeFont(new Font("微软雅黑", Font.PLAIN, 20));  
	        theme.setLargeFont(new Font("微软雅黑", Font.PLAIN, 14));  
	        theme.setRegularFont(new Font("微软雅黑", Font.PLAIN, 12));  
	        theme.setSmallFont(new Font("微软雅黑", Font.PLAIN, 10));  
	        ChartFactory.setChartTheme(theme); 
	        
	        JFreeChart jfreechart = ChartFactory.createLineChart(  
	                title,// 图表标题  
	                "Date", // 主轴标签（x轴）  
	                "Price",// 范围轴标签（y轴）  
	                categorydataset, // 数据集  
	                PlotOrientation.VERTICAL,// 方向  
	                true, // 是否包含图例  
	                true, // 提示信息是否显示  
	                false);// 是否使用urls  
	        
	        // 改变图表的背景颜色  
	        jfreechart.setBackgroundPaint(Color.white);  
	          
	        CategoryPlot categoryplot = (CategoryPlot) jfreechart.getPlot();  
	        categoryplot.setBackgroundPaint(Color.lightGray);  
	        categoryplot.setRangeGridlinePaint(Color.white);  
	        categoryplot.setRangeGridlinesVisible(false);  
	  
	        //修改范围轴。 将默认刻度值 （允许显示小数） 改成只显示整数的刻度值。  
	        NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();  
	        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());  
	          
	        // 设置X轴上的Lable让其45度倾斜   
	        CategoryAxis domainAxis = categoryplot.getDomainAxis();  
	        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); // 设置X轴上的Lable让其45度倾斜       
	         domainAxis.setLowerMargin(0.0);   // 设置距离图片左端距离   
	         domainAxis.setUpperMargin(0.0);  // 设置距离图片右端距离   
	          
	            
	            
	        LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer) categoryplot  
	                .getRenderer();  
	        lineandshaperenderer.setShapesVisible(true);  
	        lineandshaperenderer.setDrawOutlines(true);  
	        lineandshaperenderer.setUseFillPaint(true);  
	        lineandshaperenderer.setBaseFillPaint(Color.white);  
	        lineandshaperenderer.setSeriesStroke(0, new BasicStroke(3.0F));  
	        lineandshaperenderer.setSeriesOutlineStroke(0, new BasicStroke(2.0F));  
	        lineandshaperenderer.setSeriesShape(0, new Ellipse2D.Double(-5.0, -5.0,  
	                10.0, 10.0));  
	        lineandshaperenderer.setItemMargin(0.4); //设置x轴每个值的间距（不起作用？？）  
	          
	        // 显示数据值  
	        DecimalFormat decimalformat1 = new DecimalFormat("##.##");// 数据点显示数据值的格式  
	        lineandshaperenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator(  
	                "{2}", decimalformat1));//  设置数据项标签的生成器  
	        lineandshaperenderer.setBaseItemLabelsVisible(true);// 基本项标签显示  
	        lineandshaperenderer.setBaseShapesFilled(true);// 在数据点显示实心的小图标  
	                  
	          
	        return jfreechart;  
	    }
	    
	    private JPanel createDemoPanel(CategoryDataset categoryDataset, String title) {
	        JFreeChart jfreechart = createChart(categoryDataset, title);                  
	        return new ChartPanel(jfreechart);
	    }
}
