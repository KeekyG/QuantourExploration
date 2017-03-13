package blService;

import java.util.ArrayList;
import java.util.Date;

import vo.ShareLineVO;

public interface StockBlService {
	
	/**
	 * 获取用户可能输入的股票代号
	 * @param head 用户已输入的数字
	 * @return 所有用户可能的输入
	 */
	public ArrayList<String> getPossibleCodes(String head);
	
	/**
	 * 获取用户可能输入的股票名
	 * @param head 用户已输入的字符
	 * @return 所有用户可能的输入
	 */
	public ArrayList<String> getPossibleNames(String head);
	
	/**
	 * 获取一段时间中某一股票的交易情况
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @param code 股票代号
	 * @return
	 */
	public ShareLineVO getShareLine(Date beginTime, Date endTime, String code);
	
	/**
	 * 获取一段时间中某一股票的交易情况
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @param code 股票名
	 * @return
	 */
	public ShareLineVO getShareLineByName(Date beginTime, Date endTime, String name);
	
}
