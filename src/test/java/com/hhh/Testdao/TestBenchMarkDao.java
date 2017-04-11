package com.hhh.Testdao;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.HashMap;

import org.junit.Test;

import data.dao.impl.BenchmarkDaoImpl;
import enums.PlateType;
import po.BenchmarkPO;

public class TestBenchMarkDao {

	@Test
	public void test() {
		LocalDate beginDate = LocalDate.of(2006, 3, 15);
		LocalDate endDate = LocalDate.of(2008, 4, 15);
		BenchmarkDaoImpl benchmarkDaoImpl = new BenchmarkDaoImpl();
		BenchmarkPO benchmarkPO = benchmarkDaoImpl.getBenchmark(PlateType.HS300, beginDate, endDate);
		System.out.println(benchmarkPO.getBeginDate());
		System.out.println(benchmarkPO.getEndDate());
		HashMap<LocalDate, Double> hashMap = benchmarkPO.getChanges();
		System.out.println(benchmarkPO.getChanges());
		fail("Not yet implemented");
	}

}
