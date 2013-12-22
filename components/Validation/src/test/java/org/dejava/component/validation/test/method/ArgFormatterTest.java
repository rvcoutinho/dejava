package org.dejava.component.validation.test.method;

import java.util.Arrays;
import java.util.Collection;

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
			{ "jhdjs676thsjh", "[^\\d]", "676" }, { "jhd-js676-thsjh", "\\w", "--" } };

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

	/**
	 * Test data for the split test. The array positions are: [0] - originalArgument, [1] - token, [2] -
	 * expected return (as array).
	 */
	private static final Object[][] SPLIT_DATA = { { null, new String[] {} },
			{ "teste", new String[] { "teste" } }, { "teste ", new String[] { "teste" } },
			{ "teste,    teste", new String[] { "teste", "teste" } },
			{ "teste,  safg,gsdgfsd,sg", new String[] { "teste", "safg", "gsdgfsd", "sg" } } };

	/**
	 * Tests the method split.
	 */
	@Test
	public void testSplit() {
		// For each test data.
		for (final Object[] currentTestData : SPLIT_DATA) {
			// Splits current string.
			final Collection<String> finalList = ArgFormatter.split((String) currentTestData[0]);
			// Asserts that the final list contains all expected items.
			Assert.assertTrue(finalList.containsAll(Arrays.asList((String[]) currentTestData[1])));
			// Asserts that the expected items contain all the final list items.
			Assert.assertTrue(Arrays.asList((String[]) currentTestData[1]).containsAll(finalList));
		}
	}
}
