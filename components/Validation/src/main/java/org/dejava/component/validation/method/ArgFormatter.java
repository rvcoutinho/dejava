package org.dejava.component.validation.method;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Provides some argument formatter methods. Can be used as some kind of pre validation.
 */
public final class ArgFormatter {

	/**
	 * Removes the occurrences of some invalid regular expression from an original argument.
	 * 
	 * @param originalArgument
	 *            The original argument value.
	 * @param invalidRegex
	 *            The regular expression to be removed from the original argument value.
	 * @return The original argument value without the occurrences of the given regular expression.
	 */
	public static String removeInvalidRegex(final String originalArgument, final String invalidRegex) {
		// Makes a copy of the original argument value.
		String formattedArgument = originalArgument;
		// If the argument and the regular expression are not null.
		if ((formattedArgument != null) && (invalidRegex != null)) {
			// Replaces all occurrences of the given regular expression with an empty string.
			formattedArgument = formattedArgument.replaceAll(invalidRegex, "");
		}
		// Returns the formatted argument.
		return formattedArgument;
	}

	/**
	 * The split regular expression token.
	 */
	private static final String SPLIT_TOKEN = ",";

	/**
	 * Splits a string around matches of the "," token.
	 * 
	 * @param initialList
	 *            The initial string with the list content.
	 * @return The string split around matches of the "," token.
	 */
	public static Collection<String> split(final String initialList) {
		// Creates the final list.
		final Collection<String> finalList = new ArrayList<>();
		// If the given list is not null.
		if (initialList != null) {
			// For each item in the list (between each token).
			for (String currentItem : initialList.split(SPLIT_TOKEN)) {
				// If the current item is not null.
				if (currentItem != null) {
					// At first, trim the current item.
					currentItem = currentItem.trim();
					// If the current item is not empty.
					if (!currentItem.isEmpty()) {
						// Adds the current item to the final list.
						finalList.add(currentItem);
					}
				}
			}
		}
		// Returns the new list.
		return finalList;
	}

	/**
	 * Private constructor.
	 */
	private ArgFormatter() {
		super();
	}
}
