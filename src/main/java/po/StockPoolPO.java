package po;

import java.util.HashMap;

/**
 * @author ssw's father
 * 
 * 股票池PO
 * 
 * name 股票池名称
 * capacity 股票池大小
 * stocks 股票池中股票，左边为股票代码，右边为股票名称
 *
 */
public class StockPoolPO {
	
	private String name;
	
	private int capacity;
	
	private HashMap<String, String> stocks;
	
	public StockPoolPO(String name, HashMap<String, String> stocks) {
		this.name = name;
		this.stocks = stocks;
		this.capacity = stocks.size();
	}

	public String getName() {
		return name;
	}

	public int getCapacity() {
		return capacity;
	}

	public HashMap<String, String> getStocks() {
		return stocks;
	}
}
