package org.dejava.component.test.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.dejava.component.test.runner.JUnitParametricRunner;

/**
 * Defines testing groups in a test class. Must be used with proper JUnit runner (as for
 * {@link JUnitParametricRunner})
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.TYPE, ElementType.METHOD })
public @interface GroupTest {
	
	/**
	 * If annotating a type: defines the groups of tests that should be considered while running the test
	 * class. If empty (or if the annotation is not present for the class), all groups must be tested.
	 * 
	 * If annotating a test method: defines the groups that the test method is part of. If empty, the test
	 * method will only be invoked if the all groups must be invoked (see definition above).
	 */
	String[] groups() default {};
}
