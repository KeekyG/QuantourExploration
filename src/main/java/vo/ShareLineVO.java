package vo;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author ssw's daddy
 * 
 * 储存两日期之间股票的价格信息， 构造方法传入arraylist并自动按日期排序
 * 
 * beginDate	开始日期
 * endDate	结束日期
 * code	股票代码
 * name	股票名
 * yesterdayShare 前一个交易日的股票数据
 *
 */
public class ShareLineVO extends ArrayList<StockShareVO> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2211010709935873030L;

	private Date beginDate;
	
	private Date endDate;
	
	private String code;
	
	private String name;
	
	private StockShareVO yesterdayShare;
	
	public ShareLineVO(ArrayList<StockShareVO> shares, Date beginDate, Date endDate, String code, String name, StockShareVO yesterdayShare) {
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.code = code;
		this.name = name;
		this.yesterdayShare = yesterdayShare;
		this.addAll(shares);
		this.sort((a, b) -> b.getDate().before(a.getDate()) ? 1 : -1);
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public StockShareVO getYesterdayShare() {
		return yesterdayShare;
	}

}
