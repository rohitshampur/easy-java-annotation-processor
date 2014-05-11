package com.proserus.ejap.processors.utils;

import javax.lang.model.element.Element;

public class ProcessorUtils {

	public static String retrieveSource(Element userTypeMethodField) {
		String  source = userTypeMethodField.getSimpleName().toString();
		
		if(userTypeMethodField.getEnclosingElement()!=null){
			source = userTypeMethodField.getEnclosingElement().toString() +"."+source;
		}
		
		return source;
	}
}
