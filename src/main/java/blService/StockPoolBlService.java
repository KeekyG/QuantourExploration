package blService;

import java.time.LocalDate;

import vo.StockPoolVO;

public interface StockPoolBlService {
	
	/**
	 * 获取股票池
	 * @param name 股票池名称（若为板块股票池则为板块名
	 * @param beginDate 开始时间
	 * @param enDate 结束时间
	 * @return
	 */
	public StockPoolVO getStockPool(String name, LocalDate beginDate, LocalDate enDate);
}
