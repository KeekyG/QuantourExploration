package blService;

import java.time.LocalDate;

import enums.PlateType;
import vo.IncomeLineVO;

public interface BenchMarkBlService {
	
	/**
	 * 获取股票基准指标信息
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public IncomeLineVO getBenchMark(PlateType type, LocalDate beginDate, LocalDate endDate);
}
