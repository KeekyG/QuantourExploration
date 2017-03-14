package vo;

import java.util.Date;

/**
 * @author ssw's father
 * 
 * 用来画k线图和股票比较的VO
 * 
 * date     日期
 * open     开盘指数
 * high     最高指数
 * low      最低指数
 * close    收盘指数
 * volume   交易量
 * code     股票代码
 * name     股票名称
 * market   市场名称
 *
 */
public class StockShareVO { 
	private Date date;
	
	private double open;
	
	private double high;
	
	private double low;
	
	private double close;
	
	private long volume;
	
	private String code;
	
	private String name;
	
	private String market;
	
	public StockShareVO(Date date, double open, double high, double low, double close, long volume, String code, String name, String market) {
		this.date = date;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
		this.code = code;
		this.name = name;
		this.market = market;
	}
	
	public Date getDate() {
		return date;
	}

	public double getOpen() {
		return open;
	}

	public double getHigh() {
		return high;
	}

	public double getLow() {
		return low;
	}

	public double getClose() {
		return close;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getMarket() {
		return market;
	}

	public long getVolume() {
		return volume;
	}


}
