package com.test.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.test.domain.Item;

@Repository
public interface SQLMapper {

	public List<Item> getAllItems();
	public Item getSingleItem(int id);
	public void save(Item item);
	public void saveChange(Item item);
	public void delete(int id);
}
