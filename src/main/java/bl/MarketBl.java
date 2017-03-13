package bl;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import blService.MarketBlService;
import blService.TransferBlService;
import data.dao.StockDao;
import data.dao.impl.StockDaoImpl;
import po.StockUpDownPO;
import utility.TimeUtility;
import vo.DailyStockVO;
import vo.ThermometerVO;

public class MarketBl implements MarketBlService {

	private StockDao stockDao;
	
	private TransferBlService transferBlService;
	
	public MarketBl() {
		stockDao = new StockDaoImpl();
		transferBlService = new TransferBl();
	}
	
	@Override
	public ThermometerVO getMarketThermo(Date date) {
		ArrayList<StockUpDownPO> stockPOs = stockDao.getDailyStock(date);
		ArrayList<StockUpDownPO> beforeStockPOs = stockDao.getDailyStock(TimeUtility.getDayBefore(date));
		
		ArrayList<DailyStockVO> dailyStockVOs = new ArrayList<>();
		ArrayList<DailyStockVO> beforeDayStockVOs = new ArrayList<>();
		dailyStockVOs = stockPOs.stream().map(a -> transferBlService.toDailyStockVO(a)).collect(Collectors.toCollection(ArrayList::new));
		beforeDayStockVOs = beforeStockPOs.stream().map(a -> transferBlService.toDailyStockVO(a)).collect(Collectors.toCollection(ArrayList::new));
		dailyStockVOs.sort((a, b) -> a.getCode().compareTo(b.getCode()));
		beforeDayStockVOs.sort((a, b) -> a.getCode().compareTo(b.getCode()));
		
		
	}
	
	private ThermometerVO calculateThermo(Date date, ArrayList<DailyStockVO> before, ArrayList<DailyStockVO> today) {
		int bLength = before.size();
		int tLength = today.size();
		if (bLength != tLength) {
			return null;
		}
		
		long totalVolume = 0;
		
		long upNum = 0;
		
		long downNum = 0;
		
		long fivePercentUpNum = 0;
		
		long fivePercentDownNum = 0;
		
		long fivePercentBiggerNum = 0;
		
		long fivePercentSmallerNum = 0;
		
		for (int i = 0; i < bLength; i++) {
			totalVolume += today.get(i).getVolume();
			
		}
	}

}
