package com.hhh.TestBl;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import bl.MarketBl;
import bl.MarketThermometerBl;
import blService.MarketBlService;
import blService.MarketThermometerBlService;

public class TestMarketThermometer {

	@SuppressWarnings("deprecation")
	@Test
	public void test() {
		MarketBlService marketBlService = new MarketBl();
		Date date = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yy/dd");
		try {
			date = simpleDateFormat.parse("02/05/02");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MarketThermometerBlService marketThermometerBlService = new MarketThermometerBl();
		marketBlService.getMarketThermo(date);
		System.out.println(marketThermometerBlService.drawThermometer(date));
	}

}
