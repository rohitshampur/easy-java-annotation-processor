package com.proserus.ejap.processors;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.tools.Diagnostic;

import com.proserus.ejap.annotations.EnforceElementModifiers;
import com.proserus.ejap.processors.enu.EjapModifier;
import com.proserus.ejap.processors.utils.ProcessorUtils;

public class EnforceElementModifiersProcessor implements EjapConditionalProcessorIF {
	private Set<Modifier> notDefaultModifier = new HashSet<Modifier>() {
		private static final long serialVersionUID = 1L;

		{
			add(Modifier.PUBLIC);
			add(Modifier.PRIVATE);
			add(Modifier.PROTECTED);
		}
	};

	public void validate(Element userAnnotation, Element userTypeMethodField, ProcessingEnvironment processingEnv) {
		EnforceElementModifiers allowedModifiers = (EnforceElementModifiers) userAnnotation.getAnnotation(EnforceElementModifiers.class);
		Set<String> allowedMod = new HashSet<String>();
		for (EjapModifier allowedModifier : allowedModifiers.value()) {
			allowedMod.add(allowedModifier.name());
		}
		for (Modifier appliedModifier : userTypeMethodField.getModifiers()) {
			if (!allowedMod.contains(appliedModifier.name())
					|| (allowedMod.contains(EjapModifier.DEFAULT_NO_MODIFIER) && notDefaultModifier.contains(appliedModifier))) {
				processingEnv.getMessager().printMessage(
						Diagnostic.Kind.ERROR,
						"Annotation is not allowed with modifier '" + appliedModifier.name().toLowerCase() + "': "
								+ ProcessorUtils.retrieveSource(userTypeMethodField), userTypeMethodField);
			}
		}
	}

}
