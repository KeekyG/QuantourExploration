package com.hhh.QuantourExploration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class SplitFile {

	public static void main(String[] args) {
		CsvReader record = null;
		//主板
		CsvWriter writer1 = null;
		//中小板
//		CsvWriter writer2 = null;
//		//创业板
//		CsvWriter writer3 = null;
//		//其余
//		CsvWriter writer4 = null;
		try {
			record = new CsvReader("C://Users/ssw/Desktop/量化交易(数据)/股票历史数据ALL(代码).csv", '	',Charset.forName("UTF8"));
			writer1 = new CsvWriter("C://Users/ssw/Desktop/量化交易(数据)/股票历史数据(主板).csv", '	',Charset.forName("UTF8"));
//			writer2 = new CsvWriter("C://Users/ssw/Desktop/量化交易(数据)/股票历史数据(中小板).csv", '	',Charset.forName("UTF8"));
//			writer3 = new CsvWriter("C://Users/ssw/Desktop/量化交易(数据)/股票历史数据(创业板).csv", '	',Charset.forName("UTF8"));
//			writer4 = new CsvWriter("C://Users/ssw/Desktop/量化交易(数据)/股票历史数据(其他).csv", '	',Charset.forName("UTF8"));
			record.readHeaders();
			writer1.writeRecord(record.getHeaders());
//			writer2.writeRecord(record.getHeaders());
//			writer3.writeRecord(record.getHeaders());
//			writer4.writeRecord(record.getHeaders());
			while(record.readRecord()){
				record.getRawRecord();
				String[] contents = {record.get("Serial"),record.get("Date"),record.get("Open"),record.get("High"),record.get("Low"),record.get("Close"),
						record.get("Volume"),record.get("Adj Close"),record.get("code"),record.get("name"),record.get("market")};
				if(record.get("code").startsWith("000")){
					writer1.writeRecord(contents);
				}
				else if(record.get("code").startsWith("001")){
					writer1.writeRecord(contents);
				}
//				else if(record.get("code").startsWith("002")){
//					writer2.writeRecord(contents);
//				}
//				else if(record.get("code").startsWith("300")){
//					writer3.writeRecord(contents);
//				}
//				else {
//					writer4.writeRecord(contents);
//				}
			}
			record.close();
			writer1.close();
//			writer2.close();
//			writer3.close();
//			writer4.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
