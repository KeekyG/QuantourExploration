package vo;

import java.time.LocalDate;
import java.util.HashMap;

public class FormationPeriodStockVO extends StockSetVO {
	
	public FormationPeriodStockVO(LocalDate beginDate, LocalDate endDate, HashMap<String, ShareLineVO> stocks) {
		super();
		super.setBeginDate(beginDate);
		super.setEndDate(endDate);
		super.setStocks(stocks);
	}
}
