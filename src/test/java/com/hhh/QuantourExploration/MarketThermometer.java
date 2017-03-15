package com.hhh.QuantourExploration;

import java.awt.Font;
import java.awt.RenderingHints;
import java.util.Date;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import bl.MarketBl;
import blService.MarketBlService;
import vo.ThermometerVO;

public class MarketThermometer {

	public void drawThermometer(Date day){
		
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

	}
	
	private CategoryDataset creatDataset(){
		DefaultCategoryDataset defaultCategoryDataset = new DefaultCategoryDataset();
		return defaultCategoryDataset;
	}
	
	public JFreeChart creatPanel(CategoryDataset dataset){
		JFreeChart chart = ChartFactory.createBarChart("Market Theromometer",	//表格名字
				"名称",	//X轴名字
				"数量",	//Y轴名字
				dataset,	//data
				PlotOrientation.HORIZONTAL,	//图标方向
				true,	//是否显示图例
				true,	//是否显示提示信息
				false);	//是否显示urls
		return chart;
	}
}
