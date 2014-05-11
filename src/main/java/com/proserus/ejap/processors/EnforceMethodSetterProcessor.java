package com.proserus.ejap.processors;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.tools.Diagnostic;

import com.proserus.ejap.processors.utils.ProcessorUtils;

public class EnforceMethodSetterProcessor implements EjapConditionalProcessorIF {

	public void validate(Element userAnnotation, Element userTypeMethodField, ProcessingEnvironment processingEnv) {
		if (userTypeMethodField instanceof ExecutableElement && userTypeMethodField.getSimpleName().toString().startsWith("set")) {
		} else {
			processingEnv.getMessager().printMessage(
					Diagnostic.Kind.ERROR,
					"Annotated element must be a setter method: " + ProcessorUtils.retrieveSource(userTypeMethodField), userTypeMethodField);
		}
	}

}
