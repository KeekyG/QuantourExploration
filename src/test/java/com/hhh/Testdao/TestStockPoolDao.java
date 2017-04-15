package com.hhh.Testdao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import data.dao.impl.StockPoolDaoImpl;
import po.StockPoolPO;

public class TestStockPoolDao {

	@Test
	public void test() {
		HashMap<String, String> stocks = new HashMap<>();
		stocks.put("String", "String");
		
		StockPoolPO stockPoolPO = new StockPoolPO("name", stocks);
		StockPoolDaoImpl stockPoolDaoImpl = new StockPoolDaoImpl();
		assertEquals(true, stockPoolDaoImpl.setStockPool(stockPoolPO));
		assertEquals("name", stockPoolDaoImpl.getStockPool("name").getName());
		assertEquals("String", stockPoolDaoImpl.getStockPool("name").getStocks().get("String"));
		assertEquals(null, stockPoolDaoImpl.getStockPool("name"));
		ArrayList<String> arrayList = stockPoolDaoImpl.getStockPoolNames();
		System.out.println(arrayList.get(0));
		String temp = arrayList.get(0);
		assertEquals(true, stockPoolDaoImpl.deleteStockPool(temp));
	}

}
