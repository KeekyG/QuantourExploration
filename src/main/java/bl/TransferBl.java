package bl;

import java.util.ArrayList;
import java.util.Date;

import blService.TransferBlService;
import po.StockUpDownPO;
import vo.DailyStockVO;
import vo.ShareLineVO;
import vo.StockShareVO;

public class TransferBl implements TransferBlService {

	@Override
	public DailyStockVO toDailyStockVO(StockUpDownPO po) {
		return new DailyStockVO(po.getDate(), po.getOpen(), po.getClose(), po.getVolume(), po.getCode(), po.getName(), po.getMarket());
	}

	@Override
	public StockShareVO toStockShareVO(StockUpDownPO po) {
		return new StockShareVO(po.getDate(), po.getOpen(), po.getHigh(), po.getLow(), po.getClose(), po.getVolume(), po.getCode(), po.getName(), po.getMarket());
	}

	@Override
	public ShareLineVO toShareLineVO(ArrayList<StockShareVO> shareVOs, Date beginDate, Date endDate, String code, String name) {
		return new ShareLineVO(shareVOs, beginDate, endDate, code, name);
	}

}
