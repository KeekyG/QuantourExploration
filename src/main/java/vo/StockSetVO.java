package vo;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * @author ssw's father
 * 
 * 抽象类，股票的集合
 *
 */
abstract class StockSetVO {
	
	private HashMap<String, ShareLineVO> stocks;
	
	private LocalDate beginDate;
	
	private LocalDate endDate;
	
	public LocalDate getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(LocalDate beginDate) {
		this.beginDate = beginDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public void setStocks(HashMap<String, ShareLineVO> stocks) {
		this.stocks = stocks;
	}

	public HashMap<String, ShareLineVO> getStocks() {
		return stocks;
	}
}
