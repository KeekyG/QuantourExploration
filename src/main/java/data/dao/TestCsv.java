package data.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
 
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.csvreader.CsvReader;

import po.StockUpDownPO;

public class TestCsv {
	
	//CSV文件头
    private static final String [] FILE_HEADER = {"Serial","Date","Open","High","Low","Close","Volume","Adj Close","code","name","market"};
     
    public static void readCsvFile(String fileName) {
        FileReader fileReader = null;
        CSVParser csvFileParser = null;
        //创建CSVFormat（header mapping）
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER);
        try {
            //初始化FileReader object
            fileReader = new FileReader(fileName);
            //初始化 CSVParser object
            csvFileParser = new CSVParser(fileReader, csvFileFormat);
            //CSV文件records
            List<CSVRecord> csvRecords = csvFileParser.getRecords(); 
            // CSV
            List<StockUpDownPO> stockList = new ArrayList<StockUpDownPO>();
            // 
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm/dd/yyyy");
            for (int i = 1; i < csvRecords.size(); i++) {
                CSVRecord record = csvRecords.get(i);
                //创建用户对象填入数据
                StockUpDownPO stockUpDownPO = new StockUpDownPO(Integer.parseInt(record.get("Serial")),simpleDateFormat.parse(record.get("Date")),
                		Double.valueOf(record.get("Open")),Double.valueOf(record.get("High")),Double.valueOf(record.get("Low")),Double.valueOf(record.get("Close")),
                		Long.parseLong(record.get("Volume")),Double.valueOf(record.get("Adj Close")),record.get("code"),record.get("name"),record.get("market"));
                stockList.add(stockUpDownPO); 
            }
            // 遍历打印
            for (StockUpDownPO stockUpDownPO : stockList) {
                System.out.println(stockUpDownPO.toString());
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
                csvFileParser.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
	
	
     
    public static void main(String[] args){
//        readCsvFile("C://Users/ssw/Desktop/量化交易/Test.csv");
    	
    	//生成CsvReader对象，以，为分隔符，GBK编码方式
    	          CsvReader r;
				try {
					r = new CsvReader("C://Users/ssw/Desktop/量化交易/Test.csv", '	',Charset.forName("UTF8"));
    	          //读取表头
    	        
					r.readHeaders();
				
    	          //逐条读取记录，直至读完
    	          while (r.readRecord()) {
    	              //读取一条记录
    	             System.out.println(r.getRawRecord());
    	              //按列名读取这条记录的值
    	             System.out.println(r.get("Serial"));
    	             System.out.println(r.get("Date"));
    	             System.out.println(r.get("Open"));
    	             System.out.println(r.get("High"));
    	             System.out.println(r.get("Low"));
    	             System.out.println(r.get("Close"));
    	             System.out.println(r.get("Volume"));
    	             System.out.println(r.get("Adj Close"));
    	             System.out.println(r.get("code"));
    	             System.out.println(r.get("name"));
    	             System.out.println(r.get("market"));
    	         }
    	         r.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	
    }
	
}
