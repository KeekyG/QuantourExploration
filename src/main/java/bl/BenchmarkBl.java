package bl;

import java.time.LocalDate;

import blService.BenchmarkBlService;
import data.dao.BenchmarkDao;
import data.dao.impl.BenchmarkDaoImpl;
import enums.PlateType;
import po.BenchmarkPO;
import vo.IncomeLineVO;

public class BenchmarkBl implements BenchmarkBlService{
	
	private BenchmarkDao benchmarkDao;
	
	public BenchmarkBl() {
		this.benchmarkDao = new BenchmarkDaoImpl();
	}

	@Override
	public IncomeLineVO getBenchmark(PlateType type, LocalDate beginDate, LocalDate endDate) {
		BenchmarkPO benchmarkPO = benchmarkDao.getBenchmark(type, beginDate, endDate);
		return new IncomeLineVO(benchmarkPO.getChanges());
	}

}
