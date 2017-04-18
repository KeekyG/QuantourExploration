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
import vo.StockPoolVO;
import vo.StockShareVO;

public class IncomeBl implements IncomeBlService {

	
	@Override
	public IncomeLineVO calculateIncomeRate(ArrayList<HoldingPeriodStockVO> holdingPeriodStocks) {
		HashMap<LocalDate, Double> incomeMap = new HashMap<>();
		
		for (HoldingPeriodStockVO holdingPeriodStockVO : holdingPeriodStocks) {
			
			HashMap<String, ShareLineVO> stockMap = holdingPeriodStockVO.getStocks();
			
			double size = stockMap.size();
			
			Iterator<String> iterator = stockMap.keySet().iterator();
			while (iterator.hasNext()) {
				String code = (String) iterator.next();
				ShareLineVO shareLineVO = stockMap.get(code);
				for (int i = 0; i < shareLineVO.size(); i++) {
					LocalDate date = TimeUtility.dateToLocalDate(shareLineVO.get(i).getDate());
					if (i == 0) {
						double rate = (shareLineVO.get(i).getAdjClose() - shareLineVO.get(i).getOpen()) / shareLineVO.get(i).getOpen() / size;
						if (incomeMap.containsKey(date)) {
							incomeMap.put(date, incomeMap.get(date) + rate);
						} else {
							incomeMap.put(date, (shareLineVO.get(i).getAdjClose() - shareLineVO.get(i).getOpen()) / shareLineVO.get(i).getOpen());
						}
					} else {
						double rate = (shareLineVO.get(i).getAdjClose() - shareLineVO.get(i-1).getAdjClose()) / shareLineVO.get(i-1).getAdjClose() / size;
						if (incomeMap.containsKey(date)) {
							incomeMap.put(date, incomeMap.get(date) + rate);
						} else {
							incomeMap.put(date, (shareLineVO.get(i).getAdjClose() - shareLineVO.get(i).getOpen()) / shareLineVO.get(i).getOpen());
						}
					}
				}
			}
			
		}
		return calculateTotalRate(new IncomeLineVO(incomeMap));
	}

	@Override
	public IncomeLineVO calculateTotalRate(IncomeLineVO incomeLineVO) {
		int size = incomeLineVO.size();
		for (int i = 1; i < size; i++) {
			incomeLineVO.get(i).setRate(incomeLineVO.get(i-1).getRate() * incomeLineVO.get(i).getRate());
		}
		return incomeLineVO;
	}

	@Override
	public IncomeLineVO calculateAverageIncome(StockPoolVO stockPoolVO) {
		HashMap<LocalDate, Double> incomeMap = new HashMap<>();
		HashMap<LocalDate, Integer> volumeMap = new HashMap<>();
		for (LocalDate date : stockPoolVO.getDateList()) {
			incomeMap.put(date, 0.0);
			volumeMap.put(date, 0);
		}
		
		HashMap<String, ShareLineVO> stockMap = stockPoolVO.getStocks();
		Iterator<String> codeIterator = stockMap.keySet().iterator();
		while (codeIterator.hasNext()) {
			String code = (String) codeIterator.next();
			ShareLineVO shareLineVO = stockMap.get(code);
			for (StockShareVO stockShareVO : shareLineVO) {
				LocalDate date = TimeUtility.dateToLocalDate(stockShareVO.getDate());
				double rate = (stockShareVO.getAdjClose() - stockShareVO.getOpen()) / stockShareVO.getOpen();
				incomeMap.put(date, incomeMap.get(date)+rate);
				volumeMap.put(date, volumeMap.get(date)+1);
			}
		}
		
		Iterator<LocalDate> dateIterator = incomeMap.keySet().iterator();
		while (dateIterator.hasNext()) {
			LocalDate localDate = (LocalDate) dateIterator.next();
			incomeMap.put(localDate, incomeMap.get(localDate) / (double) volumeMap.get(localDate));
		}
		
		return calculateTotalRate(new IncomeLineVO(incomeMap));
	}

}
