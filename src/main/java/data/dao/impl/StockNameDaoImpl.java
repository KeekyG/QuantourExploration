package data.dao.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;

import com.csvreader.CsvReader;

import data.dao.StockNameDao;

public class StockNameDaoImpl implements StockNameDao{
	

	private String filePath = "target/f/all.csv";

	public HashMap<String, String> getStockName() {
		CsvReader record = null;
		HashMap<String, String> stockNames = new HashMap<String,String>();
		try {
			record = new CsvReader(filePath, '	',Charset.forName("UTF8"));
			record.readHeaders();
			System.out.println(record.getHeaders());
			while (record.readRecord()) {
				record.getRawRecord();
				if(!stockNames.containsKey(record.get("name"))){
					stockNames.put(record.get("name"),record.get("code"));
				}
			}
			record.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return stockNames;
	}

	public ArrayList<String> getStockCode() {
		CsvReader record = null;
		ArrayList<String> stockCodes = new ArrayList<String>();
		try {
		record = new CsvReader(filePath, '	',Charset.forName("UTF8"));
		record.readHeaders();
		while (record.readRecord()) {
			record.getRawRecord();
			if(!stockCodes.contains(record.get("code"))){
				stockCodes.add(record.get("code"));
			}
		}
		record.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return stockCodes;
	}

}
