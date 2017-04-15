package vo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author ssw's daddy
 * 
 * 收益率的vo，可用来画图也可用来算奇怪的数据
 * 
 * beginDate 开始时间
 * endDate 结束时间
 *
 */
public class IncomeLineVO extends ArrayList<PointVO> {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3031169251414181259L;

	private LocalDate beginDate;
	
	private LocalDate endDate;
	
	public IncomeLineVO(HashMap<LocalDate, Double> incomeMap) {
		beginDate = LocalDate.of(2018, 1, 1);
		endDate = LocalDate.of(2000, 1, 1);
		Iterator<LocalDate> iterator = incomeMap.keySet().iterator();
		while (iterator.hasNext()) {
			LocalDate localDate = (LocalDate) iterator.next();
			this.add(new PointVO(localDate, incomeMap.get(localDate)));
			if (localDate.isBefore(beginDate)) {
				beginDate = localDate;
			}
			if (localDate.isAfter(endDate)) {
				endDate = localDate;
			}
		}
	}
	
	public LocalDate getBeginDate() {
		return beginDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}
}
