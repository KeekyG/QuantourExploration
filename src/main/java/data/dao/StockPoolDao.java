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
}
