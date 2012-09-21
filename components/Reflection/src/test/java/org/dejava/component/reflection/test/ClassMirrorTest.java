package org.dejava.component.reflection.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.dejava.component.reflection.ClassMirror;
import org.dejava.component.test.annotation.ParametricTest;
import org.dejava.component.test.runner.JUnitParametricRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests for the class mirror.
 */
@RunWith(JUnitParametricRunner.class)
public class ClassMirrorTest {

	/**
	 * Valid classes names to be used in the tests.
	 */
	private static final String[] VALID_CLASSES_NAMES = { "java.lang.Object", "java.util.ArrayList",
			"org.junit.Assert", "org.dejava.component.reflection.ClassMirror" };

	/**
	 * Gets the parameters to be used in the testGetClassByClass method.
	 * 
	 * @return The parameters to be used in the testGetClassByClass method.
	 */
	public static Collection<String> getValidClassesNames() {
		// Returns some valid classes names.
		return new ArrayList<>(Arrays.asList(VALID_CLASSES_NAMES));
	}

	/**
	 * Tests the getClass method with valid classes names.
	 * 
	 * @param className
	 *            Class name to me used in the test.
	 */
	@ParametricTest(paramsValues = { "getValidClassesNames" })
	public void testGetClassByClassName(final String className) {
		// Tries to get the class via ClassMirror.
		final Class<?> clazz = ClassMirror.getClass(className, null, true);
		// Assert that the names match.
		Assert.assertEquals(className, clazz.getName());
	}

	/**
	 * Tests the getClass method with valid classes names and class loaders. TODO
	 */
	public void testGetClassByClassNameAndClassLoader() {
	}

	/**
	 * Gets the stack trace as a map: class names by depth.
	 * 
	 * @return The stack trace as a map: class names by depth.
	 */
	public static Map<Integer, String> getStackTraceByDepth() {
		// Creates a new hash map for the stack entries (by their depths).
		final HashMap<Integer, String> stackByDepth = new HashMap<>();
		// Gets the stack trace.
		final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		// For each entry in the stack trace (skipping the first two - this method and
		// Thread.getStackTrace()).
		for (Integer currentDepth = 0; currentDepth < (stackTrace.length - 2); currentDepth++) {
			// Adds the class in the entry (skipping the first) with the current depth.
			stackByDepth.put(currentDepth, stackTrace[currentDepth + 2].getClassName());
		}
		// Returns the map.
		return stackByDepth;
	}

	/**
	 * Tests the getCurrentClassName method with valid depths.
	 **/
	@Test
	public void testGetCurrentClassName() {
		// For each entry in the stack trace.
		for (final Entry<Integer, String> stackTraceEntry : getStackTraceByDepth().entrySet()) {
			// Tries to get the class for the given depth.
			final String currentClassName = ClassMirror.getCurrentClassName(stackTraceEntry.getKey());
			// Assert that the class name is correct.
			Assert.assertEquals(stackTraceEntry.getValue(), currentClassName);
		}
	}

	/**
	 * Tests the getCurrentClass method with valid depths.
	 **/
	@Test
	public void testGetCurrentClass() {
		// For each entry in the stack trace.
		for (final Entry<Integer, String> stackTraceEntry : getStackTraceByDepth().entrySet()) {
			// Tries to get the class for the given depth.
			final Class<?> currentClass = ClassMirror.getCurrentClass(stackTraceEntry.getKey());
			// Assert that the class name is correct.
			Assert.assertEquals(stackTraceEntry.getValue(), currentClass.getName());
		}
	}

}
