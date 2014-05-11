package com.proserus.ejap;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;

import org.kohsuke.MetaInfServices;

//@SupportedAnnotationTypes("com.proserus.SecondAnnotation")
@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_6)
@MetaInfServices(javax.annotation.processing.Processor.class)
public class EjapMainProcessor extends AbstractProcessor {

	public EjapMainProcessor() {
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		for (TypeElement ele : annotations) {
			if (ele.getKind().equals(ElementKind.ANNOTATION_TYPE)) {
				for (EjapConditionalProcessorsEnum c : EjapConditionalProcessorsEnum.values()) {
					if (ele.getAnnotation(c.getAnnotationClass()) != null) {
						for (Element e : roundEnv.getElementsAnnotatedWith(ele)) {
							c.getAnnotationProcessor().validate(ele, e, processingEnv);
						}
					}
				}
			}
		}
		return false;
	}
}