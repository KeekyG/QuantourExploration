package bl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.stream.Collectors;

import blService.HoldingPeriodBlService;
import vo.FormationPeriodStockVO;
import vo.HoldingPeriodStockVO;
import vo.ShareLineVO;

/**
 * @author hhh
 * 
 * 均值回归策略的持有期选择
 *
 */
public class HoldingPeriodMeanReversionBl implements HoldingPeriodBlService {

	@Override
	public ArrayList<HoldingPeriodStockVO> getHoldingPeriods(ArrayList<FormationPeriodStockVO> formationList,
			ArrayList<FormationPeriodStockVO> holdingList) {
		int size = formationList.size();
		ArrayList<HoldingPeriodStockVO> holdingPeriodStockVOs = new ArrayList<>();
		
		for (int i = 0; i < size; i++) {
			FormationPeriodStockVO formationVO = formationList.get(i);
			FormationPeriodStockVO holdingRawVO = holdingList.get(i);
			HashMap<String, ShareLineVO> holdingRawMap = holdingRawVO.getStocks();
			ArrayList<String> sortedCodeList = sortStocks(formationVO.getStocks());
			HashMap<String, ShareLineVO> holdingMap = new HashMap<>();
			int codeNum = sortedCodeList.size();
			int firstFifth = (codeNum % 5 == 0) ? codeNum / 5 : codeNum / 5 + 1;
			for (int j = 0; j < firstFifth; j++) {
				holdingMap.put(sortedCodeList.get(i), holdingRawMap.get(sortedCodeList.get(i)));
			}
			
			HoldingPeriodStockVO holdingPeriodStockVO = new HoldingPeriodStockVO(holdingRawVO.getBeginDate(), holdingRawVO.getEndDate(), holdingMap);
			holdingPeriodStockVOs.add(holdingPeriodStockVO);
		}
		return holdingPeriodStockVOs;
	}
	
	private ArrayList<String> sortStocks(HashMap<String, ShareLineVO> stockMap) {
		HashMap<String, Double> incomeMap = new HashMap<>();
		Iterator<String> iterator = stockMap.keySet().iterator();
		while (iterator.hasNext()) {
			String code = (String) iterator.next();
			double price = stockMap.get(code).get(stockMap.get(code).size() - 1).getAdjClose();
			ArrayList<Double> priceList = stockMap.get(code).stream().map(a -> a.getAdjClose()).collect(Collectors.toCollection(ArrayList::new));
			double avg = average(priceList);
			double mean = (price - avg) / price;
			incomeMap.put(code, mean);
		}
		ArrayList<String> sortedList = new ArrayList<>(stockMap.keySet());
		sortedList.sort((a, b) -> (incomeMap.get(a) > incomeMap.get(b) ? -1 : 1));
		return sortedList;
	}
	
	private double average(ArrayList<Double> nums) {
		double sum = nums.stream().reduce((double) 0, (result, element) ->  result = result + element);
		return sum / (double) nums.size();
	}

}
