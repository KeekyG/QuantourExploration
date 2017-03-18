package com.hhh.TestBl;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import bl.StockBl;
import blService.StockBlService;
import vo.ShareLineVO;

public class TestStockBl {

	@Test
	public void test() {
		StockBlService stockBlService = new StockBl();
		Date beginTime = null;
		Date endTime = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yy");
		try {
			beginTime = simpleDateFormat.parse("02/02/05");
			endTime = simpleDateFormat.parse("02/20/05");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ShareLineVO shareLineVO = stockBlService.getShareLineByName(beginTime, endTime,"深发展A");
		assertEquals("深发展A", shareLineVO.getYesterdayShare().getName());
		assertEquals(beginTime, shareLineVO.getBeginDate());
	}

}
