package bl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import blService.IncomeBlService;
import blService.YieldHitogramBlService;
import vo.IncomeLineVO;

public class YieldHitogramBl implements YieldHitogramBlService{

	@Override
	public int getPositiveIncome(HashMap<LocalDate, Double> strategyRates, HashMap<LocalDate, Double> baseRates) {
		int positiveNum = 0;
		Iterator<LocalDate> iterator1 = strategyRates.keySet().iterator();
		ArrayList<Double> S_Rates = new ArrayList<>();
		while(iterator1.hasNext()){
			LocalDate localDate = iterator1.next();
			S_Rates.add(strategyRates.get(localDate));
		}
		Iterator<LocalDate> iterator2 = baseRates.keySet().iterator();
		ArrayList<Double> B_Rates = new ArrayList<>();
		while(iterator2.hasNext()){
			LocalDate localDate = iterator2.next();
			B_Rates.add(baseRates.get(localDate));
		}
		for (int i = 0; i < S_Rates.size(); i++) {
			if(S_Rates.get(i) >= B_Rates.get(i)){
				positiveNum++;
			}
		}
		return positiveNum;
	}

	@Override
	public int getNegativeIncome(HashMap<LocalDate, Double> strategyRates, HashMap<LocalDate, Double> baseRates) {
		return strategyRates.size() - getPositiveIncome(strategyRates, baseRates);
	}

	@Override
	public double getWinRate(HashMap<LocalDate, Double> strategyRates, HashMap<LocalDate, Double> baseRates) {
		
		return getPositiveIncome(strategyRates, baseRates) / (getPositiveIncome(strategyRates, baseRates) + getNegativeIncome(strategyRates, baseRates));
	}

	@Override
	public HashMap<LocalDate, Double> getStrategyRates(IncomeLineVO strategyRates, int holdingPeriodDay) {
		//周期数
		int num_cycle = strategyRates.size()/holdingPeriodDay + 1;
		//最后一个周期的天数
		int last_cycle = strategyRates.size()%holdingPeriodDay;
		IncomeBlService incomeBlService = new IncomeBl();
		HashMap<LocalDate, Double> strategyMap = new HashMap<>();
		int flag = 0;
		int times = 1;
		while(times != num_cycle-1){
			HashMap<LocalDate, Double> everyCycleMap = new HashMap<>();
			for (int i = flag; i < flag+holdingPeriodDay; i++) {
				everyCycleMap.put(strategyRates.get(flag).getDate(), strategyRates.get(flag+holdingPeriodDay).getRate());
			}
			IncomeLineVO everyCycle = new IncomeLineVO(everyCycleMap);
			IncomeLineVO everyCycleTotal = incomeBlService.calculateTotalRate(everyCycle);
			strategyMap.put(everyCycleTotal.get(holdingPeriodDay-1).getDate(), everyCycleTotal.get(holdingPeriodDay-1).getRate());
			flag+=holdingPeriodDay;
			times++;
		}
		HashMap<LocalDate, Double> lastCycleMap = new HashMap<>();
		for(int i = strategyRates.size()-last_cycle; i < strategyRates.size(); i++){
			lastCycleMap.put(strategyRates.get(strategyRates.size()-last_cycle).getDate(), strategyRates.get(strategyRates.size()).getRate());
		}
		IncomeLineVO lastCycle = new IncomeLineVO(lastCycleMap);
		IncomeLineVO lastCycleTotal = incomeBlService.calculateTotalRate(lastCycle);
		strategyMap.put(lastCycleTotal.get(last_cycle-1).getDate(), lastCycleTotal.get(last_cycle-1).getRate());
		return strategyMap;
	}

	@Override
	public HashMap<LocalDate, Double> getBaseRates(IncomeLineVO baseRates, int holdingPeriodDay) {
		int num_cycle = baseRates.size()/holdingPeriodDay + 1;
		int last_cycle = baseRates.size()%holdingPeriodDay;
		IncomeBlService incomeBlService = new IncomeBl();
		HashMap<LocalDate, Double> baseMap = new HashMap<>();
		int flag = 0;
		int times = 1;
		while(times != num_cycle-1){
			HashMap<LocalDate, Double> everyCycleMap = new HashMap<>();
			for (int i = flag; i < flag+holdingPeriodDay; i++) {
				everyCycleMap.put(baseRates.get(flag).getDate(), baseRates.get(flag+holdingPeriodDay).getRate());
			}
			IncomeLineVO everyCycle = new IncomeLineVO(everyCycleMap);
			IncomeLineVO everyCycleTotal = incomeBlService.calculateTotalRate(everyCycle);
			baseMap.put(everyCycleTotal.get(holdingPeriodDay-1).getDate(), everyCycleTotal.get(holdingPeriodDay-1).getRate());
			flag+=holdingPeriodDay;
			times++;
		}
		HashMap<LocalDate, Double> lastCycleMap = new HashMap<>();
		for(int i = baseRates.size()-last_cycle; i < baseRates.size(); i++){
			lastCycleMap.put(baseRates.get(baseRates.size()-last_cycle).getDate(), baseRates.get(baseRates.size()).getRate());
		}
		IncomeLineVO lastCycle = new IncomeLineVO(lastCycleMap);
		IncomeLineVO lastCycleTotal = incomeBlService.calculateTotalRate(lastCycle);
		baseMap.put(lastCycleTotal.get(last_cycle-1).getDate(), lastCycleTotal.get(last_cycle-1).getRate());
		return baseMap;
	}


}
