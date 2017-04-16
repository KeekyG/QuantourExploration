package data.dao.impl;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import com.csvreader.CsvReader;

import data.dao.PoolCodeDao;

public class PoolCodeDaoImpl implements PoolCodeDao{

	@Override
	public ArrayList<String> getHS300Code() {
		CsvReader reader = null;
		ArrayList<String> code = new ArrayList<>();
		try {
			reader = new CsvReader("target/f/code(mb).csv", '	',Charset.forName("UTF8"));
			reader.readHeaders();
			while(reader.readRecord()){
				reader.getRawRecord();
				code.add(reader.get("code"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return code;
	}

	@Override
	public ArrayList<String> getSMEcode() {
		CsvReader reader = null;
		ArrayList<String> code = new ArrayList<>();
		try {
			reader = new CsvReader("target/f/code(sb).csv", '	',Charset.forName("UTF8"));
			reader.readHeaders();
			while(reader.readRecord()){
				reader.getRawRecord();
				code.add(reader.get("code"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return code;
	}

	@Override
	public ArrayList<String> getGEMcode() {
		CsvReader reader = null;
		ArrayList<String> code = new ArrayList<>();
		try {
			reader = new CsvReader("target/f/code(gem).csv", '	',Charset.forName("UTF8"));
			reader.readHeaders();
			while(reader.readRecord()){
				reader.getRawRecord();
				code.add(reader.get("code"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return code;
	}

}
