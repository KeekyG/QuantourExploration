package vo;

import java.util.Date;

/**
 * @author ssw's dad
 * 
 * 用来显示市场温度计的VO
 * 
 * date     日期
 * open     开盘指数
 * close    收盘指数
 * adjClose 复权收盘价
 * volume   成交量
 * code     股票代码
 * name     股票的名称
 * market   市场名称
 *
 */
public class DailyStockVO {

	private Date date;
	
	private double open;
	
	private double close;
	
	private double adjClose;
	
	private long volume;
	
	private String code;
	
	private String name;
	
	private String market;
	
	public DailyStockVO(Date date, double open, double close, double adjClose, long volume, String code, String name, String market) {
		this.date = date;
		this.open = open;
		this.close = close;
		this.adjClose = adjClose;
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

	public double getClose() {
		return close;
	}

	public long getVolume() {
		return volume;
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

	public double getAdjClose() {
		return adjClose;
	}

}
