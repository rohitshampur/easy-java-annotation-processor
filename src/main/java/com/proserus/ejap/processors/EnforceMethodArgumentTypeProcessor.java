package com.proserus.ejap.processors;

import java.util.Arrays;
import java.util.List;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.MirroredTypesException;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;

import com.proserus.ejap.annotations.EnforceMethodArgumentTypes;
import com.proserus.ejap.processors.utils.ProcessorUtils;

public class EnforceMethodArgumentTypeProcessor implements EjapConditionalProcessorIF {

	public void validate(Element userAnnotation, Element userTypeMethodField, ProcessingEnvironment processingEnv) {
		EnforceMethodArgumentTypes paramType = (EnforceMethodArgumentTypes) userAnnotation.getAnnotation(EnforceMethodArgumentTypes.class);
		boolean isBad = true;
		List<? extends TypeMirror> types = null;
		try {
			Arrays.toString(paramType.value());
		} catch (MirroredTypesException e) {
			types = e.getTypeMirrors();

		}

		if (userTypeMethodField instanceof ExecutableElement) {
			for (VariableElement var : ((ExecutableElement) userTypeMethodField).getParameters()) {
				if (types.contains(var.asType())) {
					isBad = false;
					break;
				}
			}
		}

		if (isBad) {
			processingEnv.getMessager().printMessage(
					Diagnostic.Kind.ERROR,
					"Annotated (" + ProcessorUtils.retrieveSource(userTypeMethodField)
							+ ") must be a method and return one of the following types: " + types, userTypeMethodField);
		}
	}
}
