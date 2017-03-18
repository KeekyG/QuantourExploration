package bl;

import java.awt.Color;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.SegmentedTimeline;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.ohlc.OHLCSeries;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;

import blService.KMapBlService;
import vo.ShareLineVO;

public class KMapBl implements KMapBlService{
	
	
	public void kTest(ShareLineVO shareLineVO){
		
		double avg5=0.0,avg10=0.0,avg20=0.0,avg30=0.0,avg60=0.0;
	    
	    OHLCSeriesCollection seriesCollection = new OHLCSeriesCollection();//定义k线图数据集
        OHLCSeries series1 = new OHLCSeries("");//数据序列 阴
        OHLCSeries series2 = new OHLCSeries("");//数据序列 阳

    

        TimeSeriesCollection volSeriesCollection = new TimeSeriesCollection();//保留成交量数据的集合
        TimeSeries series3 = new TimeSeries("");//对应时间成交量数据 阴
        TimeSeries series4 = new TimeSeries("");//对应时间成交量数据 阳
        
     
        
        for(int i = 0; i < shareLineVO.size(); i++){
        	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yy");
        	Date date = shareLineVO.get(i).getDate();
        	String s2 = simpleDateFormat.format(date);
        	String s3 = s2.substring(s2.length()-2,s2.length());
        	int a = Integer.parseInt(s3)+2000;
        	if(shareLineVO.get(i).getClose()>shareLineVO.get(i).getOpen()){
        		series2.add(new Day(shareLineVO.get(i).getDate().getDate(),shareLineVO.get(i).getDate().getMonth()+1,a), shareLineVO.get(i).getOpen(), shareLineVO.get(i).getHigh(), shareLineVO.get(i).getLow(), shareLineVO.get(i).getClose());
        		series4.add(new Day(shareLineVO.get(i).getDate().getDate(),shareLineVO.get(i).getDate().getMonth()+1,a), shareLineVO.get(i).getVolume());
        	}else{
        		series1.add(new Day(shareLineVO.get(i).getDate().getDate(),shareLineVO.get(i).getDate().getMonth()+1,a), shareLineVO.get(i).getOpen(), shareLineVO.get(i).getHigh(), shareLineVO.get(i).getLow(), shareLineVO.get(i).getClose());
        		series3.add(new Day(shareLineVO.get(i).getDate().getDate(),shareLineVO.get(i).getDate().getMonth()+1,a), shareLineVO.get(i).getVolume());
        	}
        }//添加数据
        
        
        //k线图数据
        seriesCollection.addSeries(series1);
        seriesCollection.addSeries(series2);
        //成交量数据
        volSeriesCollection.addSeries(series3);
        volSeriesCollection.addSeries(series4);
        
        
        
        
        
       

        

        TimeSeriesCollection lineSeriesConllection = new TimeSeriesCollection();//保留均线图数据的集合
        TimeSeries series5 = new TimeSeries("MA5");//对应时间成交量数据，5天
        TimeSeries series6 = new TimeSeries("MA10");//对应时间成交量数据，10天
        TimeSeries series7 = new TimeSeries("MA20");//对应时间成交量数据，20天
        TimeSeries series8 = new TimeSeries("MA30");//对应时间成交量数据，30天
        TimeSeries series9 = new TimeSeries("MA60");//对应时间成交量数据，60天

        
        //添加均线图5天数据
        for(int i = 4; i < shareLineVO.size(); i++){
                avg5=0.0;
        	    for(int j=i-4;j<i+1;j++){
        	    	avg5=avg5+shareLineVO.get(j).getClose();
        	    }
        	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yy");
            	Date date = shareLineVO.get(i).getDate();
            	String s2 = simpleDateFormat.format(date);
            	String s3 = s2.substring(s2.length()-2,s2.length());
            	int a = Integer.parseInt(s3)+2000;
                series5.add(new Day(shareLineVO.get(i).getDate().getDate(),shareLineVO.get(i).getDate().getMonth()+1,
                		a),avg5/5);
            
        }
        
        //添加均线图10天数据
        for(int i = 9; i < shareLineVO.size(); i++){
        	avg10=0.0;
    	    for(int j=i-9;j<i+1;j++){
    	    	avg10=avg10+shareLineVO.get(j).getClose();
    	    }
    	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yy");
        	Date date = shareLineVO.get(i).getDate();
        	String s2 = simpleDateFormat.format(date);
        	String s3 = s2.substring(s2.length()-2,s2.length());
        	int a = Integer.parseInt(s3)+2000;
            series6.addOrUpdate(new Day(shareLineVO.get(i).getDate().getDate(),shareLineVO.get(i).getDate().getMonth()+1,
            		a),avg10/10);
        
        }
        //添加均线图20天数据
        for(int i = 19; i < shareLineVO.size(); i++){
        	avg20=0.0;
    	    for(int j=i-19;j<i+1;j++){
    	    	avg20=avg20+shareLineVO.get(j).getClose();
    	    }
    	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yy");
        	Date date = shareLineVO.get(i).getDate();
        	String s2 = simpleDateFormat.format(date);
        	String s3 = s2.substring(s2.length()-2,s2.length());
        	int a = Integer.parseInt(s3)+2000;
            series7.addOrUpdate(new Day(shareLineVO.get(i).getDate().getDay(),shareLineVO.get(i).getDate().getMonth()+1,
            		a),avg20/20);
        
        }  
        //添加均线图30天数据
        for(int i = 29; i < shareLineVO.size(); i++){
        	avg30=0.0;
    	    for(int j=i-29;j<i+1;j++){
    	    	avg30=avg30+shareLineVO.get(j).getClose();
    	    }
    	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yy");
        	Date date = shareLineVO.get(i).getDate();
        	String s2 = simpleDateFormat.format(date);
        	String s3 = s2.substring(s2.length()-2,s2.length());
        	int a = Integer.parseInt(s3)+2000;
            series8.addOrUpdate(new Day(shareLineVO.get(i).getDate().getDate(),shareLineVO.get(i).getDate().getMonth()+1,
            		a),avg30/30);
        
    }
        //添加均线图60天数据
        for(int i = 59; i < shareLineVO.size(); i++){
        	avg60=0.0;
    	    for(int j=i-59;j<i+1;j++){
    	    	avg60=avg60+shareLineVO.get(j).getClose();
    	    }
    	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yy");
        	Date date = shareLineVO.get(i).getDate();
        	String s2 = simpleDateFormat.format(date);
        	String s3 = s2.substring(s2.length()-2,s2.length());
        	int a = Integer.parseInt(s3)+2000;
            series9.addOrUpdate(new Day(shareLineVO.get(i).getDate().getDate(),shareLineVO.get(i).getDate().getMonth()+1,
            		a),avg60/60);
        
    }
        
        
        lineSeriesConllection.addSeries(series5);
        lineSeriesConllection.addSeries(series6);
        lineSeriesConllection.addSeries(series7);
        lineSeriesConllection.addSeries(series8);
        lineSeriesConllection.addSeries(series9);
        //设置K线图的画图器
        CandlestickRenderer candlestickRender = new CandlestickRenderer();
        candlestickRender.setUpPaint(Color.BLACK);//设置股票上涨的K线图颜色
        candlestickRender.setDownPaint(Color.CYAN);//设置股票下跌的K线图颜色
        candlestickRender.setSeriesPaint(1, Color.RED);//设置股票下跌的K线图颜色
        candlestickRender.setSeriesPaint(0, Color.CYAN);//设置股票上涨的K线图颜色
        candlestickRender.setAutoWidthMethod(CandlestickRenderer.WIDTHMETHOD_AVERAGE);//设置如何对K线图的宽度进行设定
        candlestickRender.setAutoWidthGap(0.001);//设置各个K线图之间的间隔

       
        
        //设置k线图y轴参数
        double max=0.0,min=100.0;
        for(int i = 0; i < shareLineVO.size(); i++){
        	if(shareLineVO.get(i).getHigh()>max){
        		max=shareLineVO.get(i).getHigh();
        	} 
        	if(shareLineVO.get(i).getLow()<min){
        		min=shareLineVO.get(i).getLow();
        	}
        }
        NumberAxis y1Axis=new NumberAxis();//设置Y轴，为数值,后面的设置，参考上面的y轴设置
        y1Axis.setAutoRange(false);//设置不采用自动设置数据范围
        y1Axis.setLabelFont(new Font("微软雅黑", Font.BOLD, 12));
        y1Axis.setRange(min*0.9, max*1.1);// 设定y轴值的范围，比最低值要低一些，比最大值要大一些，这样图形看起来会美观些
        y1Axis.setTickUnit(new NumberTickUnit((max*1.1-min*0.9)/10));// 设置刻度显示的密度
        y1Axis.setAutoTickUnitSelection(true);//数据轴的数据标签是否自动确定（默认为true）
   
        
        //设置k线图x轴，也就是时间轴
        DateAxis domainAxis = new DateAxis();
        domainAxis.setAutoRange(false);//设置不采用自动设置时间范围
        //设置时间范围，注意，最大和最小时间设置时需要+ - 。否则时间刻度无法显示
        domainAxis.setRange(shareLineVO.getBeginDate(), shareLineVO.getEndDate());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");            
        domainAxis.setAutoTickUnitSelection(false);//设置不采用自动选择刻度值
        domainAxis.setTickMarkPosition(DateTickMarkPosition.START);//设置标记的位置
        domainAxis.setStandardTickUnits(DateAxis.createStandardDateTickUnits());// 设置标准的时间刻度单位
        domainAxis.setTickUnit(new DateTickUnit(DateTickUnit.DAY,7));// 设置时间刻度的间隔，一般以周为单位
        domainAxis.setDateFormatOverride(df);//设置时间格式
        domainAxis.setTimeline(SegmentedTimeline.newMondayThroughFridayTimeline());
        
        
        //设置均线图画图器
        XYLineAndShapeRenderer lineAndShapeRenderer = new XYLineAndShapeRenderer();
        lineAndShapeRenderer.setBaseItemLabelsVisible(true);
        lineAndShapeRenderer.setSeriesShapesVisible(0, false);//设置不显示数据点模型
        lineAndShapeRenderer.setSeriesShapesVisible(1, false);
        lineAndShapeRenderer.setSeriesShapesVisible(2, false);
        lineAndShapeRenderer.setSeriesShapesVisible(3, false);
        lineAndShapeRenderer.setSeriesShapesVisible(4, false);
        lineAndShapeRenderer.setSeriesPaint(0, Color.WHITE);//设置均线颜色
        lineAndShapeRenderer.setSeriesPaint(1, Color.YELLOW);
        lineAndShapeRenderer.setSeriesPaint(2, Color.MAGENTA);
        lineAndShapeRenderer.setSeriesPaint(3, Color.GREEN);
        lineAndShapeRenderer.setSeriesPaint(4, Color.BLUE);
        

        
        
        
        
        //生成画图细节 第一个和最后一个参数这里需要设置为null，否则画板加载不同类型的数据时会有类型错误异常
        //可能是因为初始化时，构造器内会把统一数据集合设置为传参的数据集类型，画图器可能也是同样一个道理
        XYPlot plot = new XYPlot(null,domainAxis,y1Axis,null);
        plot.setBackgroundPaint(Color.BLACK);//设置曲线图背景色
        plot.setDomainGridlinesVisible(false);//不显示网格
        plot.setRangeGridlinePaint(Color.RED);//设置间距格线颜色为红色

        //将设置好的数据集合和画图器放入画板
        plot.setDataset(0, seriesCollection);
        plot.setRenderer(0, candlestickRender);
        plot.setDataset(1, lineSeriesConllection);
        plot.setRenderer(1, lineAndShapeRenderer);


        //设置柱状图参数
        XYBarRenderer barRenderer = new XYBarRenderer();

        barRenderer.setDrawBarOutline(true);//设置显示边框线
        barRenderer.setBarPainter(new StandardXYBarPainter());//取消渐变效果
        barRenderer.setMargin(0.3);//设置柱形图之间的间隔            
        barRenderer.setSeriesPaint(0, Color.BLACK);//设置柱子内部颜色
        barRenderer.setSeriesPaint(1, Color.CYAN);//设置柱子内部颜色
        barRenderer.setSeriesOutlinePaint(0, Color.RED);//设置柱子边框颜色
        barRenderer.setSeriesOutlinePaint(1, Color.CYAN);//设置柱子边框颜色
        barRenderer.setShadowVisible(false);//设置没有阴影

        //设置柱状图y轴参数
        NumberAxis y2Axis=new NumberAxis();//设置Y轴，为数值,后面的设置，参考上面的y轴设置
        y2Axis.setLabelFont(new Font("微软雅黑", Font.BOLD, 12));//设置y轴字体
        y2Axis.setAutoRange(true);//设置采用自动设置时间范围

        //这里不设置x轴，x轴参数依照k线图x轴为模板
        XYPlot plot2 = new XYPlot(volSeriesCollection, null, y2Axis, barRenderer);
        plot2.setBackgroundPaint(Color.BLACK);//设置曲线图背景色
        plot2.setDomainGridlinesVisible(false);//不显示网格
        plot2.setRangeGridlinePaint(Color.RED);//设置间距格线颜色为红色

        //建立一个恰当的联合图形区域对象，以x轴为共享轴
        CombinedDomainXYPlot domainXYPlot = new CombinedDomainXYPlot(domainAxis);//
        domainXYPlot.add(plot, 2);//添加图形区域对象，后面的数字是计算这个区域对象应该占据多大的区域2/3
        domainXYPlot.add(plot2, 1);//添加图形区域对象，后面的数字是计算这个区域对象应该占据多大的区域1/3
        domainXYPlot.setGap(10);//设置两个图形区域对象之间的间隔空间
        //生成图纸
        JFreeChart chart = new JFreeChart(shareLineVO.getName(), new Font("微软雅黑", Font.BOLD, 24), domainXYPlot, true);
        ChartFrame frame = new ChartFrame(shareLineVO.getName(), chart);
        frame.pack();
        frame.setVisible(true);
        

      
    }
    
    
}
