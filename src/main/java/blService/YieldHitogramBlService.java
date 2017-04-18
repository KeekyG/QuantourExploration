package blService;

import java.time.LocalDate;
import java.util.HashMap;

import vo.IncomeLineVO;

public interface YieldHitogramBlService {
	
	/**
	 * @param strategyRates 策略收益率
	 * @param baseRates 基准收益率
	 * @return 获取正收益周期数
	 */
	public int getPositiveIncome(HashMap<LocalDate, Double> strategyRates,HashMap<LocalDate, Double> baseRates);
	
	/**
	 * @param strategyRates 策略收益率
	 * @param baseRates 基准收益率
	 * @return 获取负收益周期数
	 */
	public int getNegativeIncome(HashMap<LocalDate, Double> strategyRates,HashMap<LocalDate, Double> baseRates);
	
	/**
	 * @param strategyRates 策略收益率
	 * @param baseRates 基准收益率
	 * @return 获取赢率
	 */
	public double getWinRate(HashMap<LocalDate, Double> strategyRates,HashMap<LocalDate, Double> baseRates);
	
	/**
	 * @param strategyRates 策略收益率
	 * @param baseRates 基准收益率
	 * @param holdingPeriodDay 持有期
	 * @return 获取策略收益率（左日期，右收益率，指的是每一周期的累计收益率）
	 */
	public HashMap<LocalDate, Double> getStrategyRates(IncomeLineVO strategyRates,int holdingPeriodDay);
	
	/**
	 * @param strategyRates 策略收益率
	 * @param baseRates 基准收益率
	 * @param holdingPeriodDay 持有期
	 * @return 获取基准收益率（左日期，右收益率，指的是每一周期的累计收益率）
	 */
	public HashMap<LocalDate, Double> getBaseRates(IncomeLineVO baseRates,int holdingPeriodDay);

}
