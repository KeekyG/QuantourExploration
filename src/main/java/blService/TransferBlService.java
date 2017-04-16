package blService;

import java.util.ArrayList;
import java.util.Date;

import po.StockPoolPO;
import po.StockUpDownPO;
import vo.DailyStockVO;
import vo.SelfSelectStockPoolVO;
import vo.ShareLineVO;
import vo.StockShareVO;

public interface TransferBlService {

	public DailyStockVO toDailyStockVO(StockUpDownPO po);
	
	public StockShareVO toStockShareVO(StockUpDownPO po);
	
	public ShareLineVO toShareLineVO(ArrayList<StockShareVO> shareVOs, Date beginDate, Date endDate, String code, String name, StockShareVO yesterdayShare);
	
	public SelfSelectStockPoolVO toSelfSelectStockPoolVO(StockPoolPO stockPoolPO);
}
