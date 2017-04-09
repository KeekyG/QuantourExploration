package vo;

import java.util.ArrayList;
import java.util.HashMap;

import enums.StockPoolType;

/**
 * @author ssw's daddy
 * 
 * 股票池VO
 * 
 * type 股票池类型，分为自选和板块
 * name 股票池名称
 * stocks 股票池中股票
 *
 */
public class StockPoolVO {
	
	private StockPoolType type;
	
	private String name;
	
	private HashMap<String, ArrayList<ShareLineVO>> stocks;
	
	public StockPoolVO(StockPoolType type, String name, ArrayList<ShareLineVO> stocks) {
		this.type = type;
		this.name = name;
		HashMap<String, ArrayList<ShareLineVO>> stockMap = new HashMap<>();
		for (ShareLineVO shareLineVO : stocks) {
			stockMap.put(shareLineVO.getCode(), stocks);
		}
	}

	public StockPoolType getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public HashMap<String, ArrayList<ShareLineVO>> getStocks() {
		return stocks;
	}
	
	
}
