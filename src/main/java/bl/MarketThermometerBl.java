package bl;

import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import java.util.Date;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import blService.MarketThermometerBlService;
import vo.ThermometerVO;

public class MarketThermometerBl implements MarketThermometerBlService{
	
	private MarketBl marketBl = new MarketBl();
	private ThermometerVO thermometerVO;
        
	public JPanel drawThermometer(Date day){
		
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

        thermometerVO = marketBl.getMarketThermo(day);
        
//        thermometerVO = new ThermometerVO(day, 500, 60, 50, 40, 40, 40, 40);
        CategoryDataset dataset = creatDataset(day);
		JFreeChart chart = creatChart(dataset);
		JPanel panel = new ChartPanel(chart);
		return panel;
	}
	
	/**
	 *  计算总交易量
	 * @return 当日总交易量
	 */
	public double SumOfTrans(){
		return thermometerVO.getTotalVolume();
	}
	/**
	 * 设置当前日期的温度计的dataset
	 * @param day 要搜索的日期
	 * @return
	 */
	private CategoryDataset creatDataset(Date day){
		DefaultCategoryDataset defaultCategoryDataset = new DefaultCategoryDataset();
		defaultCategoryDataset.addValue(thermometerVO.getUpNum(), "","涨停股票数");
		defaultCategoryDataset.addValue(thermometerVO.getDownNum(), "","跌停股票数");
		defaultCategoryDataset.addValue(thermometerVO.getFivePercentUpNum(), "","涨幅超过5%的股票数");
		defaultCategoryDataset.addValue(thermometerVO.getFivePercentDownNum(), "","跌幅超过5%的股票数");
		defaultCategoryDataset.addValue(thermometerVO.getFivePercentBiggerNum(), "","开盘‐收盘大于5%*上一个交易日收盘价的股票个数");
		defaultCategoryDataset.addValue(thermometerVO.getFivePercentSmallerNum(), "","开盘‐收盘小于‐5%*上一个交易日收盘价的股票个数");
		return defaultCategoryDataset;
	}
	
	
	
	/**
	 * 根据dataset中的数据进行画图
	 * @param dataset
	 * @return
	 */
	private JFreeChart creatChart(CategoryDataset dataset){
		JFreeChart chart = ChartFactory.createBarChart("Market Theromometer",	//表格名字
				"名称",	//X轴名字
				"数量",	//Y轴名字
				dataset,	//data
				PlotOrientation.HORIZONTAL,	//图标方向
				true,	//是否显示图例
				false,	//是否显示提示信息
				false);	//是否显示urls
		
		chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
		chart.setBorderPaint(Color.BLACK);
		return chart;
	}
}
