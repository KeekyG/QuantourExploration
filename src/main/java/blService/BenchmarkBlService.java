package blService;

import java.time.LocalDate;

import enums.PlateType;
import vo.IncomeLineVO;

public interface BenchmarkBlService {
	
	/**
	 * 获取股票基准指标信息
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public IncomeLineVO getBenchmark(PlateType type, LocalDate beginDate, LocalDate endDate);
}
