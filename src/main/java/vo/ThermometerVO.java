package vo;

import java.util.Date;

/**
 * @author ssw's grandfather
 * 
 * 市场温度计
 * 
 * date 日期
 * totalVolume 当日总交易量
 * upNum 涨停股票数
 * downNum 跌停股票数
 * fivePercentUpNum 涨幅超过5%的股票数
 * fivePercentDownNum 跌幅超过5%的股票数
 * fivePercentBiggerNum 开盘‐收盘大于5%*上一个交易日收盘价的股票个数
 * fivePercentSmallerNum 开盘‐收盘小于‐5%*上一个交易日收盘价的股票个数
 *
 */
public class ThermometerVO {
	
	private Date date;
	
	private long totalVolume;
	
	private long upNum;
	
	private long downNum;
	
	private long fivePercentUpNum;
	
	private long fivePercentDownNum;
	
	private long fivePercentBiggerNum;
	
	private long fivePercentSmallerNum;
	
	/**
	 * @param date
	 * @param totalVolume
	 * @param upNum
	 * @param downNum
	 * @param fivePercentUpNum
	 * @param fivePercentDownNum
	 * @param fivePercentBiggerNum
	 * @param fivePercentSmallerNum
	 */
	public ThermometerVO(Date date, long totalVolume, long upNum, long downNum, long fivePercentUpNum, long fivePercentDownNum, long fivePercentBiggerNum, long fivePercentSmallerNum) {
		this.date = date;
		this.totalVolume = totalVolume;
		this.upNum = upNum;
		this.downNum = downNum;
		this.fivePercentUpNum = fivePercentUpNum;
		this.fivePercentDownNum = fivePercentDownNum;
		this.fivePercentBiggerNum = fivePercentBiggerNum;
		this.fivePercentSmallerNum = fivePercentSmallerNum;
	}

	public Date getDate() {
		return date;
	}

	public long getTotalVolume() {
		return totalVolume;
	}

	public long getUpNum() {
		return upNum;
	}

	public long getDownNum() {
		return downNum;
	}

	public long getFivePercentUpNum() {
		return fivePercentUpNum;
	}

	public long getFivePercentDownNum() {
		return fivePercentDownNum;
	}

	public long getFivePercentBiggerNum() {
		return fivePercentBiggerNum;
	}

	public long getFivePercentSmallerNum() {
		return fivePercentSmallerNum;
	}
}
