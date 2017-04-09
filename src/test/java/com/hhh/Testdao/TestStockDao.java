package com.hhh.Testdao;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import data.dao.StockDao;
import data.dao.impl.StockDaoImpl;
import po.StockUpDownPO;

public class TestStockDao {

	@Test
	public void test() {
		StockDao stockDao = new StockDaoImpl();
		Date date = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yy/dd");
		try {
			date = simpleDateFormat.parse("02/05/02");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<StockUpDownPO> stockUpDownPOs = stockDao.getDailyStock(date);
		StockUpDownPO stockUpDownPO = stockUpDownPOs.get(0);
		assertEquals(date, stockUpDownPO.getDate());
	}

}
