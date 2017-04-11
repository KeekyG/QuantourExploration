package data.dao.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import data.dao.StockPoolDao;
import po.StockPoolPO;

public class StockPoolDaoImpl implements StockPoolDao {

	@Override
	public ArrayList<String> getStockPoolNames() {
		String filePath = "target/f/PoolName.txt";
		ArrayList<String> poolNames = new ArrayList<>();
		File file = new File(filePath);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = reader.readLine()) != null) {
            	poolNames.add(line);
            }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return poolNames;
	}

	@Override
	public boolean deleteStockPool(String name) {
		String filePath = "target/f/PoolName.txt";
		File file = new File(filePath);
        BufferedReader reader = null;
        BufferedWriter writer = null;
        ArrayList<String> list = new ArrayList<>();
        boolean delete = false;
        try {
            reader = new BufferedReader(new FileReader(file));
            String r = "";
            while ((r = reader.readLine()) != null) {
            	list.add(r);
            }
            reader.close();
            writer = new BufferedWriter(new FileWriter(file));
            if(list.contains(name)){
            	list.remove(name);
            	delete = true;
            }
            for (String s : list) {
            	writer.write(s);
				writer.newLine();
			}
        	writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return delete;
	}

	@Override
	public StockPoolPO getStockPool(String name) {
		String filePath = "target/f/" + name +".csv";
		CsvReader reader = null;
		HashMap<String, String> map = new HashMap<>();
		try {
			reader = new CsvReader(filePath,'	',Charset.forName("UTF8"));
			reader.readHeaders();
			while(reader.readRecord()){
				reader.getRawRecord();
				map.put(reader.get("code"), reader.get("name"));
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new StockPoolPO(name, map);
	}

	@Override
	public boolean setStockPool(StockPoolPO stockPoolPO) {
		//添加以股票池名称为名的csv文件
		String filePath = "target/f/" + stockPoolPO.getName() + ".csv";
		CsvWriter writer1 = new CsvWriter(filePath,'	',Charset.forName("UTF8"));
		String[] headers = {"code","name"};
		boolean set1 = false;
		boolean set2 = false;
		try {
			writer1.writeRecord(headers);
			Iterator<String> iterator = stockPoolPO.getStocks().keySet().iterator();
			while(iterator.hasNext()){
				String code = iterator.next();
				String name = stockPoolPO.getStocks().get(code);
				String[] contents = {code,name};
				writer1.writeRecord(contents);
			}
			writer1.close();
			set1 = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//将股票池名称写入PoolName的txt文件
		File file = new File("target/f/PoolName.txt");
		BufferedReader reader = null;
        BufferedWriter writer2 = null;
        ArrayList<String> list = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String r = "";
            while ((r = reader.readLine()) != null) {
            	list.add(r);
            }
            reader.close();
            list.add(stockPoolPO.getName());
            writer2 = new BufferedWriter(new FileWriter(file));
            for (String s : list) {
            	writer2.write(s);
				writer2.newLine();
			}
        	writer2.close();
        	set2 = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
		return set1&&set2;
	}

}
