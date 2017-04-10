package data.dao.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.HashMap;

import com.csvreader.CsvReader;

import data.dao.BenchmarkDao;
import enums.PlateType;
import po.BenchmarkPO;

public class BenchmarkDaoImpl implements BenchmarkDao {

	@Override
	public BenchmarkPO getBenchmark(PlateType type, LocalDate beginDate, LocalDate endDate) {
		HashMap<LocalDate, Double> rate = new HashMap<>();
		String filePath = "";
		if(type.equals(PlateType.HS300)){
			filePath = "f/000300.csv";
		}
		else if(type.equals(PlateType.SME)){
			filePath = "f/399005.csv";
		}
		else if(type.equals(PlateType.GEM)){
			filePath = "f/399006.csv";
		}
		CsvReader record = null;
		try {
			record = new CsvReader(filePath, ',',Charset.forName("UTF8"));
			record.readHeaders();
			while(record.readRecord()){
				record.getRawRecord();
				LocalDate localDate = LocalDate.parse(record.get("日期"));
				if(localDate.isAfter(beginDate) && localDate.isBefore(endDate)){
					rate.put(localDate, Double.valueOf(record.get("涨跌幅")));
				}
			}
			record.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new BenchmarkPO(type, beginDate, endDate, rate);
	}

}
