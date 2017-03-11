package data.dao;

import java.util.ArrayList;
import java.util.Date;

import po.StockUpDownPO;

public interface StockDao {
	
	/**
	 * @param start  开始日期
	 * @param end    结束日期
	 * @param code   股票编号
	 * @return       获取所需股票
	 */
	public ArrayList<StockUpDownPO> getSearchStocks(Date start,Date end,String code);
	
	/**
	 * @param date  当日日期
	 * @return      获取某一日期股票
	 */
	public ArrayList<StockUpDownPO> getDailyStock(Date date);
	
}
