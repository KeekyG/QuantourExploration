package bl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import blService.SelfStockPoolBlService;
import blService.StockBlService;
import blService.StockPoolBlService;
import enums.StockPoolType;
import utility.TimeUtility;
import vo.SelfSelectStockPoolVO;
import vo.ShareLineVO;
import vo.StockPoolVO;
import vo.StockShareVO;

/**
 * @author ssw's daddy
 * 
 * 自选股票池的操作
 */
public class StockPoolSelfBl implements StockPoolBlService {

	private SelfStockPoolBlService selfStockPoolBl;
	private StockBlService stockBl;
	
	public StockPoolSelfBl() {
		this.selfStockPoolBl = new SelfStockPoolBl();
		this.stockBl = new StockBl();
	}
	
	@Override
	public StockPoolVO getStockPool(String name, LocalDate beginDate, LocalDate endDate) {
		StockPoolVO stockPoolVO;
		
		HashMap<String, String> stocks = selfStockPoolBl.getStocks(name).getStocks();
		ArrayList<ShareLineVO> poolStocks = new ArrayList<>();
		
		for (String code : stocks.keySet()) {
			poolStocks.add(stockBl.getShareLine(TimeUtility.localDateToDate(beginDate), TimeUtility.localDateToDate(endDate), code));
		}
		
		HashSet<LocalDate> dates = new HashSet<>();
		
		for (ShareLineVO shareLineVO : poolStocks) {
			for (StockShareVO stockShareVO : shareLineVO) {
				dates.add(TimeUtility.dateToLocalDate(stockShareVO.getDate()));
			}
		}
		
		ArrayList<LocalDate> dateList = new ArrayList<>(dates);
		
		dateList.sort((a, b) -> a.isBefore(b) ? -1 : 1);
		
		stockPoolVO = new StockPoolVO(StockPoolType.SELF_CHOOSE, name, beginDate, endDate, poolStocks, dateList);
		
		return stockPoolVO;
	}
	

}
