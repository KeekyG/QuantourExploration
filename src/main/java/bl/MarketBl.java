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
		calculateThermo(date, dailyStockVOs);
		return null;
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
			totalVolume += today.get(i).getVolume();
			System.out.println(today.get(i).getName()+"\t"+today.get(i).getAdjClose()+"\t"+today.get(i+1).getAdjClose()+"\t"+(today.get(i).getAdjClose()-today.get(i+1).getAdjClose())/today.get(i+1).getAdjClose());
		}
		
		return null;

	}
	
	/*public static void main(String[] args) {
		MarketBl bl = new MarketBl();
		Date date = new Date(105, 1, 2);
		for(int i=0; i<1000; i++){
			date.setTime(date.getTime()+86400000L);
		
			System.err.println(date);
			bl.getMarketThermo(date);
		}

	}*/
	
	

}
