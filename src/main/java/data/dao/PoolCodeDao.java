package data.dao;

import java.util.ArrayList;

public interface PoolCodeDao {
	
	/**
	 * @return 主板股票代码
	 */
	public ArrayList<String> getHS300Code();
	
	/**
	 * @return 中小板股票代码
	 */
	public ArrayList<String> getSMEcode();
	
	/**
	 * @return 创业板股票代码
	 */
	public ArrayList<String> getGEMcode();
	
}
