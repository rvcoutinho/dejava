package org.dejava.component.validation.method;

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
	 * Private constructor.
	 */
	private ArgFormatter() {
		super();
	}
}
