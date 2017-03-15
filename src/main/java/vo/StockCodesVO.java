package vo;

import java.util.ArrayList;
import java.util.HashMap;

import data.dao.StockNameDao;
import data.dao.impl.StockNameDaoImpl;

public class StockCodesVO {
	
	private ArrayList<String> codeList;
	
	private HashMap<String, String> nameMap;
	
	private static StockCodesVO instance;
	
	private StockCodesVO() {
		StockNameDao dao = new StockNameDaoImpl();
		codeList = dao.getStockCode();
		nameMap = dao.getStockName();
	}
	
	public static StockCodesVO getInstance() {
		if (instance == null) {
			instance = new StockCodesVO();
		}
		return instance;
	}

	public ArrayList<String> getCodeList() {
		return codeList;
	}

	public HashMap<String, String> getNameMap() {
		return nameMap;
	}
}
