package blService;

import java.time.LocalDate;
import java.util.HashMap;

import vo.IncomeLineVO;

public interface AnalysisBlService {
	
	/**
	 * 获取累计收益率列表
	 * @return 累计收益率列表
	 */
	public IncomeLineVO getTotalIncome();
	
	/**
	 * 获取基准收益率列表
	 * @return 基准收益率列表
	 */
	public IncomeLineVO getTotalBenchmarkIncome();
	
	/**
	 * 获取正收益周期数
	 * @return 正收益周期数
	 */
	public int getPositiveIncomeNums();
	
	/**
	 * 获取负收益周期数
	 * @return 负收益周期数
	 */
	public int getNegativeIncomeNums();
	
	/**
	 * 获取策略胜率
	 * @return 策略胜率
	 */
	public double getWinRate();
	
	/**
	 * 你妈的，这个方法名字太长了
	 * @return 不写注释了
	 */
	public HashMap<LocalDate, Double> getStrategyRates();
	
	/**
	 * 这也是个长方法
	 * @return 注释也不写了
	 */
	public HashMap<LocalDate, Double> getBaseRates();
	
	/**
	 * 获取超额收益
	 * @return 超额收益
	 */
	public double getExcessEarnings();
}
