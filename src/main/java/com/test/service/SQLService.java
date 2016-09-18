package com.test.service;

import java.util.HashMap;

import com.test.domain.Item;

public interface SQLService {
	HashMap<String, Item> getAllItems();
	Item getSingleItem(int id);
	void save(Item item);
	void saveChange(Item item);
	void delete(int id);
}
