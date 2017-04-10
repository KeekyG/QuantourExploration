package data.dao;

import po.InterestPO;

public interface InterestDao {

	/**
	 * 用来获取一定时间内的一年定期利率
	 * 
	 * @param 开始年份，四个字符
	 * @param 结束年份，四个字符
	 * @return 开始年份和结束年份之间的利率
	 */
	public InterestPO getInterest(String beginYear, String endYear);
}
