package com.hhh.QuantourExploration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import com.csvreader.CsvWriter;

public class Add399005 {

	public static void main(String[] args) {
		CsvWriter writer = new CsvWriter("C://Users/ssw/Desktop/量化交易(数据)/399005(add).csv", ',',Charset.forName("UTF8"));
		File file = new File("C://Users/ssw/Desktop/量化交易(数据)/add1.txt");
		try {
			FileReader reader = new FileReader(file);
//			String[] headers = {"Date","Rate"};
//			writer.writeRecord(headers);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = null;
			while((line = bufferedReader.readLine()) != null){
				String rate1 = line.substring(11,line.length()-1);
				double d = Double.parseDouble(rate1);
				d *= 0.01;
				String rate2 = String.valueOf(d);
				String[] contents = {line.substring(0, 10),"'399005","中小板P",rate2};
				writer.writeRecord(contents);
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
