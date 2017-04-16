package blService;

import java.util.ArrayList;

import vo.FormationPeriodStockVO;
import vo.StockPoolVO;

public interface FormationPeriodBlService {
	
	/**
	 * 生成形成期列表
	 * @param stockPool 股票池
	 * @param formationDays 形成期长度
	 * @param holdingDays 持有期长度
	 * @return 形成期列表
	 */
	public ArrayList<FormationPeriodStockVO> getFormationPeriods(StockPoolVO stockPool, int formationDays, int holdingDays);
}
