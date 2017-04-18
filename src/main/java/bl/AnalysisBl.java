package bl;

import java.time.LocalDate;
import java.util.HashMap;

import blService.AnalysisBlService;
import blService.BackTestBlService;
import blService.IncomeBlService;
import blService.YieldHitogramBlService;
import enums.StrategyType;
import vo.IncomeLineVO;

public class AnalysisBl implements AnalysisBlService {

	private int holdingPeriod;
	
	private IncomeLineVO incomes;
	
	private IncomeLineVO benchMarks;
	
	private IncomeBlService incomeBl;
	
	private YieldHitogramBlService yieldHitogramBl;
	
	private HashMap<LocalDate, Double> strategyRateMap;
	
	private HashMap<LocalDate, Double> baseRateMap;
	
	public AnalysisBl(StrategyType type, LocalDate beginDate, LocalDate endDate,
			String stockPoolName, int formationPeriod, int holdingPeriod) {
		this.holdingPeriod = holdingPeriod;
		BackTestBlService backTestBl = new BackTestBl(stockPoolName, beginDate, endDate);
		incomes = backTestBl.getBackTestResult(type, beginDate, endDate, stockPoolName, formationPeriod, holdingPeriod);
		benchMarks = backTestBl.getBenchmark(stockPoolName, beginDate, endDate);
		
		incomeBl = new IncomeBl();
		yieldHitogramBl = new YieldHitogramBl();
	}
	
	@Override
	public IncomeLineVO getTotalIncome() {
		return incomeBl.calculateTotalRate(incomes);
	}

	@Override
	public IncomeLineVO getTotalBenchmarkIncome() {
		return incomeBl.calculateTotalRate(benchMarks);
	}

	@Override
	public int getPositiveIncomeNums() {
		if (strategyRateMap == null) {
			strategyRateMap = yieldHitogramBl.getStrategyRates(incomes, holdingPeriod);
		}
		if (baseRateMap == null) {
			baseRateMap = yieldHitogramBl.getBaseRates(benchMarks, holdingPeriod);
		}
		return yieldHitogramBl.getPositiveIncome(strategyRateMap, baseRateMap);
	}

	@Override
	public int getNegativeIncomeNums() {
		if (strategyRateMap == null) {
			strategyRateMap = yieldHitogramBl.getStrategyRates(incomes, holdingPeriod);
		}
		if (baseRateMap == null) {
			baseRateMap = yieldHitogramBl.getBaseRates(benchMarks, holdingPeriod);
		}
		return yieldHitogramBl.getNegativeIncome(strategyRateMap, baseRateMap);
	}

	@Override
	public double getWinRate() {
		if (strategyRateMap == null) {
			strategyRateMap = yieldHitogramBl.getStrategyRates(incomes, holdingPeriod);
		}
		if (baseRateMap == null) {
			baseRateMap = yieldHitogramBl.getBaseRates(benchMarks, holdingPeriod);
		}
		return yieldHitogramBl.getWinRate(strategyRateMap, baseRateMap);
	}

	@Override
	public HashMap<LocalDate, Double> getStrategyRates() {
		if (strategyRateMap == null) {
			strategyRateMap = yieldHitogramBl.getStrategyRates(incomes, holdingPeriod);
		}
		return strategyRateMap;
	}

	@Override
	public HashMap<LocalDate, Double> getBaseRates() {
		if (baseRateMap == null) {
			baseRateMap = yieldHitogramBl.getBaseRates(benchMarks, holdingPeriod);
		}
		return baseRateMap;
	}

	@Override
	public double getExcessEarnings() {
		IncomeLineVO totalIncomes = incomeBl.calculateTotalRate(incomes);
		IncomeLineVO totalBenchmarks = incomeBl.calculateTotalRate(benchMarks);
		return totalIncomes.get(totalIncomes.size()-1).getRate() - totalBenchmarks.get(totalBenchmarks.size()-1).getRate();
	}

}
