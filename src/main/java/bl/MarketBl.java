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
		
		ArrayList<DailyStockVO> dailyStockVOs = new ArrayList<>();
		dailyStockVOs = stockPOs.stream().map(a -> transferBlService.toDailyStockVO(a)).collect(Collectors.toCollection(ArrayList::new));
		dailyStockVOs.sort((a, b) -> a.getCode().compareTo(b.getCode()));
		return calculateThermo(date, dailyStockVOs);
	}
	
	private ThermometerVO calculateThermo(Date date, ArrayList<DailyStockVO> today) {
		
		int length = today.size();
		
		long totalVolume = 0;
		
		long upNum = 0;
		
		long downNum = 0;
		
		long fivePercentUpNum = 0;
		
		long fivePercentDownNum = 0;
		
		long fivePercentBiggerNum = 0;
		
		long fivePercentSmallerNum = 0;
		
		for (int i = 0; i < length; i+=2) {
			DailyStockVO todayStockVO = today.get(i);
			DailyStockVO yesterdayStockVO = today.get(i+1);
			totalVolume += todayStockVO.getVolume();
			double open = todayStockVO.getOpen();
			double yesterdayClose = yesterdayStockVO.getAdjClose();
			double todayClose = todayStockVO.getAdjClose();
			
			double change = (todayClose - yesterdayClose) / yesterdayClose;
			if (change > 0.05) {
				fivePercentUpNum++;
			} else if(change < 0.05) {
				fivePercentDownNum++;
			}
			
			if (change > 0.095) {
				upNum++;
			} else if (change < -0.095) {
				downNum++;
			}
			
			double wierd = yesterdayClose * 0.05;
			
			if ((open - todayClose) > wierd) {
				fivePercentBiggerNum++;
			}
			if ((open - todayClose) < -1*wierd) {
				fivePercentSmallerNum++;
			}
			
		}
		
		return new ThermometerVO(date, totalVolume, upNum, downNum, fivePercentUpNum, fivePercentDownNum, fivePercentBiggerNum, fivePercentSmallerNum);

	}
		
}
