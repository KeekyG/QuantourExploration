package data.dao.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.csvreader.CsvReader;

import data.dao.StockDao;
import po.StockUpDownPO;

public class StockDaoImpl implements StockDao{

	public ArrayList<StockUpDownPO> getSearchStocks(Date start, Date end, String code) {
		CsvReader record = null;
		ArrayList<StockUpDownPO> stockUpDownPOs = new ArrayList<StockUpDownPO>();
		try{
		record = new CsvReader("C://Users/ssw/Desktop/量化交易/股票历史数据ALL.csv", '	',Charset.forName("UTF8"));
		record.readHeaders();
		while (record.readRecord()) {
			record.getRawRecord();
			if(record.get("code").equals(code)){
			    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yy");
				Date date = simpleDateFormat.parse(record.get("Date"));
				if(start.before(date) && end.after(date)){
					stockUpDownPOs.add(new StockUpDownPO(Integer.parseInt(record.get("Serial")),date,Double.valueOf(record.get("Open")),
							Double.valueOf(record.get("High")),Double.valueOf(record.get("Low")),Double.valueOf(record.get("Close")),
	                		Long.parseLong(record.get("Volume")),Double.valueOf(record.get("Adj Close")),record.get("code"),record.get("name"),record.get("market")));
				}
		    }
		}
			record.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return stockUpDownPOs;
	}

	public ArrayList<StockUpDownPO> getDailyStock(Date date) {
		CsvReader record = null;
		ArrayList<StockUpDownPO> stockUpDownPOs = new ArrayList<StockUpDownPO>();
		try{
			record = new CsvReader("C://Users/ssw/Desktop/量化交易/股票历史数据ALL.csv", '	',Charset.forName("UTF8"));
			record.readHeaders();
			while (record.readRecord()) {
				record.getRawRecord();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yy");
				Date d = simpleDateFormat.parse(record.get("Date"));
				if(d.equals(date)){
					stockUpDownPOs.add(new StockUpDownPO(Integer.parseInt(record.get("Serial")),date,Double.valueOf(record.get("Open")),
							Double.valueOf(record.get("High")),Double.valueOf(record.get("Low")),Double.valueOf(record.get("Close")),
		                	Long.parseLong(record.get("Volume")),Double.valueOf(record.get("Adj Close")),record.get("code"),record.get("name"),record.get("market")));
				}
			}
				record.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return stockUpDownPOs;
	}
	
}
