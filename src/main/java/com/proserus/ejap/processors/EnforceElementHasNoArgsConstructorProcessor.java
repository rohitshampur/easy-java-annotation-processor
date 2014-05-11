package com.proserus.ejap.processors;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

import com.proserus.ejap.processors.utils.ProcessorUtils;

public class EnforceElementHasNoArgsConstructorProcessor implements EjapConditionalProcessorIF {

	public void validate(Element userAnnotation, Element userTypeMethodField, ProcessingEnvironment processingEnv) {
		boolean noArgFound = false;
		if (userTypeMethodField instanceof TypeElement) {
			for (Element method : ((TypeElement) userTypeMethodField).getEnclosedElements()) {
				if (method.getSimpleName().equals("<init>") && ((ExecutableElement) method).getTypeParameters().size() == 0) {
					noArgFound = true;
				}
			}
			if (!noArgFound) {
				processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
						"Type '" + userTypeMethodField.getSimpleName() + "' must have a no-arg constructor': " + ProcessorUtils.retrieveSource(userTypeMethodField), userTypeMethodField);
			}
		} else {
			processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
					"Annotation '" + userAnnotation.getSimpleName() + "' is only allowed at the 'type level': " + ProcessorUtils.retrieveSource(userTypeMethodField), userTypeMethodField);
		}
	}
}
