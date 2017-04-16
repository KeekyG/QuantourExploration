package bl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import blService.FormationPeriodBlService;
import vo.FormationPeriodStockVO;
import vo.ShareLineVO;
import vo.StockPoolVO;

public class FormationPeriodBl implements FormationPeriodBlService {

	@Override
	public ArrayList<FormationPeriodStockVO> getFormationPeriods(StockPoolVO stockPool, int formationDays,
			int holdingDays) {
		ArrayList<FormationPeriodStockVO> fomationPeriods = new ArrayList<>();
		
		HashMap<String, ShareLineVO> stocksInPool = stockPool.getStocks();
		
		ArrayList<ArrayList<LocalDate>> periods = new ArrayList<>();
		ArrayList<LocalDate> dateList = stockPool.getDateList();
		int size = dateList.size();
		int times = size / (formationDays + holdingDays);
		for (int i = 0; i < size; i+=(formationDays+holdingDays)) {
			List<LocalDate> subList = dateList.subList(i, i+formationDays);
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
			
			
			
		}
		
		
		return fomationPeriods;
	}

}
