package com.hhh.QuantourExploration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;


import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class AddCode {

	public static void main(String[] args) {
		CsvReader record = null;
		CsvWriter writer = null;
		try {
			record = new CsvReader("C://Users/ssw/Desktop/量化交易(数据)/股票历史数据ALL(clean新).csv", '	',Charset.forName("UTF8"));
			writer = new CsvWriter("C://Users/ssw/Desktop/量化交易(数据)/股票历史数据ALL(代码).csv", '	',Charset.forName("UTF8"));
			record.readHeaders();
			writer.writeRecord(record.getHeaders());
			while(record.readRecord()){
				record.getRawRecord();
				String code = "";
				if(record.get("code").length() != 6){
					for (int i = 0; i < 6-record.get("code").length(); i++) {
						code += "0";
					}
					code = code + record.get("code");
				}
				else{
					code = record.get("code");
				}
				String[] contents = {record.get("Serial"),record.get("Date"),record.get("Open"),record.get("High"),record.get("Low"),record.get("Close"),
						record.get("Volume"),record.get("Adj Close"),code,record.get("name"),record.get("market")};
				writer.writeRecord(contents);
			}
			record.close();
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
