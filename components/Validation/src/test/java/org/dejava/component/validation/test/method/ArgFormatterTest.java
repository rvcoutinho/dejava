package org.dejava.component.validation.test.method;

import org.dejava.component.validation.method.ArgFormatter;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for the ArgFormatter class.
 */
public final class ArgFormatterTest {

	/**
	 * Test data for the removeInvalidRegex test. The array positions are: [0] - originalArgument, [1] -
	 * invalidRegex, [2] - expected return.
	 */
	private static final String[][] RM_INVALID_REGEX_DATA = { { null, null, null }, { null, "teste", null },
			{ "teste", null, "teste" }, { "jhdjs676thsjh", "\\d", "jhdjsthsjh" },
			{ "jhd-js676-thsjh", "\\w", "--" } };

	/**
	 * Tests the method removeInvalidRegex.
	 */
	@Test
	public void testRemoveInvalidRegex() {
		// For each test data.
		for (final String[] currentTestData : RM_INVALID_REGEX_DATA) {
			// Asserts that the argument formatter works as expected.
			Assert.assertEquals(currentTestData[2],
					ArgFormatter.removeInvalidRegex(currentTestData[0], currentTestData[1]));
		}
	}

}
