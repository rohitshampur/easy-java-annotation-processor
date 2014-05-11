package com.proserus.ejap;

import java.lang.annotation.Annotation;

import com.proserus.ejap.annotations.EnforceElementForbiddenModifiers;
import com.proserus.ejap.annotations.EnforceElementHasNoArgsConstructor;
import com.proserus.ejap.annotations.EnforceElementModifiers;
import com.proserus.ejap.annotations.EnforceMethodArgumentTypes;
import com.proserus.ejap.annotations.EnforceMethodConstructor;
import com.proserus.ejap.annotations.EnforceMethodGetter;
import com.proserus.ejap.annotations.EnforceMethodNumberOfArguments;
import com.proserus.ejap.annotations.EnforceMethodReturnTypes;
import com.proserus.ejap.annotations.EnforceMethodSetter;
import com.proserus.ejap.processors.EjapConditionalProcessorIF;
import com.proserus.ejap.processors.EnforceElementForbiddenModifiersProcessor;
import com.proserus.ejap.processors.EnforceElementHasNoArgsConstructorProcessor;
import com.proserus.ejap.processors.EnforceElementModifiersProcessor;
import com.proserus.ejap.processors.EnforceMethodArgumentTypeProcessor;
import com.proserus.ejap.processors.EnforceMethodConstructorProcessor;
import com.proserus.ejap.processors.EnforceMethodGetterProcessor;
import com.proserus.ejap.processors.EnforceMethodNumberOfArgumentsProcessor;
import com.proserus.ejap.processors.EnforceMethodReturnTypesProcessor;
import com.proserus.ejap.processors.EnforceMethodSetterProcessor;

public enum EjapConditionalProcessorsEnum {
	ALLOWED_MODIFIER(EnforceElementModifiers.class, new EnforceElementModifiersProcessor()),
	FORBIDDEN_MODIFIER(EnforceElementForbiddenModifiers.class, new EnforceElementForbiddenModifiersProcessor()),
	GETTER(EnforceMethodGetter.class, new EnforceMethodGetterProcessor()),
	SETTER(EnforceMethodSetter.class, new EnforceMethodSetterProcessor()),
	NUMBER_OF_ARGUMENTS(EnforceMethodNumberOfArguments.class, new EnforceMethodNumberOfArgumentsProcessor()),
	CONSTRUCTOR(EnforceMethodConstructor.class, new EnforceMethodConstructorProcessor()),
	RETURN_TYPE(EnforceMethodReturnTypes.class, new EnforceMethodReturnTypesProcessor()),
	PARAM_TYPES(EnforceMethodArgumentTypes.class, new EnforceMethodArgumentTypeProcessor()),
	NO_ARGS_CONSTRUCTOR(EnforceElementHasNoArgsConstructor.class, new EnforceElementHasNoArgsConstructorProcessor());	

	private EjapConditionalProcessorIF annotationProcessor;
	private Class<? extends Annotation> annotationClass;
	
	private EjapConditionalProcessorsEnum(Class<? extends Annotation> annotationClass, EjapConditionalProcessorIF annotationCheck) {
		this.annotationClass = annotationClass;
		this.annotationProcessor = annotationCheck;
	}
	
	public EjapConditionalProcessorIF getAnnotationProcessor() {
		return annotationProcessor;
	}

	public Class<? extends Annotation> getAnnotationClass() {
		return annotationClass;
	}
}
