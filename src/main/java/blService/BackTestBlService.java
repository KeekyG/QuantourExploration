package blService;

import java.time.LocalDate;

import enums.StrategyType;
import vo.IncomeLineVO;

public interface BackTestBlService {
	
	/**
	 * 获取回测结果
	 * @param type 策略类型
	 * @param beginDate 开始日期
	 * @param endDate 结束日期
	 * @param stockPoolName 股票池名称
	 * @param formationPeriod 形成期
	 * @param holdingPeriod 持有期
	 * @return 累计收益率列表
	 */
	public IncomeLineVO getBackTestResult(StrategyType type, LocalDate beginDate, LocalDate endDate, String stockPoolName, int formationPeriod, int holdingPeriod);
	
	/**
	 * 获取基准收益率列表
	 * @param stockPoolName 股票池名称
	 * @param beginDate 开始日期
	 * @param endDate 结束日期
	 * @return 累计基准收益率列表
	 */
	public IncomeLineVO getBenchmark(String stockPoolName, LocalDate beginDate, LocalDate endDate);
}
