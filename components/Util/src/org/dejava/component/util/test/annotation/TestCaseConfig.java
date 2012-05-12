package org.dejava.component.util.test.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Test case configuration.
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface TestCaseConfig {
	
	/**
	 * Method name expression for the default relative path.
	 */
	public static final String METHOD_NAME_EXPRESSION = "#{annotated_method_name}";
	
	/**
	 * Default relative path for the configuration file.
	 */
	public static final String DEFAULT_RELATIVE_PATH = "xml/" + METHOD_NAME_EXPRESSION + ".xml";
	
	/**
	 * XML relative path for the test case configuration. Default is "xml/#{annotated method name}.xml"
	 */
	String xmlRelativePath() default "";
}
