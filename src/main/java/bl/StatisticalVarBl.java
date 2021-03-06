package bl;

import java.util.Iterator;

import blService.StatisticalVarBlService;
import po.InterestPO;
import vo.IncomeLineVO;
import vo.PointVO;

public class StatisticalVarBl implements StatisticalVarBlService{

	@Override
	public double getStrategyAnnualRate(IncomeLineVO strategyRates) {
		return (strategyRates.get(strategyRates.size()-1).getRate() / strategyRates.size()) * 365;
	}

	@Override
	public double getBaseAnnualRate(IncomeLineVO baseRates) {
		return (baseRates.get(baseRates.size()-1).getRate() / baseRates.size()) * 365;
	}

	@Override
	public double getAlpha(InterestPO interestPO, IncomeLineVO strategyRates, IncomeLineVO baseRates) {
		//计算无风险利率
		Iterator<String> iterator = interestPO.getInterests().keySet().iterator();
		double rate_interest = 0;
		while(iterator.hasNext()){
			String year = iterator.next();
			rate_interest = interestPO.getInterests().get(year);
		}
		return (getStrategyAnnualRate(strategyRates) - rate_interest) - getBeta(strategyRates, baseRates) * 
				(int)(baseRates.get(baseRates.size()-1).getRate() - rate_interest);
	}

	@Override
	public double getBeta(IncomeLineVO strategyRates, IncomeLineVO baseRates) {
		//策略收益率与基准收益率的协方差
		double cov_SB = 0;
		//基准收益率的方差
		double var_B = 0;
		//期望
		double e_S = 0;
		double e_B = 0;
		double e_SB = 0;
		
		for (PointVO pointVO : strategyRates) {
			e_S += pointVO.getRate();
		}
		e_S /= strategyRates.size();
		for (PointVO pointVO : baseRates) {
			e_B += pointVO.getRate();
		}
		e_B /= strategyRates.size();
		for (int i = 0; i < strategyRates.size(); i++) {
			e_SB += strategyRates.get(i).getRate() * baseRates.get(i).getRate();
		}
		e_SB /= strategyRates.size();
		cov_SB = e_SB - e_S * e_B;
		
		for (PointVO pointVO : baseRates) {
			var_B += Math.pow(pointVO.getRate() - e_B, 2);
		}
		var_B /= strategyRates.size();
		return cov_SB/var_B;
	}

	@Override
	public double getSharpeRatio(IncomeLineVO strategyRates, InterestPO interestPO) {
		//策略收益率的标准差
		double s_S = 0;
		
		double e_S = 0;
		for (PointVO pointVO : strategyRates) {
			e_S += pointVO.getRate();
		}
		e_S /= strategyRates.size();
		for (PointVO pointVO : strategyRates) {
			s_S += Math.pow(pointVO.getRate() - e_S, 2);
		}
		s_S = Math.sqrt(s_S / strategyRates.size());
		//计算无风险利率
		Iterator<String> iterator = interestPO.getInterests().keySet().iterator();
		double rate_interest = 0;
		while(iterator.hasNext()){
			String year = iterator.next();
			rate_interest = interestPO.getInterests().get(year);
		}
		return (strategyRates.get(strategyRates.size()-1).getRate() - rate_interest) / s_S;
	}

	@Override
	public double getMaxDrawDown(IncomeLineVO strategyRates) {
		double maxDrawDown = 0;
		double maxTmp = 0;
		double beginRate = strategyRates.get(0).getRate();
		double endRate = strategyRates.get(1).getRate();
		double beforeEndRate = 0;
		int flag = 1;
		while(flag < strategyRates.size()){
			if(maxTmp > maxDrawDown){
				maxDrawDown = maxTmp;
			}
			if(endRate < beginRate){
				maxDrawDown = beginRate - endRate;
				flag++;
				endRate = strategyRates.get(flag).getRate();
				beforeEndRate = strategyRates.get(flag-1).getRate();
				if(beforeEndRate < endRate){
					if(maxTmp < maxDrawDown){
						maxTmp = maxDrawDown;
					}
					beginRate = endRate;
					flag++;
					endRate = strategyRates.get(flag).getRate();
					beforeEndRate = strategyRates.get(flag-1).getRate();
				}
				else{
					maxDrawDown = beginRate - endRate;
					flag++;
					if(flag < strategyRates.size()){
						endRate = strategyRates.get(flag).getRate();
						beforeEndRate = strategyRates.get(flag-1).getRate();
					}
				}
			}
			else{
				beginRate = endRate;
				flag++;
				if(flag < strategyRates.size()){
					endRate = strategyRates.get(flag).getRate();
					beforeEndRate = strategyRates.get(flag-1).getRate();
				}
			}
			
		}
		if(maxTmp > maxDrawDown){
			maxDrawDown = maxTmp;
		}
		return maxDrawDown;
	}

}
