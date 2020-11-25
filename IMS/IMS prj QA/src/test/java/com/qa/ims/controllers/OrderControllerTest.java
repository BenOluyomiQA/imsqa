package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private OrderDAO dao;

	@InjectMocks
	private OrderController controller;

	@Test
	public void testCreate() {
		final Long order_id =(long) 1;
		final Long customer_id =(long) 1;
		final Order created = new Order(order_id, customer_id);

		Mockito.when(utils.getOrderId()).thenReturn(order_id);
		Mockito.when(utils.getLong()).thenReturn(customer_id);
		Mockito.when(dao.create(created)).thenReturn(created);

		assertEquals(created, controller.create());

		Mockito.verify(utils, Mockito.times(2)).getOrderId();
		Mockito.verify(utils, Mockito.times(2)).getLong();
		Mockito.verify(dao, Mockito.times(1)).create(created);
	}

	@Test
	public void testReadAll() {
		List<Order> orders = new ArrayList<>();
		orders.add(new Order((long)1, (long)1));

		Mockito.when(dao.readAll()).thenReturn(orders);

		assertEquals(orders, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}

	@Test
	public void testUpdate() {
		Order updated = new Order((long)2,(long)2);

		Mockito.when(this.utils.getLong()).thenReturn((long) 1,(long)1);
		Mockito.when(this.utils.getLong()).thenReturn(updated.getOrderId(),updated.getOrderId());
		Mockito.when(this.dao.update(updated)).thenReturn(updated);

		assertEquals(updated, this.controller.update());

		Mockito.verify(this.utils, Mockito.times(1)).getOrderId();
		Mockito.verify(this.utils, Mockito.times(1)).getLong();
		Mockito.verify(this.dao, Mockito.times(1)).update(updated);
	}

	@Test
	public void testDelete() {
		final long order_id = 1;
		

		Mockito.when(utils.getOrderId()).thenReturn(order_id);
		Mockito.when(dao.delete(order_id, order_id)).thenReturn(1);

		assertEquals(1, this.controller.delete());

		Mockito.verify(utils, Mockito.times(1)).getOrderId();
		Mockito.verify(dao, Mockito.times(1)).delete(order_id, order_id);
	}

}
