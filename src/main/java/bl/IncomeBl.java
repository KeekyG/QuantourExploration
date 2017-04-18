package bl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import blService.IncomeBlService;
import utility.TimeUtility;
import vo.HoldingPeriodStockVO;
import vo.IncomeLineVO;
import vo.ShareLineVO;

public class IncomeBl implements IncomeBlService {

	
	@Override
	public IncomeLineVO calculateTotalIncomeRate(ArrayList<HoldingPeriodStockVO> holdingPeriodStocks) {
		HashMap<LocalDate, Double> incomeMap = new HashMap<>();
		int size = holdingPeriodStocks.get(0).getStocks().size();
		for (HoldingPeriodStockVO holdingPeriodStockVO : holdingPeriodStocks) {
			
			HashMap<String, ShareLineVO> stockMap = holdingPeriodStockVO.getStocks();
			
			Iterator<String> iterator = stockMap.keySet().iterator();
			while (iterator.hasNext()) {
				String code = (String) iterator.next();
				ShareLineVO shareLineVO = stockMap.get(code);
				for (int i = 0; i < shareLineVO.size(); i++) {
					LocalDate date = TimeUtility.dateToLocalDate(shareLineVO.get(i).getDate());
					if (i == 0) {
						double rate = (shareLineVO.get(i).getAdjClose() - shareLineVO.get(i).getOpen()) / shareLineVO.get(i).getOpen();
						if (incomeMap.containsKey(date)) {
							incomeMap.put(date, incomeMap.get(date) + rate);
						} else {
							incomeMap.put(date, (shareLineVO.get(i).getAdjClose() - shareLineVO.get(i).getOpen()) / shareLineVO.get(i).getOpen());
						}
					} else {
						double rate = (shareLineVO.get(i).getAdjClose() - shareLineVO.get(i-1).getAdjClose()) / shareLineVO.get(i-1).getAdjClose();
						if (incomeMap.containsKey(date)) {
							incomeMap.put(date, incomeMap.get(date) + rate);
						} else {
							incomeMap.put(date, (shareLineVO.get(i).getAdjClose() - shareLineVO.get(i).getOpen()) / shareLineVO.get(i).getOpen());
						}
					}
				}
			}
			
		}
		return null;
	}

	@Override
	public IncomeLineVO calculateTotalBenchmarkRate(IncomeLineVO incomeLineVO) {
		int size = incomeLineVO.size();
		for (int i = 1; i < size; i++) {
			incomeLineVO.get(i).setRate(incomeLineVO.get(i-1).getRate() * incomeLineVO.get(i).getRate());
		}
		return incomeLineVO;
	}

}
