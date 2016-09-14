package com.test.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.domain.Item;
import com.test.mapper.TestMappper;

@Service
public class ServiceImp implements TestService {
	@Autowired
	private TestMappper testMappper;
	
	@Autowired
	SqlSession session;
	
	@SuppressWarnings("null")
	@Override
	public HashMap<String, Item> getAllItems() {
		List<Item> selectList = session.selectList("com.test.mapper.TestMapper.getAllItems");
		HashMap<String, Item> items = new HashMap<String, Item>();
		int len = selectList.size();
		for (int i = 0; i < len; i++) {
			Item item = selectList.get(i);
			items.put(String.valueOf(item.getId()), item);
		}
		return items;
	}

}
