Annotate your own annotation and benefits from this easy annotation processing at compile-time.

No need to write a Java Annotation Processor, just use this library.

# Latest release: [easy-java-annotation-processor-0.3.jar](http://bit.ly/easy-java-annotation-processor-03) #

  1. Include the dependency in your pom (same project as your custom annotations):
```
<dependency>
  <groupId>com.proserus.ejap</groupId>
  <artifactId>easy-java-annotation-processor</artifactId>
  <version>0.3</version>
</dependency>
```
  1. Declare the Easy Processor in your pom (same project as your annotated objects):
```
<plugin>
  <artifactId>maven-compiler-plugin</artifactId>
  <version>3.1</version>
  <configuration>
    <annotationProcessors>
      <annotationProcessor>
        com.proserus.ejap.EjapMainProcessor
      </annotationProcessor>
    </annotationProcessors>
  </configuration>
  <dependencies>
    <dependency>
      <groupId>com.proserus.ejap</groupId>
      <artifactId>easy-java-annotation-processor</artifactId>
      <version>0.3</version>
    </dependency>
  </dependencies>
</plugin>
```
  1. Define your own annotation and annotate it with one of the following depending on your desired constraints:
```
EnforceElementForbiddenModifiers
EnforceElementHasNoArgConstructor
EnforceElementModifiers
EnforceMethodArgumentTypes
EnforceMethodConstructor
EnforceMethodGetter
EnforceMethodNumberOfArguments
EnforceMethodSetter
```

# Exemples #
If your annotation must only be used on getter method, use `@EnforceMethodGetter`:
```
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
@EnforceMethodGetter
public @interface CustomAnnotation {
	boolean customParameter() default false;
}
```

If your annotation must only be used on public method/field/type, use `@EnforceElementModifiers`:
```
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.METHOD, ElementType.FIELD,ElementType.TYPE})
@EnforceElementModifiers(EjapModifier.PUBLIC)
public @interface CustomAnnotation {
	boolean customParameter() default false;
}
```

If your annotation must only be used on methods with one parameter, which must also have a no-arg constructor:
```
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
@EnforceMethodNumberOfArguments(1)
@EnforceElementHasNoArgConstructor
public @interface CustomAnnotation {
	boolean customParameter() default false;
}
```

If your annotation must only be used on methods with parameters of specific types and NOT protected, private, abstract or static:
```
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
@EnforceMethodArgumentTypes({Comparator.class, int.class, String.class})
@EnforceElementForbiddenModifiers({EjapModifier.PROTECTED, EjapModifier.PRIVATE,
                                   EjapModifier.ABSTRACT, EjapModifier.STATIC})
public @interface CustomAnnotation {
	boolean customParameter() default false;
}
```

public setter:
```
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
@EnforceMethodGetter
@EnforceElementModifiers(EjapModifier.PUBLIC)
public @interface CustomAnnotation {
	boolean customParameter() default false;
}
```

Protected Constructor method:
```
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
@EnforceMethodConstructor
@EnforceElementModifiers(EjapModifier.PROTECTED)
public @interface CustomAnnotation {
	boolean customParameter() default false;
}
```


# Previous releases #
[here](https://drive.google.com/folderview?id=0B5XbviLoYz2FbDNVeE1qRFRQNWM&usp=sharing)