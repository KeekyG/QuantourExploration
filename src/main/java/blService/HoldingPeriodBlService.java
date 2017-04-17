package blService;

import java.util.ArrayList;

import vo.FormationPeriodStockVO;
import vo.HoldingPeriodStockVO;

public interface HoldingPeriodBlService {
	
	/**
	 * 根据算法对形成期股票进行排序，筛选持有期股票
	 * @param formationList 形成期股票列表的列表
	 * @param holdingList 所有形成期股票在持有期中的涨跌情况
	 * @return 持有期列表的列表
	 */
	public ArrayList<HoldingPeriodStockVO> getHoldingPeriods(ArrayList<FormationPeriodStockVO> formationList, ArrayList<FormationPeriodStockVO> holdingList);

}
