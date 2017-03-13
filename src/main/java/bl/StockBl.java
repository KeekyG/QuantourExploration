package bl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;

import blService.StockBlService;
import blService.TransferBlService;
import data.dao.StockDao;
import data.dao.StockNameDao;
import data.dao.impl.StockDaoImpl;
import data.dao.impl.StockNameDaoImpl;
import po.StockUpDownPO;
import vo.ShareLineVO;
import vo.StockShareVO;

public class StockBl implements StockBlService {

	private StockDao stockDao;

	private StockNameDao stockNameDao;
	
	private TransferBlService transferBlService;

	private ArrayList<String> codeList;

	private HashMap<String, String> nameMap;
	
	private ArrayList<String> nameList;

	public StockBl() {
		stockDao = new StockDaoImpl();
		stockNameDao = new StockNameDaoImpl();
		transferBlService = new TransferBl();
		codeList = stockNameDao.getStockCode();
		nameMap = stockNameDao.getStockName();
		nameList = new ArrayList<>();
		nameList.addAll(nameMap.keySet());
	}

	@Override
	public ArrayList<String> getPossibleCodes(String head) {
		return codeList.stream().filter(s -> s.startsWith(head)).collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public ArrayList<String> getPossibleNames(String head) {
		return nameList.stream().filter(s -> s.startsWith(head)).collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public ShareLineVO getShareLine(Date beginTime, Date endTime, String code) {
		if(!codeList.contains(code)) {
			return null;
		} else {
			ArrayList<StockUpDownPO> pos = stockDao.getSearchStocks(beginTime, endTime, code);
			ArrayList<StockShareVO> shareVOs = pos.stream().map(a -> transferBlService.toStockShareVO(a)).collect(Collectors.toCollection(ArrayList::new));
			return transferBlService.toShareLineVO(shareVOs, beginTime, endTime, code, shareVOs.get(0).getName());
		}
	}

	@Override
	public ShareLineVO getShareLineByName(Date beginTime, Date endTime, String name) {
		if (!nameList.contains(name)) {
			return null;
		} else {
			return getShareLine(beginTime, endTime, nameMap.get(name));
		}
	}

}
