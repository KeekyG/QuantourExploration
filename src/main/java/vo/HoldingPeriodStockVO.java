package vo;

import java.time.LocalDate;
import java.util.HashMap;

public class HoldingPeriodStockVO extends StockSetVO {
	
	public HoldingPeriodStockVO(LocalDate beginDate, LocalDate endDate, HashMap<String, ShareLineVO> stocks) {
		super();
		super.setBeginDate(beginDate);
		super.setEndDate(endDate);
		super.setStocks(stocks);
	}

}
