package com.hhh.QuantourExploration;

import java.util.ArrayList;

import data.dao.impl.StockNameDaoImpl;

public class Testdao {
	public static void main(String args[]){
		StockNameDaoImpl stockNameDaoImpl = new StockNameDaoImpl();
		ArrayList<String> tmPos = stockNameDaoImpl.getStockCode();
		for(String stock: tmPos){
			System.out.println(stock);
		}
	}
}
