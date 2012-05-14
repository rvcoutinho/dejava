package org.dejava.component.util.test.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.dejava.component.util.test.model.MultiDataTestSourceType;

/**
 * Multiple data test. This annotation tells the JUnit (with the proper Runner) to run the annotated test
 * multiple times by injecting each of the items in the Iterable returned by the test data source.
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface MultiDataTest {
	
	/**
	 * The source type for the multiple data test.
	 */
	MultiDataTestSourceType sourceType() default MultiDataTestSourceType.XML;
	
	/**
	 * Method name expression to be evaluated later.
	 */
	public static final String METHOD_NAME_EXPRESSION = "#{annotated_method_name}";
	
	/**
	 * Default path for the XML.
	 */
	public static final String DEFAULT_XML_PATH = "xml/" + METHOD_NAME_EXPRESSION + ".xml";
	
	/**
	 * XML path for the test data objects.
	 * 
	 * Will be used if the source type is {@link MultiDataTestSourceType#XML}.
	 * 
	 * Default is "xml/#{annotated method name}.xml".
	 */
	String xmlPath() default "";
	
	/**
	 * JNDI path for the JNDI object that contains the method for the test data objects.
	 * 
	 * Will be used if the source type is {@link MultiDataTestSourceType#JNDI}.
	 */
	String jndiObjectPath() default "";
	
	/**
	 * Default name for the test data objects method.
	 */
	public static final String DEFAULT_METHOD_NAME = METHOD_NAME_EXPRESSION + "Data";
	
	/**
	 * Method name (with no args) for the test data objects.
	 * 
	 * Will be used if the source type is {@link MultiDataTestSourceType#METHOD} or
	 * {@link MultiDataTestSourceType#JNDI}.
	 * 
	 * If the type is {@link MultiDataTestSourceType#METHOD}, the method must be within the test class.
	 * 
	 * Otherwise, the method will be called from within the JNDI object.
	 * 
	 * Default is "#{annotated_method_name}Data".
	 */
	String methodName() default "";
}
