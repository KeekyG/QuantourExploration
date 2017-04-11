package data.dao.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;

import com.csvreader.CsvReader;

import data.dao.InterestDao;
import po.InterestPO;

public class InterestDaoImpl implements InterestDao {

	@Override
	public InterestPO getInterest(String beginYear, String endYear) {
		HashMap<String, Double> interests = new HashMap<>();
		String filePath = "target/f/AnnualInterest.csv";
		CsvReader record = null;
		try {
			record = new CsvReader(filePath, '	',Charset.forName("UTF8"));
			record.readHeaders();
			while(record.readRecord()){
				record.getRawRecord();
				if((Integer.valueOf(record.get("Date")) >= Integer.valueOf(beginYear)) && 
						(Integer.valueOf(record.get("Date")) <= Integer.valueOf(endYear))){
					interests.put(record.get("Date"), Double.valueOf(record.get("Rate")));
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
		return new InterestPO(interests);
	}

}
