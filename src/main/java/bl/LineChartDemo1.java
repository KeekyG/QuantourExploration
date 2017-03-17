package bl;

/** 
 * 使用 categoryDataset 数据集创建折线图 
 * 当数据多时，在JPanel中无法完全看到横坐标的值，显示为省略号。 
 * 解决方法： 
 *       方法1、将报表保存为图片时，设置图片的宽度足够大（2000或3000），图片可以显示横坐标值。 
 *             这种方法治标不治本，所以有了第2种方法 
 *       方法2、设置X轴上的Lable让其45度倾斜。 
 */
import java.util.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.jfree.data.category.CategoryDataset;  
import org.jfree.data.category.DefaultCategoryDataset;

import blService.StockBlService;
import vo.ShareLineVO;
import vo.StockShareVO;  

 /**
  * 处理数据设置Dataset
  * @author KeekyG
  *
  */
public class LineChartDemo1{

    private ArrayList<Double> logvalue1 = new ArrayList<>();
    private ArrayList<Double> logvalue2 = new ArrayList<>();
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yy");
	
	/**
	 * 返回选定日期内某股票的所有信息（包括所选日期前一天的信息）
	 * @param stock
	 * @param day1
	 * @param day2
	 * @return
	 */
	public ShareLineVO getStock(String stock, Date day1, Date day2){
		ShareLineVO Stock;
		StockBlService stockBlService = new StockBl();
		Stock = stockBlService.getShareLine(day1, day2, stock);
		return Stock;
	}
	
	/**
	 * 将传入的数据置为categorydataset并返回
	 * @param Stock1
	 * @param Stock2
	 * @return
	 */
    public CategoryDataset createLastDataset(ArrayList<StockShareVO> Stock1, ArrayList<StockShareVO> Stock2) {  
        DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
        for(StockShareVO stock: Stock1){
        	defaultcategorydataset.addValue(stock.getClose(), stock.getName(), simpleDateFormat.format(stock.getDate()));
		}
 
        for(StockShareVO stock: Stock2){
        	defaultcategorydataset.addValue(stock.getClose(), stock.getName(), simpleDateFormat.format(stock.getDate()));
		}        
        return defaultcategorydataset;  
    }
    
    /**
     * 返回选定日期内的最高值
     * @param Stock
     * @return
     */
    public double getMax(ArrayList<StockShareVO> Stock) {
    	int i = 1;
    	int length = Stock.size();
    	double value = Stock.get(0).getHigh();
    	for(StockShareVO stock = Stock.get(i); i <= length ; i++){
    		if(value < stock.getHigh()){
    			value = stock.getHigh();
    		}
    	}
        return value;  
    }
    
    /**
     * 返回选定日期内的最低值
     * @param Stock
     * @return
     */
    public double getMin(ArrayList<StockShareVO> Stock) {  
    	int i = 1;
    	int length = Stock.size();
    	double value = Stock.get(0).getHigh();
    	for(StockShareVO stock = Stock.get(i); i <= length ; i++){
    		if(value < stock.getHigh()){
    			value = stock.getHigh();
    		}
    	}
        return value;  
    }
    
    /**
     * 计算两个时间之间的对数收益率
     * @param now
     * @param before
     * @return
     */
    private double logValue(double now, double before){
    	return Math.log(now/before);
    }
    
    /**
     * 将数据置为对应的对数收益率并放入categorydataset中并返回
     * @param Stock1
     * @param Stock2
     * @return
     */
    public CategoryDataset createLogDataset(ArrayList<StockShareVO> Stock1, ArrayList<StockShareVO> Stock2) {  
        DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
        ShareLineVO stock1 = (ShareLineVO)Stock1;
        ShareLineVO stock2 = (ShareLineVO)Stock2;
        double date1 = stock1.getYesterdayShare().getAdjClose();
        double date2 = stock2.getYesterdayShare().getAdjClose();
        for(StockShareVO stock: Stock1){
        	double value = logValue(date1, stock.getAdjClose());
        	logvalue1.add(value);
        	defaultcategorydataset.addValue(value, stock.getName(), simpleDateFormat.format(stock.getDate()));
        	date1 = stock.getAdjClose();
		}
 
        for(StockShareVO stock: Stock2){
        	double value = logValue(date2, stock.getAdjClose());
        	logvalue2.add(value);
        	defaultcategorydataset.addValue(value, stock.getName(), simpleDateFormat.format(stock.getDate()));
        	date2 = stock.getAdjClose();
		}        
        return defaultcategorydataset;  
    }
    
    //进行格式转换
    private DecimalFormat decimalFormat = new DecimalFormat("##.##");
    
    /**
     * 返回股票的涨跌幅
     * @param stockShareVOs
     * @return
     */
	public String getChange(ArrayList<StockShareVO> stockShareVOs){
		ShareLineVO stock1 = (ShareLineVO)stockShareVOs;
        double first = stock1.getYesterdayShare().getClose();
		double last = stockShareVOs.get(stockShareVOs.size() - 1).getClose();
		return decimalFormat.format(last - first);
	}
	
    /**
     * 返回对数收益率的方差
     * @return
     */
    public String calVariance1(){
    	double sum = 0;
    	int counter = 0;
    	for(double temp: logvalue1){
    		counter++;
    		sum += temp;
    	}
    	double ave = sum/counter;
    	sum = 0;
    	for(double temp: logvalue1){
    		sum += (temp - ave)*(temp - ave);
    	}
    	return decimalFormat.format(sum);
    }
    
    public String calVariance2(){
    	double sum = 0;
    	int counter = 0;
    	for(double temp: logvalue2){
    		counter++;
    		sum += temp;
    	}
    	double ave = sum/counter;
    	sum = 0;
    	for(double temp: logvalue2){
    		sum += (temp - ave)*(temp - ave);
    	}
    	return decimalFormat.format(sum);
    }
}  
