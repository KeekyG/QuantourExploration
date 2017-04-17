package blService;

import java.util.ArrayList;

import vo.FormationPeriodStockVO;
import vo.StockPoolVO;

public interface FormationPeriodBlService {
	
	/**
	 * 生成每个周期列表（包括形成期和持有期）
	 * @param stockPool 股票池
	 * @param formationDays 形成期长度
	 * @param holdingDays 持有期长度
	 * @return 形成期列表
	 */
	public ArrayList<FormationPeriodStockVO> getFormationPeriods(StockPoolVO stockPool, int formationDays, int holdingDays);
	
	/**
	 * 将每个周期列表中的形成期取出
	 * @param formationPeriodStockVO getFormationPeriods产生的周期列表
	 * @param formationDays 形成期
	 * @param holdingDays 持有期
	 * @return 形成期股票列表
	 */
	public ArrayList<FormationPeriodStockVO> splitToFormations(ArrayList<FormationPeriodStockVO> stockPeriods, int formationDays, int holdingDays);
	
	/**
	 * 将每个周期列表中的持有期取出
	 * @param formationPeriodStockVO getFormationPeriods产生的周期列表
	 * @param formationDays 形成期
	 * @param holdingDays 持有期
	 * @return 持有期股票列表
	 */
	public ArrayList<FormationPeriodStockVO> splitToHoldings(ArrayList<FormationPeriodStockVO> stockPeriods, int formationDays, int holdingDays);
}
