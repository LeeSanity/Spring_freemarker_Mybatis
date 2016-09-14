package com.test.freemarker;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import com.test.domain.Item;

import freemarker.template.SimpleHash;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

@Component("hashKeyConvert")
public class HashKeyConvert implements TemplateMethodModelEx {

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String,Item> exec(@SuppressWarnings("rawtypes") List arguments) throws TemplateModelException {
		
		SimpleHash sh = (SimpleHash) arguments.get(0);
		HashMap<Integer,Item> items = (HashMap<Integer, Item>) sh.toMap();
		HashMap<String,Item> itemResult = new HashMap<String,Item>();
		
		Iterator<Entry<Integer, Item>> entries = items.entrySet().iterator();
		while (entries.hasNext()) {
		    Entry<Integer, Item> entry = entries.next();
		    itemResult.put(String.valueOf(entry.getKey()), entry.getValue());
//		    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		}
		
//		TemplateScalarModel tsm = (TemplateScalarModel) arguments.get(1);
//		String test = tsm.getAsString();
//		System.out.println(test);	
//		HashMap<String,Item> items = (HashMap<String,Item>)DeepUnwrap.unwrap(arguments[0]);
		return itemResult;
	}

}
