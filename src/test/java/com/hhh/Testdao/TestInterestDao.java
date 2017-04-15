package com.hhh.Testdao;

import static org.junit.Assert.*;

import org.junit.Test;

import data.dao.InterestDao;
import data.dao.impl.InterestDaoImpl;
import po.InterestPO;

public class TestInterestDao {

	@Test
	public void test() {
		InterestDaoImpl interestDao = new InterestDaoImpl();
		InterestPO interestPO = interestDao.getInterest("2010", "2011");
		System.out.println(interestPO.getInterests());
		fail("Not yet implemented");
	}

}
