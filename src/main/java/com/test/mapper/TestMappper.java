package com.test.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.test.domain.Item;

@Repository
public interface TestMappper {

	public List<Item> getAllItems();
}
