package com.hhh.two;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.text.DecimalFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import vo.IncomeLineVO;
import vo.PointVO;

public class RelativeRatePanel {
	
	private IncomeLineVO strategyVO;
	private IncomeLineVO standardVO;
	
//	public static void main(String args[]){
//		CumulativeRatePanel cumulativeRatePanel = new CumulativeRatePanel();
//		JFrame frame = new JFrame("");
//		frame.getContentPane().add(cumulativeRatePanel.createCumulativeRatePanel());
//		frame.setVisible(true);
//		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
//		frame.setSize(800, 600);
//		frame.setLocation(300, 400);
//	}
//	
	/**
	 * 构造函数
	 * @param strategyVo	策略数据
	 * @param standardVo	基准数据
	 */
	public RelativeRatePanel(IncomeLineVO strategyVo, IncomeLineVO standardVo) {
		// TODO Auto-generated constructor stub
		this.standardVO = standardVo;
		this.strategyVO = strategyVo;
	}
	
	/**
	 * 将图绘制为jpanel
	 * @return 
	 */
	private JPanel createCumulativeRatePanel(){
		
		return new ChartPanel(createChart());
	}
	
	private CategoryDataset createDateset(){
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(PointVO pointVO:strategyVO){
			dataset.addValue(pointVO.getRate(), "策略", pointVO.getDate());
		}
		for(PointVO pointVO:standardVO){
			dataset.addValue(pointVO.getRate(), "基准", pointVO.getDate());
		}
//		ArrayList<Integer> data = setData();
//		int date = 0;
//		for(int temp: data){
//			dataset.addValue(temp, "策略", String.valueOf(++date));
//		}
//		date = 0;
//		data = setData();
//		for(int temp:data){
//			dataset.addValue(-temp, "基准", String.valueOf(++date));
//		}
		return dataset;
	}
	
//	/**
//	 * 随机生成数据进行测试代码完成后记得删除
//	 * @return
//	 */
//	private ArrayList<Integer> setData(){
//		ArrayList<Integer> data = new ArrayList<>();
//		for(int i = 0; i < 50;i++){
//			data.add((int)(Math.random()*100));
//		}
//		return data;
//	}
	
	private JFreeChart createChart(){

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
        
        CategoryDataset categorydataset= createDateset();
        
        JFreeChart jfreechart = ChartFactory.createBarChart(  
                "相对指数收益分布率",// 图表标题  
                "", // 主轴标签（x轴）  
                "",// 范围轴标签（y轴）  
                categorydataset, // 数据集  
                PlotOrientation.VERTICAL,// 方向  
                true, // 是否包含图例  
                true, // 提示信息是否显示  
                false);// 是否使用urls  6
        // 改变图表的背景颜色  
        jfreechart.setBackgroundPaint(Color.white);  
          
        CategoryPlot categoryplot = (CategoryPlot) jfreechart.getPlot();  
        categoryplot.setBackgroundPaint(Color.lightGray);  
        categoryplot.setRangeGridlinePaint(Color.white);  
        categoryplot.setRangeGridlinesVisible(true);  
  
        //修改范围轴。 将默认刻度值 （允许显示小数） 改成只显示整数的刻度值。  
        NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();  
        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());  
          
        // 设置X轴上的Lable让其45度倾斜   
        CategoryAxis domainAxis = categoryplot.getDomainAxis();  
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); // 设置X轴上的Lable让其45度倾斜       
         domainAxis.setLowerMargin(0.0);   // 设置距离图片左端距离   
         domainAxis.setUpperMargin(0.0);  // 设置距离图片右端距离   
          
        //设置y轴上的数值大小
         NumberAxis numberAxis = (NumberAxis) categoryplot.getRangeAxis();
         numberAxis.setAutoTickUnitSelection(false);
         double unit=10d;//刻度的长度
         NumberTickUnit ntu= new NumberTickUnit(unit);
         numberAxis.setTickUnit(ntu);
//         DateAxis xAxis = new DateAxis("???");
//         xAxis.setRange(-0.5000, 0.20000);
//         xAxis.setAutoTickUnitSelection(false);
//         xAxis.setTickUnit(new DateTickUnit(DateTickUnit.MINUTE,20));
//         categoryplot.setDomainAxis(xAxis);
            
        LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer) categoryplot  
                .getRenderer();  
        lineandshaperenderer.setDrawOutlines(true);  
        lineandshaperenderer.setUseFillPaint(true);  
        lineandshaperenderer.setBaseFillPaint(Color.white);  
        lineandshaperenderer.setSeriesStroke(0, new BasicStroke(3.0F));  
        lineandshaperenderer.setSeriesOutlineStroke(0, new BasicStroke(2.0F));  
        lineandshaperenderer.setSeriesShape(0, new Ellipse2D.Double(-5.0, -5.0,  
                10.0, 10.0));  
          
        // 显示数据值  
        DecimalFormat decimalformat1 = new DecimalFormat("##.##");// 数据点显示数据值的格式  
        lineandshaperenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator(  
                "{2}", decimalformat1));//  设置数据项标签的生成器  
        lineandshaperenderer.setBaseItemLabelsVisible(true);// 基本项标签显示  
        lineandshaperenderer.setBaseShapesFilled(true);// 在数据点显示实心的小图标  
        
        return jfreechart;
	}
}
