package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOTestFail {

	private final OrderDAO orDAO = new OrderDAO();

	@BeforeClass
	public static void init() {
		DBUtils.connect("root", "fail");
	}
	
	@Before
	public void setup() {
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test 
	public void testReadAll() {
		assertEquals(new ArrayList<>(), orDAO.readAll());
	}
	
}