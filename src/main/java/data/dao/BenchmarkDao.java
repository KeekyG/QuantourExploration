package data.dao;

import java.time.LocalDate;

import enums.PlateType;
import po.BenchmarkPO;

public interface BenchmarkDao {
	
	/**
	 * 获得基准收益率
	 * 
	 * @param 板块类型
	 * @param 开始日期
	 * @param 结束日期
	 * @return 板块在该日期内的基准收益率
	 */
	public BenchmarkPO getBenchmark(PlateType type, LocalDate beginDate, LocalDate endDate);
}
