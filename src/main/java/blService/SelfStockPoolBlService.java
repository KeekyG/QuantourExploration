package blService;

import java.util.ArrayList;

import vo.SelfSelectStockPoolVO;

public interface SelfStockPoolBlService {
	
	/**
	 * 获取所有自选股票池名称
	 * @return 所有自选股票池名称
	 */
	public ArrayList<String> getStockPools();
	
	/**
	 * 获取一个股票池里所有股票
	 * @param name 股票池名称
	 * @return 自选股票池VO
	 */
	public SelfSelectStockPoolVO getStocks(String name);
	
	/**
	 * 增加股票池
	 * @param name 股票池名称
	 * @return 是否成功
	 */
	public boolean addStockPool(String name);
	
	/**
	 * 往股票池中添加股票
	 * @param stockPoolName 股票池名称
	 * @param stockCode 股票代码
	 * @param stockName 股票名称
	 * @return 是否成功
	 */
	public boolean addStock(String stockPoolName, String stockCode, String stockName);
	
	/**
	 * 删除股票池
	 * @param stockPoolName 股票池名称
	 * @return 是否成功
	 */
	public boolean deleteStockPool(String stockPoolName);
	
	/**
	 * 删除股票池中的股票
	 * @param stockPoolName 股票池名称
	 * @param stockCode 股票代码
	 * @return 是否成功
	 */
	public boolean deleteStock(String stockPoolName, String stockCode);
}
