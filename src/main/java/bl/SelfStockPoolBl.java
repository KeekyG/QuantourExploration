package bl;

import java.util.ArrayList;
import java.util.HashMap;


import blService.SelfStockPoolBlService;
import blService.TransferBlService;
import data.dao.StockPoolDao;
import data.dao.impl.StockPoolDaoImpl;
import po.StockPoolPO;
import vo.SelfSelectStockPoolVO;

public class SelfStockPoolBl implements SelfStockPoolBlService{
	
	private StockPoolDao stockPoolDao;
	
	private TransferBlService transferBlService;
	
	public SelfStockPoolBl() {
		this.stockPoolDao = new StockPoolDaoImpl();
		this.transferBlService = new TransferBl();
	}

	@Override
	public ArrayList<String> getStockPools() {
		return stockPoolDao.getStockPoolNames();
	}

	@Override
	public SelfSelectStockPoolVO getStocks(String name) {
		return transferBlService.toSelfSelectStockPoolVO(stockPoolDao.getStockPool(name));
	}

	@Override
	public boolean addStockPool(String name) {
		return stockPoolDao.setStockPool(new StockPoolPO(name, new HashMap<>()));
	}

	@Override
	public boolean addStock(String stockPoolName, String stockCode, String stockName) {
		return stockPoolDao.addStock(stockPoolName, stockCode, stockName);
	}

	@Override
	public boolean deleteStockPool(String stockPoolName) {
		return stockPoolDao.deleteStockPool(stockPoolName);
	}

	@Override
	public boolean deleteStock(String stockPoolName, String stockCode) {
		return stockPoolDao.deleteStock(stockPoolName, stockCode);
	}

}
