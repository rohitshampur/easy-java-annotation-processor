package com.proserus.ejap.processors;

import java.util.Arrays;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.tools.Diagnostic;

import com.proserus.ejap.annotations.EnforceMethodNumberOfArguments;
import com.proserus.ejap.processors.utils.ProcessorUtils;

public class EnforceMethodNumberOfArgumentsProcessor implements EjapConditionalProcessorIF {

	public void validate(Element userAnnotation, Element userTypeMethodField, ProcessingEnvironment processingEnv) {
		boolean isBad = true;
		EnforceMethodNumberOfArguments argumentNumber = (EnforceMethodNumberOfArguments) userAnnotation.getAnnotation(EnforceMethodNumberOfArguments.class);

		if (userTypeMethodField instanceof ExecutableElement) {
			for (int val : argumentNumber.value()) {
				if (((ExecutableElement) userTypeMethodField).getParameters().size() == val) {
					isBad = false;
				}
			}
		}

		if (isBad) {
			processingEnv.getMessager().printMessage(
					Diagnostic.Kind.ERROR,
					"Annotated element must be a method with exactly " + Arrays.toString(argumentNumber.value()) + " arguments: "
							+ ProcessorUtils.retrieveSource(userTypeMethodField),userTypeMethodField);
		}
	}

}
