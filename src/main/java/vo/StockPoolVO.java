package vo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import enums.StockPoolType;

/**
 * @author ssw's daddy
 * 
 * 股票池VO
 * 
 * type 股票池类型，分为自选和板块
 * name 股票池名称
 * capacity 股票池大小
 * beginDate 开始时间
 * endDate 结束时间
 * stocks 股票池中股票
 * dateList 股票池的日期列表
 *
 */
public class StockPoolVO extends StockSetVO {
	
	private StockPoolType type;
	
	private String name;
	
	private int capacity;
	
	private ArrayList<LocalDate> dateList;
	
	public StockPoolVO(StockPoolType type, String name, LocalDate beginDate, LocalDate endDate, ArrayList<ShareLineVO> stocks, ArrayList<LocalDate> dateList) {
		super();
		this.type = type;
		this.name = name;
		HashMap<String, ShareLineVO> stockMap = new HashMap<>();
		for (ShareLineVO shareLineVO : stocks) {
			stockMap.put(shareLineVO.getCode(), shareLineVO);
		}
		this.capacity = stockMap.size();
		this.dateList = dateList;
		super.setBeginDate(beginDate);
		super.setEndDate(endDate);
		super.setStocks(stockMap);
		
	}

	public StockPoolType getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public int getCapacity() {
		return capacity;
	}
	
	public ArrayList<LocalDate> getDateList() {
		return dateList;
	}
	
}
