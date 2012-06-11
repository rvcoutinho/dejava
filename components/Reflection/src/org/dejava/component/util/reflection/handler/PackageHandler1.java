package org.dejava.component.util.reflection.handler;

import org.dejava.component.util.exception.localized.unchecked.EmptyParameterException;

/**
 * Helps handling reflection with classes.
 */
public final class PackageHandler1 {
	
	/**
	 * Private constructor.
	 */
	private PackageHandler1() {
	}
	
	/**
	 * Gets the class package as a directory path.
	 * 
	 * @param clazz
	 *            Class to get the directory path.
	 * @return The class package as a directory path.
	 * @throws EmptyParameterException
	 *             If the passed class is null.
	 */
	public static String getPackageAsDirPath(final Class<?> clazz) throws EmptyParameterException {
		// If the passed class is null.
		if (clazz == null) {
			// Throws an exception.
			throw new EmptyParameterException("class"); // TODO
		}
		// Gets the directory path for the class.
		String dirPath = clazz.getPackage().toString().replace(".", "/");
		// Removes the "package " from the beginning.
		dirPath = dirPath.substring(8);
		// Returns the directory path.
		return dirPath;
	}
}
