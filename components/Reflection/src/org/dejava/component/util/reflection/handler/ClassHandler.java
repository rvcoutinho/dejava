package org.dejava.component.util.reflection.handler;

import java.util.LinkedHashSet;
import java.util.Set;

import org.dejava.component.util.exception.localized.EmptyParameterException;
import org.dejava.component.util.exception.localized.InvalidParameterException;
import org.dejava.component.util.reflection.constant.ErrorKeys;

/**
 * Helps handling reflection with classes.
 */
public final class ClassHandler {
	
	/**
	 * Private constructor.
	 */
	private ClassHandler() {
	}
	
	/**
	 * Gets the passed class and all its inherited classes and interfaces (no order granted).
	 * 
	 * @param clazz
	 *            Class from which the classes are to be extracted.
	 * @return The passed class and all its inherited classes and interfaces (no order granted).
	 * @throws EmptyParameterException
	 *             If the passed class is null.
	 */
	public static Set<Class<?>> getSuperclasses(final Class<?> clazz) throws EmptyParameterException {
		// If the passed class is null.
		if (clazz == null) {
			// Throws an exception.
			throw new EmptyParameterException("class"); // TODO
		}
		// All classes to be returned.
		final Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
		// Adds the class itself to the list.
		classes.add(clazz);
		// If the class has any superclass.
		if (clazz.getSuperclass() != null) {
			// Calls the same method recursively with its superclass (adding the
			// classes to the list).
			classes.addAll(getSuperclasses(clazz.getSuperclass()));
		}
		// If the class has any interfaces.
		if (clazz.getInterfaces() != null) {
			// For each interface.
			for (Class<?> interfaceAtual : clazz.getInterfaces()) {
				// Calls the same method recursively with the current interface
				// (adding the classes to the list).
				classes.addAll(getSuperclasses(interfaceAtual));
			}
		}
		// Adds the Object class.
		classes.add(Object.class);
		// Returns all found classes.
		return classes;
	}
	
	/**
	 * Gets the name of the current class (with the selected depth) being executed.
	 * 
	 * @param depth
	 *            Depth in the current stack for the desired class (0 is the first and so on).
	 * @return The name of the current class (with the selected depth) being executed.
	 */
	public static String getCurrentClassName(final Integer depth) {
		// Actual depth default value is 0.
		Integer actualDepth = 0;
		// If the given depth is not null.
		if (depth != null) {
			// Updates the actual depth.
			actualDepth = depth;
		}
		// Tries to return the class.
		try {
			// Gets the stack trace.
			final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
			// Returns the name of the method in the selected depth.
			return stackTrace[2 + actualDepth].getClassName();
		}
		// If the depth is deeper than the stack.
		catch (IndexOutOfBoundsException exception) {
			// Returns null.
			return null;
		}
	}
	
	/**
	 * Gets the current class (with the selected depth) being executed.
	 * 
	 * @param depth
	 *            Depth in the current stack for the desired class (0 is the first and so on).
	 * @param classLoader
	 *            Class loader that must be used in the class recovery. If no class loader is given, the
	 *            context class loader is used.
	 * @return The current class (with the selected depth) being executed.
	 * @throws InvalidParameterException
	 *             If the class loader cannot load the current class.
	 */
	public static Class<?> getCurrentClass(final Integer depth, final ClassLoader classLoader)
			throws InvalidParameterException {
		// Actual depth default value is 0.
		Integer actualDepth = 0;
		// If the given depth is not null.
		if (depth != null) {
			// Updates the actual depth.
			actualDepth = depth;
		}
		// Gets the current class name for the given depth.
		final String className = getCurrentClassName(actualDepth + 1);
		// Returns the class for the name.
		return getClass(className, true, classLoader);
	}
	
	/**
	 * Gets the current class (with the selected depth) being executed.
	 * 
	 * @param depth
	 *            Depth in the current stack for the desired class (0 is the first and so on).
	 * @return The current class (with the selected depth) being executed.
	 * @throws InvalidParameterException
	 *             If the class loader cannot load the current class.
	 */
	public static Class<?> getCurrentClass(final Integer depth) throws InvalidParameterException {
		// Actual depth default value is 0.
		Integer actualDepth = 0;
		// If the given depth is not null.
		if (depth != null) {
			// Updates the actual depth.
			actualDepth = depth;
		}
		// The context class loader is used to recover the current class.
		return getCurrentClass(actualDepth + 1, null);
	}
	
	/**
	 * Gets a class with the given name from a given class loader.
	 * 
	 * @param className
	 *            Name of the class to be found.
	 * @param initialize
	 *            Whether the class must be initialized.
	 * @param classLoader
	 *            Class loader to load the class.
	 * @return Returns the class with the name.
	 * @throws InvalidParameterException
	 *             If the class cannot be found for the name.
	 */
	public static Class<?> getClass(final String className, final Boolean initialize,
			final ClassLoader classLoader) throws InvalidParameterException {
		// If the passed class name is null.
		if (className == null) {
			// Throws an exception.
			throw new EmptyParameterException("class"); // TODO
		}
		// Actual class loader default value is the current class loader.
		ClassLoader actualClassLoader = Thread.currentThread().getContextClassLoader();
		// If the given class loader is not null.
		if (classLoader != null) {
			// Updates the actual class loader.
			actualClassLoader = classLoader;
		}
		// Tries to return the class.
		try {
			return Class.forName(className, initialize, actualClassLoader);
		}
		// If the class cannot be found.
		catch (ClassNotFoundException exception) {
			// Throws an exception.
			throw new InvalidParameterException(ErrorKeys.UNACCESSIBLE_CLASS, exception, null);
		}
	}
	
	/**
	 * Gets a class with the given name from a given class loader.
	 * 
	 * @param className
	 *            Name of the class to be found.
	 * @return Returns the class with the name.
	 * @throws InvalidParameterException
	 *             If the class cannot be found for the name.
	 */
	public static Class<?> getClass(final String className) throws InvalidParameterException {
		// Tries to return the class for the current class loader.
		return getClass(className, true, null);
	}
}