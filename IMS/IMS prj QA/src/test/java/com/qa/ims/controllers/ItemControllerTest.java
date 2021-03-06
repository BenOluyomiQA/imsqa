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

import com.qa.ims.controller.ItemController;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private ItemDAO dao;

	@InjectMocks
	private ItemController controller;

	@Test
	public void testCreate() {
		final Item created = new Item("shoes", (float)18.65);

		Mockito.when(utils.getString()).thenReturn("shoes");
		Mockito.when(utils.getItemPrice()).thenReturn((float)18.65);
		Mockito.when(dao.create(created)).thenReturn(created);

		assertEquals(created, controller.create());
		
		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(utils, Mockito.times(1)).getItemPrice();
		Mockito.verify(dao, Mockito.times(1)).create(created);
	}

	@Test
	public void testReadAll() {
		List<Item> items = new ArrayList<>();
		items.add(new Item((long)1,"guitar", (float)74.99));
		items.add(new Item((long)2,"microphone", (float)89.99));
		Mockito.when(dao.readAll()).thenReturn(items);

		assertEquals(items, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}

	@Test
	public void testUpdate() {
		Item updated = new 	Item((long)1, "chocolate", (float) 8.34);

		Mockito.when(this.utils.getItemId()).thenReturn((long)1);
		Mockito.when(this.utils.getString()).thenReturn(updated.getProductName());
		Mockito.when(this.utils.getItemPrice()).thenReturn(updated.getPrice());
		Mockito.when(this.dao.update(updated)).thenReturn(updated);

		assertEquals(updated, this.controller.update());

		Mockito.verify(this.utils, Mockito.times(1)).getItemId();
		Mockito.verify(this.utils, Mockito.times(1)).getString();
		Mockito.verify(this.utils, Mockito.times(1)).getItemPrice();
		Mockito.verify(this.dao, Mockito.times(1)).update(updated);
	}

	@Test
	public void testDelete() {
		final long ID = 1;

		Mockito.when(utils.getItemId()).thenReturn(ID);
		Mockito.when(dao.delete(ID, ID)).thenReturn(1);

		assertEquals(1, this.controller.delete());

		Mockito.verify(utils, Mockito.times(1)).getItemId();
		Mockito.verify(dao, Mockito.times(1)).delete(ID, ID);
	}

}
