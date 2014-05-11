package com.proserus.ejap.processors;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;

public interface EjapConditionalProcessorIF {
	void validate(Element userAnnotation, Element userTypeMethodField, ProcessingEnvironment processingEnv);
}
