package bl;

import java.time.LocalDate;
import java.util.ArrayList;

import blService.BackTestBlService;
import blService.BenchmarkBlService;
import blService.FormationPeriodBlService;
import blService.HoldingPeriodBlService;
import blService.IncomeBlService;
import blService.StockPoolBlService;
import enums.PlateType;
import enums.StrategyType;
import vo.FormationPeriodStockVO;
import vo.HoldingPeriodStockVO;
import vo.IncomeLineVO;
import vo.StockPoolVO;

public class BackTestBl implements BackTestBlService {

	private StockPoolVO stockPoolVO;
	private IncomeBlService incomeBl;
	
	public BackTestBl(String stockPoolName, LocalDate beginDate, LocalDate endDate) {
		incomeBl = new IncomeBl();
		
		StockPoolBlService stockPoolBlService;
		if (stockPoolName.equals("HS300") || stockPoolName.equals("SME") ||  stockPoolName.equals("GEM")) {
			stockPoolBlService = new StockPoolPlateBl();
			stockPoolVO = stockPoolBlService.getStockPool(stockPoolName, beginDate, endDate);
		} else {
			stockPoolBlService = new StockPoolSelfBl();
			stockPoolVO = stockPoolBlService.getStockPool(stockPoolName, beginDate, endDate);
		}
	}
	
	@Override
	public IncomeLineVO getBackTestResult(StrategyType type, LocalDate beginDate, LocalDate endDate,
			String stockPoolName, int formationPeriod, int holdingPeriod) {
		
		FormationPeriodBlService formationPeriodBlService = new FormationPeriodBl();
		ArrayList<FormationPeriodStockVO> periodStocks = formationPeriodBlService.getFormationPeriods(stockPoolVO, formationPeriod, holdingPeriod);
		ArrayList<FormationPeriodStockVO> formationPeriodStocks = formationPeriodBlService.splitToFormations(periodStocks, formationPeriod, holdingPeriod);
		ArrayList<FormationPeriodStockVO> holdingPeriodStocks = formationPeriodBlService.splitToHoldings(periodStocks, formationPeriod, holdingPeriod);
		
		ArrayList<HoldingPeriodStockVO> holdingPeriodStockVOs;
		HoldingPeriodBlService holdingPeriodBlService;
		if (type.equals(StrategyType.MOMENTUM)) {
			holdingPeriodBlService = new HoldingPeriodMomentumBl();
			holdingPeriodStockVOs = holdingPeriodBlService.getHoldingPeriods(formationPeriodStocks, holdingPeriodStocks);
		} else {
			holdingPeriodBlService = new HoldingPeriodMeanReversionBl();
			holdingPeriodStockVOs = holdingPeriodBlService.getHoldingPeriods(formationPeriodStocks, holdingPeriodStocks);
		}
		
		
		return incomeBl.calculateIncomeRate(holdingPeriodStockVOs);
	}

	@Override
	public IncomeLineVO getBenchmark(String stockPoolName, LocalDate beginDate, LocalDate endDate) {
		if (stockPoolName.equals("HS300") || stockPoolName.equals("GEM") || stockPoolName.equals("SME")) {
			BenchmarkBlService benchmarkBl = new BenchmarkBl();
			if (stockPoolName.equals("HS300")) {
				return benchmarkBl.getBenchmark(PlateType.HS300, beginDate, endDate);
			} else if (stockPoolName.equals("GEM")) {
				return benchmarkBl.getBenchmark(PlateType.GEM, beginDate, endDate);
			} else {
				return benchmarkBl.getBenchmark(PlateType.SME, beginDate, endDate);
			}
		} else {
			return incomeBl.calculateAverageIncome(stockPoolVO);
		}
	}

}
