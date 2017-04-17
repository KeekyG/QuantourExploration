package blService;

import java.util.ArrayList;

import po.InterestPO;
import vo.PointVO;

public interface StatisticalVarBlService {
	
	/**
	 * @param strategyRates 策略收益率
	 * @return 策略年化收益率
	 */
	public double getStrategyAnnualRate(ArrayList<PointVO> strategyRates);
	
	/**
	 * @param baseRates 基准收益率
	 * @return 基准年化收益率
	 */
	public double getBaseAnnualRate(ArrayList<PointVO> baseRates);
	
	/**
	 * @param interestPO 无风险利率PO
	 * @param strategyRates 策略收益率
	 * @param baseRates 基准收益率
	 * @return 阿尔法
	 */
	public double getAlpha(InterestPO interestPO,ArrayList<PointVO> strategyRates,ArrayList<PointVO> baseRates);
	
	/**
	 * @param strategyRates 策略收益率
	 * @param baseRates 基准收益率
	 * @return 贝塔
	 */
	public double getBeta(ArrayList<PointVO> strategyRates,ArrayList<PointVO> baseRates);
	
	/**
	 * @param strategyRates 策略收益率
	 * @param interestPO 无风险利率PO
	 * @return 夏普比率
	 */
	public double getSharpeRatio(ArrayList<PointVO> strategyRates,InterestPO interestPO);
	
	/**
	 * @param strategyRates 策略收益率
	 * @return 最大回撤
	 */
	public double getMaxDrawDown(ArrayList<PointVO> strategyRates);
	
}
