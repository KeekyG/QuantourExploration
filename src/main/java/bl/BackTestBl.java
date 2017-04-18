package bl;

import java.time.LocalDate;

import blService.BackTestBlService;
import blService.StockPoolBlService;
import enums.StrategyType;
import vo.IncomeLineVO;
import vo.StockPoolVO;

public class BackTestBl implements BackTestBlService {

	@Override
	public IncomeLineVO getBackTestResult(StrategyType type, LocalDate beginDate, LocalDate endDate,
			String stockPoolName, int formationPeriod, int holdingPeriod) {
		
		StockPoolVO stockPoolVO;
		StockPoolBlService stockPoolBlService;
		if (stockPoolName.equals("HS300") || stockPoolName.equals("SME") ||  stockPoolName.equals("GEM")) {
			
		} else {
			
		}
		return null;
	}

	@Override
	public IncomeLineVO getBenchmark(String stockPoolName, LocalDate beginDate, LocalDate endDate) {
		// TODO Auto-generated method stub
		return null;
	}

}
