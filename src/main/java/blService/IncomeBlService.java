package blService;

import java.time.LocalDate;
import java.util.ArrayList;

import vo.HoldingPeriodStockVO;
import vo.IncomeLineVO;
import vo.StockPoolVO;

public interface IncomeBlService {
	
	/**
	 * 计算持有期的收益率
	 * @param holdingPeriodStocks 持有期
	 * @return 累计持有期收益率
	 */
	public IncomeLineVO calculateIncomeRate(ArrayList<HoldingPeriodStockVO> holdingPeriodStocks);
	
	/**
	 * 计算股票池的平均收益作为基准收益
	 * @param stockPoolVO 股票池
	 * @return 累计收益
	 */
	public IncomeLineVO calculateAverageIncome(StockPoolVO stockPoolVO);
	
	/**
	 * 计算累计收益率
	 * @param incomeLineVO 每天的收益
	 * @return 累计基准收益率
	 */
	public IncomeLineVO calculateTotalRate(IncomeLineVO incomeLineVO);
	
	
}
