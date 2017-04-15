package vo;

import java.util.HashMap;

/**
 * @author ssw's daddy
 * 
 * 自选股票池VO
 * 
 * name 股票池名称
 * capacity 股票池大小
 * stocks 股票池中股票，左边为股票代码，右边为股票名称
 *
 */
public class SelfSelectStockPoolVO {
	
	private String name;
	
	private int capacity;
	
	private HashMap<String, String> stocks;
	
	public SelfSelectStockPoolVO(String name, HashMap<String, String> stocks) {
		this.name = name;
		this.stocks = stocks;
		this.capacity = stocks.size();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public HashMap<String, String> getStocks() {
		return stocks;
	}

	public void setStocks(HashMap<String, String> stocks) {
		this.stocks = stocks;
		this.capacity = stocks.size();
	}
	
	
}
