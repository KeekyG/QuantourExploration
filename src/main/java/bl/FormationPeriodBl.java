package bl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import blService.FormationPeriodBlService;
import utility.TimeUtility;
import vo.FormationPeriodStockVO;
import vo.ShareLineVO;
import vo.StockPoolVO;
import vo.StockShareVO;

public class FormationPeriodBl implements FormationPeriodBlService {

	@Override
	public ArrayList<FormationPeriodStockVO> getFormationPeriods(StockPoolVO stockPool, int formationDays,
			int holdingDays) {
		ArrayList<FormationPeriodStockVO> fomationPeriods = new ArrayList<>();
		
		HashMap<String, ShareLineVO> stocksInPool = stockPool.getStocks();
		
		ArrayList<ArrayList<LocalDate>> periods = new ArrayList<>();
		ArrayList<LocalDate> dateList = stockPool.getDateList();
		int size = dateList.size();
		int times = (size - formationDays) / holdingDays;
		for (int i = 0; i < size; i+=holdingDays) {
			List<LocalDate> subList = dateList.subList(i, i+formationDays+holdingDays);
			ArrayList<LocalDate> innerList = new ArrayList<>();
			for (LocalDate localDate : subList) {
				innerList.add(localDate);
			}
		}
		if ((size % (formationDays + holdingDays)) > formationDays) {
			List<LocalDate> subList = dateList.subList(times*size-1, size);
			ArrayList<LocalDate> innerList = new ArrayList<>();
			for (LocalDate localDate : subList) {
				innerList.add(localDate);
			}
		}
		
		for (ArrayList<LocalDate> period : periods) {
			FormationPeriodStockVO formationPeriodStockVO;
			HashMap<String, ShareLineVO> stockMap = new HashMap<>();
			
			Iterator<String> iterator = stocksInPool.keySet().iterator();
			while (iterator.hasNext()) {
				String code = (String) iterator.next();
				ShareLineVO singleStockLine = stocksInPool.get(code);
				int index = -1;
				for (StockShareVO stockShareVO : singleStockLine) {
					Date date = TimeUtility.localDateToDate(period.get(0));
					if (stockShareVO.getDate().equals(date)) {
						index = singleStockLine.indexOf(stockShareVO);
					}
				}
				if (index > -1 && (index + formationDays) <= singleStockLine.size() && TimeUtility.localDateToDate(period.get(period.size()-1)).equals(singleStockLine.get(index+formationDays+holdingDays-1))) {
					ArrayList<StockShareVO> formationStocks = new ArrayList<>();
					for (int i = index; i < index + formationDays; i++) {
						formationStocks.add(singleStockLine.get(i));
					}
					stockMap.put(code, new ShareLineVO(formationStocks, singleStockLine.getBeginDate(), singleStockLine.getEndDate(), code, singleStockLine.getName(), singleStockLine.getYesterdayShare()));
				}
			}
			
			formationPeriodStockVO = new FormationPeriodStockVO(period.get(0), period.get(period.size()-1), stockMap);
			fomationPeriods.add(formationPeriodStockVO);
		}
		
		
		return fomationPeriods;
	}

}
