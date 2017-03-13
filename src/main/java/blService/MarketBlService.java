package blService;

import java.util.Date;

import vo.ThermometerVO;

public interface MarketBlService {

	/**
	 * 根据日期得到当日的市场温度计信息
	 * @param date 日期
	 * @return 当日市场温度计
	 */
	public ThermometerVO getMarketThermo(Date date);
	
}
