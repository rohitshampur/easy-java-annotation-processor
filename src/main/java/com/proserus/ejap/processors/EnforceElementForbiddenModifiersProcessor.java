package com.proserus.ejap.processors;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.tools.Diagnostic;

import com.proserus.ejap.annotations.EnforceElementForbiddenModifiers;
import com.proserus.ejap.processors.enu.EjapModifier;
import com.proserus.ejap.processors.utils.ProcessorUtils;

public class EnforceElementForbiddenModifiersProcessor implements EjapConditionalProcessorIF {

	public void validate(Element userAnnotation, Element userTypeMethodField, ProcessingEnvironment processingEnv) {
		EnforceElementForbiddenModifiers forbiddenModifiers = (EnforceElementForbiddenModifiers) userAnnotation.getAnnotation(EnforceElementForbiddenModifiers.class);
		Set<String> appliedMod = new HashSet<String>();
		for (Modifier modifier : userTypeMethodField.getModifiers()) {
			appliedMod.add(modifier.name());
		}

		for (EjapModifier forbiddenModifier : forbiddenModifiers.value()) {
			if (appliedMod.contains(forbiddenModifier.name())
					|| (forbiddenModifier.equals(EjapModifier.DEFAULT_NO_MODIFIER) && (appliedMod.contains(EjapModifier.PUBLIC)
							|| appliedMod.contains(EjapModifier.PRIVATE) || appliedMod.contains(EjapModifier.PROTECTED)))) {
				processingEnv.getMessager().printMessage(
						Diagnostic.Kind.ERROR,
						"Annotation is not allowed with modifier '" + forbiddenModifier.name().toLowerCase() + "': "
								+ ProcessorUtils.retrieveSource(userTypeMethodField),userTypeMethodField);
			}
		}
	}
}
