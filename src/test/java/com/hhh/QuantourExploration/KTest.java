package com.hhh.QuantourExploration;

public class KTest {
public static void main(String []args){
		
	    Vo vo=new vo(); //vo(date,open,high,low,close,volume)
	    ArrayList<VO> list = new ArrayList<VO>(); 
		
	    
	    OHLCSeriesCollection seriesCollection = new OHLCSeriesCollection();//定义k线图数据集
        OHLCSeries series1 = new OHLCSeries("");//数据序列 阴
        OHLCSeries series2 = new OHLCSeries("");//数据序列 阳

    

        TimeSeriesCollection volSeriesCollection = new TimeSeriesCollection();//保留成交量数据的集合
        TimeSeries series3 = new TimeSeries("");//对应时间成交量数据 阴
        TimeSeries series4 = new TimeSeries("");//对应时间成交量数据 阳
        
        
        int days=list.size();//统计共有多少天 设置x轴
        String startDate = list.get(0).getTime();
        String endDate = list.get(list.size()-1).get(0).getTime();
        
        
        for(int i = 0; i < list.size(); i++){
        	if(close>open){
        		series2.add(date, open, high, low, close);
        		series4.add(date, volume);
        	}else{
        		series1.add(date, open, high, low, close);
        		series3.add(date, volume);
        	}
        }//添加数据
        
        
        //k线图数据
        seriesCollection.addSeries(series1);
        seriesCollection.addSeries(series2);
        //成交量数据
        volSeriesCollection.addSeries(series3);
        volSeriesCollection.addSeries(series4);
        
        
        
        
        
        //获取均线图数据
        Map<String,List<avgVO> > average = null;

        

        TimeSeriesCollection lineSeriesConllection = new TimeSeriesCollection();//保留均线图数据的集合
        TimeSeries series5 = new TimeSeries();//对应时间成交量数据，5天
        TimeSeries series6 = new TimeSeries();//对应时间成交量数据，10天
        TimeSeries series7 = new TimeSeries();//对应时间成交量数据，20天
        TimeSeries series8 = new TimeSeries();//对应时间成交量数据，30天
        TimeSeries series9 = new TimeSeries();//对应时间成交量数据，60天

        
        //添加均线图5天数据
        if(fiveDayAvg != null){
            for(List<avgVO> list : fiveDayAvg){
                series5.add(date,avg);
            }
        }
        
        //添加均线图10天数据
        if(tenDayAvg != null){
            for(List<avgVO> list : fiveDayAvg){
                series6.add(date,avg);
            }
        }
        //添加均线图20天数据
        if(twentyDayAvg != null){
            for(List<avgVO> list : fiveDayAvg){
                series7.add(date,avg);
            }
        }
        //添加均线图30天数据
        if(thirtyDayAvg != null){
            for(List<avgVO> list : fiveDayAvg){
                series8.add(date,avg);
            }
        }
        //添加均线图60天数据
        if(sixtyDayAvg != null){
            for(List<avgVO> list : fiveDayAvg){
                series9.add(date,avg);
            }
        }
        
        
        
        //设置K线图的画图器
        CandlestickRenderer candlestickRender = new CandlestickRenderer();
        candlestickRender.setUpPaint(Color.BLACK);//设置股票上涨的K线图颜色
        candlestickRender.setDownPaint(Color.CYAN);//设置股票下跌的K线图颜色
        candlestickRender.setSeriesPaint(1, Color.CYAN);//设置股票下跌的K线图颜色
        candlestickRender.setSeriesPaint(0, Color.RED);//设置股票上涨的K线图颜色
        candlestickRender.setAutoWidthMethod(CandlestickRenderer.WIDTHMETHOD_AVERAGE);//设置如何对K线图的宽度进行设定
        candlestickRender.setAutoWidthGap(0.001);//设置各个K线图之间的间隔
        candlestickRender.setSeriesVisibleInLegend(false);//设置不显示legend（数据颜色提示)

        
        //设置k线图y轴参数
        NumberAxis y1Axis=new NumberAxis();//设置Y轴，为数值,后面的设置，参考上面的y轴设置
        y1Axis.setAutoRange(false);//设置不采用自动设置数据范围
        y1Axis.setUpperMargin(Const.UPPER_RANGE);//设置向上边框距离
        y1Axis.setLabelFont(new Font("微软雅黑", Font.BOLD, 12));
        y1Axis.setRange(minValue*0.9, highValue*1.1);// 设定y轴值的范围，比最低值要低一些，比最大值要大一些，这样图形看起来会美观些
        y1Axis.setTickUnit(new NumberTickUnit((highValue*1.1-minValue*0.9)/10));// 设置刻度显示的密度
        //            y1Axis.setAutoTickUnitSelection(true);//数据轴的数据标签是否自动确定（默认为true）
   
        
        //设置k线图x轴，也就是时间轴
        DateAxis domainAxis = new DateAxis();
        domainAxis.setAutoRange(false);//设置不采用自动设置时间范围
        //设置时间范围，注意，最大和最小时间设置时需要+ - 。否则时间刻度无法显示
        domainAxis.setRange(beginTime, endTime);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");            
        domainAxis.setAutoTickUnitSelection(false);//设置不采用自动选择刻度值
        domainAxis.setTickMarkPosition(DateTickMarkPosition.START);//设置标记的位置
        System.out.println(days);
        domainAxis.setStandardTickUnits(DateAxis.createStandardDateTickUnits());// 设置标准的时间刻度单位
        domainAxis.setTickUnit(new DateTickUnit(DateTickUnit.DAY,7));// 设置时间刻度的间隔，一般以周为单位
        domainAxis.setDateFormatOverride(df);//设置时间格式
        DomainAxis.setTimeline(SegmentedTimeline.newMondayThroughFridayTimeline());// 设置时间线显示的规则，用这个方法就摒除掉了周六和周日这些没有交易的日期(很多人都不知道有此方法)，使图形看上去连续
        //排除所有节假日
        for(Date holiday : allHolidys){
            System.out.println(holiday);
            timeline.addException(holiday);
        }
        domainAxis.setTimeline(timeline);
        
        
        
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
        barRenderer.setSeriesVisibleInLegend(false);//设置不显示legend（数据颜色提示)
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
        JFreeChart chart = new JFreeChart(title, new Font("微软雅黑", Font.BOLD, 24), domainXYPlot, true);

      
 


        
		
		
	}
}
