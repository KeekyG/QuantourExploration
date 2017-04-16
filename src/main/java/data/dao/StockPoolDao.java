package data.dao;

import java.util.ArrayList;

import po.StockPoolPO;

public interface StockPoolDao {
	
	/**
	 * 获取所有自选股票池名称
	 * 
	 * @return 所有股票池名称
	 */
	public ArrayList<String> getStockPoolNames();
	
	/**
	 * 删除股票池
	 * 
	 * @param 股票池名称
	 * @return 是否成功
	 */
	public boolean deleteStockPool(String name);
	
	/**
	 * 获取股票池中的股票
	 * 
	 * @param 股票池名称
	 * @return 股票池
	 */
	public StockPoolPO getStockPool(String name);
	
	/**
	 * 更新股票池
	 * 
	 * @param 股票池
	 * @return 是否成功
	 */
	public boolean setStockPool(StockPoolPO stockPoolPO);
	
	/**
	 * 向已有股票池中添加一条股票信息
	 * 
	 * @param stockPoolName 股票池名称
	 * @param stockCode 股票代码
	 * @param stockName 股票名称
	 * @return 是否成功
	 */
	public boolean addStock(String stockPoolName, String stockCode, String stockName);
	
	/**
	 * 在已有股票池中删除一条股票信息
	 * 
	 * @param stockPoolName 股票池名称
	 * @param stockCode 股票代码
	 * @return 是否成功
	 */
	public boolean deleteStock(String stockPoolName, String stockCode);
	
}
