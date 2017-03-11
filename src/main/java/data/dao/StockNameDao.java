package data.dao;

import java.util.ArrayList;
import java.util.HashMap;

public interface StockNameDao {

	/**
	 * @return  获取股票名称
	 */
	public HashMap<String, String> getStockName();
	
	/**
	 * @return  获取股票编号
	 */
	public ArrayList<String> getStockCode();
	
}
