package data.dao.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;

import com.csvreader.CsvReader;

import data.dao.StockNameDao;

public class StockNameDaoImpl implements StockNameDao{

	public HashMap<String, String> getStockName() {
		CsvReader record = null;
		HashMap<String, String> stockNames = new HashMap<String,String>();
		try {
		record = new CsvReader("C://Users/ssw/Desktop/量化交易/股票历史数据ALL.csv", '	',Charset.forName("UTF8"));
		record.readHeaders();
		while (record.readRecord()) {
			record.getRawRecord();
			if(!stockNames.containsKey(record.get("name"))){
				stockNames.put(record.get("name"),record.get("code"));
			}
		}
		record.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stockNames;
	}

	public ArrayList<String> getStockCode() {
		CsvReader record = null;
		ArrayList<String> stockCodes = new ArrayList<String>();
		try {
		record = new CsvReader("C://Users/ssw/Desktop/量化交易/股票历史数据ALL.csv", '	',Charset.forName("UTF8"));
		record.readHeaders();
		while (record.readRecord()) {
			record.getRawRecord();
			stockCodes.add(record.get("code"));
		}
		record.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stockCodes;
	}

}
