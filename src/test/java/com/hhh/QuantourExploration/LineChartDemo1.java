package com.hhh.QuantourExploration;

/** 
 * 使用 categoryDataset 数据集创建折线图 
 * 当数据多时，在JPanel中无法完全看到横坐标的值，显示为省略号。 
 * 解决方法： 
 *       方法1、将报表保存为图片时，设置图片的宽度足够大（2000或3000），图片可以显示横坐标值。 
 *             这种方法治标不治本，所以有了第2种方法 
 *       方法2、设置X轴上的Lable让其45度倾斜。 
 */
import java.util.Date;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
  
import org.jfree.data.category.CategoryDataset;  
import org.jfree.data.category.DefaultCategoryDataset;

import bl.StockBl;
import blService.StockBlService;
import vo.StockShareVO;  
 /**
  * 处理数据设置Dataset
  * @author KeekyG
  *
  */
public class LineChartDemo1{
	
    private ArrayList<StockShareVO> Stock1;
    private ArrayList<StockShareVO> Stock2;
    private ArrayList<Double> logvalue1 = new ArrayList<>();
    private ArrayList<Double> logvalue2 = new ArrayList<>();
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yy");
	
	public LineChartDemo1(String stock1, String stock2, Date day1, Date day2) {
		StockBlService stockBlService = new StockBl();
		Stock1 = stockBlService.getShareLine(day1, day2, stock1);
		Stock2 = stockBlService.getShareLine(day1, day2, stock2);
    }
	
    public CategoryDataset createLastDataset() {  
        DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
        for(StockShareVO stock: Stock1){
        	defaultcategorydataset.addValue(stock.getClose(), stock.getName(), simpleDateFormat.format(stock.getDate()));
		}
 
        for(StockShareVO stock: Stock2){
        	defaultcategorydataset.addValue(stock.getClose(), stock.getName(), simpleDateFormat.format(stock.getDate()));
		}        
        return defaultcategorydataset;  
    }
    
    public CategoryDataset createMaxDataset() {  
        DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();  
        for(StockShareVO stock: Stock1){
        	defaultcategorydataset.addValue(stock.getHigh(), stock.getName(), simpleDateFormat.format(stock.getDate()));
		}
 
        for(StockShareVO stock: Stock2){
        	defaultcategorydataset.addValue(stock.getHigh(), stock.getName(),simpleDateFormat.format(stock.getDate()));
		}        
        return defaultcategorydataset;  
    }
    
    public CategoryDataset createMinDataset() {  
        DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
        for(StockShareVO stock: Stock1){
        	defaultcategorydataset.addValue(stock.getLow(), stock.getName(), simpleDateFormat.format(stock.getDate()));
		}
 
        for(StockShareVO stock: Stock2){
        	defaultcategorydataset.addValue(stock.getLow(), stock.getName(), simpleDateFormat.format(stock.getDate()));
		}        
        return defaultcategorydataset;  
    }
    
    private double logValue(double now, double before){
    	return Math.log(now/before);
    }
    
    public CategoryDataset createLogDataset() {  
        DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
        ArrayList<StockShareVO> clone1 = (ArrayList<StockShareVO>) Stock1.clone();
        ArrayList<StockShareVO> clone2 = (ArrayList<StockShareVO>) Stock2.clone();
        double date1 = clone1.get(0).getAdjClose();
        double date2 = clone2.get(0).getAdjClose();
        clone1.remove(0);
        clone2.remove(0);
        for(StockShareVO stock: clone1){
        	double value = logValue(date1, stock.getAdjClose());
        	logvalue1.add(value);
        	defaultcategorydataset.addValue(value, stock.getName(), simpleDateFormat.format(stock.getDate()));
        	date1 = stock.getClose();
		}
 
        for(StockShareVO stock: clone2){
        	double value = logValue(date2, stock.getAdjClose());
        	logvalue2.add(value);
        	defaultcategorydataset.addValue(value, stock.getName(), simpleDateFormat.format(stock.getDate()));
        	date2 = stock.getClose();
		}        
        return defaultcategorydataset;  
    }
    
    public double calVariance1(){
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
    	return sum;
    }
    
    public double calVariance2(){
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
    	return sum;
    }
}  
