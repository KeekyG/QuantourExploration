package com.hhh.QuantourExploration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import com.csvreader.CsvWriter;


public class AnnualRate {

	public static void main(String[] args) {
		CsvWriter writer = new CsvWriter("C://Users/ssw/Desktop/量化交易(数据)/中国人民银行历年存款利率.csv", '	',Charset.forName("UTF8"));
		File file = new File("C://Users/ssw/Desktop/量化交易(数据)/利率.txt");
		
		try {
			@SuppressWarnings({ "unused", "resource" })
			FileReader reader = new FileReader(file);
			String[] headers = {"Date","Rate"};
			writer.writeRecord(headers);
			@SuppressWarnings("resource")
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = null;
			while((line = bufferedReader.readLine()) != null){
				String[] contents = {line.substring(0, 4),line.substring(5)};
				writer.writeRecord(contents);
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
