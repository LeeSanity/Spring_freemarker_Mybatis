package com.test.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.domain.Item;
import com.test.mapper.SQLMapper;

@Service
public class ServiceImp implements SQLService {
	@Autowired
	private SQLMapper testMappper;
	
	@Autowired
	SqlSession session;
	
	@Override
	public HashMap<String, Item> getAllItems() {
		List<Item> selectList = session.selectList("com.test.mapper.SQLMapper.getAllItems");
		HashMap<String, Item> items = new HashMap<String, Item>();
		int len = selectList.size();
		for (int i = 0; i < len; i++) {
			Item item = selectList.get(i);
			items.put(String.valueOf(item.getId()), item);
		}
		return items;
	}

	@Override
	public Item getSingleItem(int id) {
		Item singleItem = session.selectOne("com.test.mapper.SQLMapper.getSingleItem", id);
		return singleItem;
	}

	@Override
	public void save(Item item) {
		session.insert("com.test.mapper.SQLMapper.save", item);
	}

	@Override
	public void saveChange(Item item) {
		session.update("com.test.mapper.SQLMapper.saveChange",item);
	}

	@Override
	public void delete(int id) {
		session.update("com.test.mapper.SQLMapper.delete",id);
	}

}
