package org.dejava.component.util.test.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.dejava.component.util.test.runner.ParametricJUnitRunner;

/**
 * Defines testing groups in a org.dejava.component.util.test.test class. Must be used with proper JUnit runner (as for
 * {@link ParametricJUnitRunner})
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.TYPE, ElementType.METHOD })
public @interface GroupTest {
	
	/**
	 * If annotating a type: defines the groups of tests that should be considered while running the org.dejava.component.util.test.test
	 * class. If empty (or if the annotation is not present for the class), all groups must be tested.
	 * 
	 * If annotating a org.dejava.component.util.test.test method: defines the groups that the org.dejava.component.util.test.test method is part of. If empty, the org.dejava.component.util.test.test
	 * method will only be invoked if the all groups must be invoked (see definition above).
	 */
	String[] groups() default {};
}
