package po;

import java.util.Date;

/**
 * @author ssw
 * 
 * serial	记录编号
 * date     日期
 * open     开盘指数
 * high     最高指数
 * low      最低指数
 * close    收盘指数
 * volume   成交量
 * adjClose 复权后的收盘指数
 * code     股票代码
 * name     股票名称
 * market   市场名称
 *
 */
public class StockUpDownPO {
	
	private int serial;
	
	private Date date;
	
	private double open;
	
	private double high;
	
	private double low;
	
	private double close;
	
	private long volume;
	
	private double adjClose;
	
	private String code;
	
	private String name;
	
	private String market;

	public int getSerial() {
		return serial;
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

	public long getVolume() {
		return volume;
	}

	public double getAdjClose() {
		return adjClose;
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
	
}
