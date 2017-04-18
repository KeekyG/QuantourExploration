package blService;


import po.InterestPO;
import vo.IncomeLineVO;

public interface StatisticalVarBlService {
	
	/**
	 * @param strategyRates 策略收益率
	 * @return 策略年化收益率
	 */
	public double getStrategyAnnualRate(IncomeLineVO strategyRates);
	
	/**
	 * @param baseRates 基准收益率
	 * @return 基准年化收益率
	 */
	public double getBaseAnnualRate(IncomeLineVO baseRates);
	
	/**
	 * @param interestPO 无风险利率PO
	 * @param strategyRates 策略收益率
	 * @param baseRates 基准收益率
	 * @return 阿尔法
	 */
	public double getAlpha(InterestPO interestPO,IncomeLineVO strategyRates,IncomeLineVO baseRates);
	
	/**
	 * @param strategyRates 策略收益率
	 * @param baseRates 基准收益率
	 * @return 贝塔
	 */
	public double getBeta(IncomeLineVO strategyRates,IncomeLineVO baseRates);
	
	/**
	 * @param strategyRates 策略收益率
	 * @param interestPO 无风险利率PO
	 * @return 夏普比率
	 */
	public double getSharpeRatio(IncomeLineVO strategyRates,InterestPO interestPO);
	
	/**
	 * @param strategyRates 策略收益率
	 * @return 最大回撤
	 */
	public double getMaxDrawDown(IncomeLineVO strategyRates);
	
}
