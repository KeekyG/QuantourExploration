package po;

import java.time.LocalDate;
import java.util.HashMap;

import enums.PlateType;

/**
 * @author ssw's father
 * 
 * 股票基准收益的PO，用来做比较
 * 
 * type 类型，分为沪深300、中小板指、创业板指
 * changes 板块每天的涨跌幅
 *
 */
public class BenchmarkPO {
	
	private PlateType type;
	
	private LocalDate beginDate;
	
	private LocalDate endDate;
	
	private HashMap<LocalDate, Double> changes;
	
	public BenchmarkPO(PlateType type, LocalDate beginDate, LocalDate endDate, HashMap<LocalDate, Double> changes) {
		this.type = type;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.changes = changes;
	}

	public PlateType getType() {
		return type;
	}

	public LocalDate getBeginDate() {
		return beginDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public HashMap<LocalDate, Double> getChanges() {
		return changes;
	}

	
}
