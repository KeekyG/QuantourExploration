package com.hhh.QuantourExploration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class SplitCode {
	
	public static void main(String[] args) {
		CsvReader reader = null;
		CsvWriter writer = null;
		ArrayList<String> contents = new ArrayList<>();
		try {
			reader = new CsvReader("target/f/all(gem).csv", '	',Charset.forName("UTF8"));
			writer = new CsvWriter("target/f/code(gem).csv", '	',Charset.forName("UTF8"));
			reader.readHeaders();
			String[] headers = {"code"};
			writer.writeRecord(headers);
			while(reader.readRecord()){
				reader.getRawRecord();
				if(!contents.contains(reader.get("code"))){
					contents.add(reader.get("code"));
					String[] s = {reader.get("code")};
					writer.writeRecord(s);
				}
			}
			reader.close();
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
