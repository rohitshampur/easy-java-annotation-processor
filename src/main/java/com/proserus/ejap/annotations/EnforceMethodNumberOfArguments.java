package com.proserus.ejap.annotations;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.ANNOTATION_TYPE)
public @interface EnforceMethodNumberOfArguments {

	int[] value();
}
