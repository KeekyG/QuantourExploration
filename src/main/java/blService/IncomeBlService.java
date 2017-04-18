package blService;

import java.util.ArrayList;

import vo.HoldingPeriodStockVO;
import vo.IncomeLineVO;

public interface IncomeBlService {
	
	/**
	 * 计算持有期的收益率
	 * @param holdingPeriodStocks 持有期
	 * @return 累计持有期收益率
	 */
	public IncomeLineVO calculateTotalIncomeRate(ArrayList<HoldingPeriodStockVO> holdingPeriodStocks);
	
	/**
	 * 通过形成期和收益率计算基准收益率的累计收益率
	 * @param holdingPeriodStocks 持有期
	 * @param incomeLineVO 每天的收益
	 * @return 累计基准收益率
	 */
	public IncomeLineVO calculateTotalBenchmarkRate(IncomeLineVO incomeLineVO);
	
}
