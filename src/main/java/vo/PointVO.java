package vo;

import java.time.LocalDate;

/**
 * @author ssw's daddy
 * 
 * 点，用来画图
 * 
 * date 日期，用于x轴数据
 * rate 值，用于y轴数据
 *
 */
public class PointVO {
	
	private LocalDate date;
	
	private double rate;
	
	public PointVO(LocalDate date, double rate) {
		this.date = date;
		this.rate = rate;
	}

	public LocalDate getDate() {
		return date;
	}

	public double getRate() {
		return rate;
	}
	
	
}
