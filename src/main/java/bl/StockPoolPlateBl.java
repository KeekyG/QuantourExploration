package bl;

import java.time.LocalDate;
import java.util.ArrayList;

import blService.BenchmarkBlService;
import blService.StockBlService;
import blService.StockPoolBlService;
import data.dao.PoolCodeDao;
import data.dao.impl.PoolCodeDaoImpl;
import enums.PlateType;
import enums.StockPoolType;
import utility.TimeUtility;
import vo.IncomeLineVO;
import vo.PointVO;
import vo.ShareLineVO;
import vo.StockPoolVO;

/**
 * @author ssw's daddy
 * 
 * 板块股票池的操作
 *
 */
public class StockPoolPlateBl implements StockPoolBlService {

	private PoolCodeDao poolCodeDao;
	private StockBlService stockBl;
	private BenchmarkBlService benchmarkBl;
	
	public StockPoolPlateBl() {
		this.poolCodeDao = new PoolCodeDaoImpl();
		this.stockBl = new StockBl();
		this.benchmarkBl = new BenchmarkBl();
	}
	
	@Override
	public StockPoolVO getStockPool(String name, LocalDate beginDate, LocalDate endDate) {
		StockPoolVO stockPoolVO;
		ArrayList<String> codeList = new ArrayList<>();
		IncomeLineVO incomeLineVO = null;
		if (name.equals("HS300")) {
			codeList = poolCodeDao.getHS300Code();
			incomeLineVO = benchmarkBl.getBenchmark(PlateType.HS300, beginDate, endDate);
		} else if (name.equals("SME")) {
			codeList = poolCodeDao.getSMEcode();
			incomeLineVO = benchmarkBl.getBenchmark(PlateType.SME, beginDate, endDate);
		} else if (name.equals("GEM")) {
			codeList = poolCodeDao.getGEMcode();
			incomeLineVO = benchmarkBl.getBenchmark(PlateType.GEM, beginDate, endDate);
		}
		
		ArrayList<ShareLineVO> poolStocks = new ArrayList<>();
		
		for (String code : codeList) {
			poolStocks.add(stockBl.getShareLine(TimeUtility.localDateToDate(beginDate), TimeUtility.localDateToDate(endDate), code));
		}
		
		ArrayList<LocalDate> dateList = new ArrayList<>();
		for (PointVO pointVO : incomeLineVO) {
			dateList.add(pointVO.getDate());
		}
		
		stockPoolVO = new StockPoolVO(StockPoolType.PLATE, name, beginDate, endDate, poolStocks, dateList);
		
		return stockPoolVO;
	}

}
