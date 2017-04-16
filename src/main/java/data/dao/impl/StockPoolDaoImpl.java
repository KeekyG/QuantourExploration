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
		File file1 = new File(filePath);
        BufferedReader reader = null;
        BufferedWriter writer = null;
        File file2 = new File("target/f/" + name + ".csv");
        ArrayList<String> list = new ArrayList<>();
        boolean delete1 = false;
        boolean delete2 = false;
        try {
        	//删除PoolName文件中的name
            reader = new BufferedReader(new FileReader(file1));
            String r = "";
            while ((r = reader.readLine()) != null) {
            	list.add(r);
            }
            reader.close();
            writer = new BufferedWriter(new FileWriter(file1));
            if(list.contains(name)){
            	list.remove(name);
            	delete1 = true;
            }
            for (String s : list) {
            	writer.write(s);
				writer.newLine();
			}
        	writer.close();
        	//删除以name命名的csv文件
        	delete2 = file2.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
		return delete1&&delete2;
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
//			Iterator<String> iterator = stockPoolPO.getStocks().keySet().iterator();
//			while(iterator.hasNext()){
//				String code = iterator.next();
//				String name = stockPoolPO.getStocks().get(code);
//				String[] contents = {code,name};
//				writer1.writeRecord(contents);
//			}
//			writer1.close();
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
            if(!list.contains(stockPoolPO.getName())){
            	list.add(stockPoolPO.getName());
            }
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

	@Override
	public boolean addStock(String stockPoolName, String stockCode, String stockName) {
		String filePath = "target/f/" + stockPoolName + ".csv";
		CsvReader reader = null;
		CsvWriter writer = null;
		ArrayList<String[]> contents = new ArrayList<>();
		boolean add1 = false;
		boolean add2 = false;
		try {
			//从原csv文件中读出array
			reader = new CsvReader(filePath,'	',Charset.forName("UTF8"));
			reader.readHeaders();
			while(reader.readRecord()){
				reader.getRawRecord();
				String[] s = {reader.get("code"),reader.get("name")};
				contents.add(s);
			}
			reader.close();
			add1 = true;
			//将新内容写入array并写入新的csv文件(与前面读入的文件同名)
			writer = new CsvWriter(filePath,'	',Charset.forName("UTF8"));
			String[] headers = {"code","name"};
			writer.writeRecord(headers);
			String[] addStr = {stockCode,stockName};
			contents.add(addStr);
			for (String[] string : contents) {
				writer.writeRecord(string);
			}
			writer.close();
			add2 = true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return add1&&add2;
	}

	@Override
	public boolean deleteStock(String stockPoolName, String stockCode) {
		String filePath = "target/f/" + stockPoolName + ".csv";
		CsvReader reader = null;
		CsvWriter writer = null;
		boolean delete1 = false;
		boolean delete2 = false;
		ArrayList<String[]> contents = new ArrayList<>();
		try {
			//从原csv文件中读出array,遇到要删除的项则跳过
			reader = new CsvReader(filePath,'	',Charset.forName("UTF8"));
			reader.readHeaders();
			while(reader.readRecord()){
				reader.getRawRecord();
				if(!reader.get("code").equals(stockCode)){
					String[] s = {reader.get("code"),reader.get("name")};
					contents.add(s);
				}
			}
			reader.close();
			delete1 = true;
			writer = new CsvWriter(filePath,'	',Charset.forName("UTF8"));
			String[] headers = {"code","name"};
			writer.writeRecord(headers);
			for (String[] string : contents) {
				writer.writeRecord(string);
			}
			writer.close();
			delete2 = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return delete1&&delete2;
	}

}
