package com.test.freemarker;

import java.util.List;

import org.springframework.stereotype.Component;

import freemarker.template.SimpleNumber;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

@Component("indexOfMethod")
public class IndexOfMethod implements TemplateMethodModel{

	@Override
	public Object exec(List args) throws TemplateModelException {
		if(args.size() != 2)
			throw new TemplateModelException("Wrong Arguments");
		return new SimpleNumber(((String) args.get(1)).indexOf((String) args.get(0)));
	}
}
