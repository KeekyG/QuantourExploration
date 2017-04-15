package vo;

import java.time.LocalDate;
import java.util.HashMap;

public class HoldingPeriodStockVO extends StockSetVO {
	
	private HashMap<LocalDate, Double> rates;

	public HoldingPeriodStockVO(LocalDate beginDate, LocalDate endDate, HashMap<String, ShareLineVO> stocks) {
		super();
		super.setBeginDate(beginDate);
		super.setEndDate(endDate);
		super.setStocks(stocks);
	}

	public HashMap<LocalDate, Double> getRates() {
		return rates;
	}
	
	public void setRates(HashMap<LocalDate, Double> rates) {
		this.rates = rates;
	}
}
