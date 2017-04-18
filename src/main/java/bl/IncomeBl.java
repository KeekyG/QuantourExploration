package bl;

import blService.IncomeBlService;
import vo.HoldingPeriodStockVO;
import vo.IncomeLineVO;

public class IncomeBl implements IncomeBlService {

	@Override
	public IncomeLineVO calculateTotalIncomeRate(HoldingPeriodStockVO holdingPeriodStocks) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IncomeLineVO calculateTotalBenchmarkRate(HoldingPeriodStockVO holdingPeriodStocks,
			IncomeLineVO incomeLineVO) {
		int size = incomeLineVO.size();
		for (int i = 1; i < size; i++) {
			incomeLineVO.get(i).setRate(incomeLineVO.get(i-1).getRate() * incomeLineVO.get(i).getRate());
		}
		return null;
	}

}
