package org.dejava.component.util.reflection.handler;

import java.lang.annotation.Annotation;

import org.dejava.component.util.exception.localized.unchecked.EmptyParameterException;

/**
 * Helps handling reflection with annotations.
 */
public final class AnnotationHandler1 {
	
	/**
	 * Private constructor.
	 */
	private AnnotationHandler1() {
	}
	
	/**
	 * Returns an annotation from the passed class (and its inherited classes and interfaces).
	 * 
	 * @param <AnyAnnotation>
	 *            Any annotation being searched.
	 * @param clazz
	 *            Class from which the annotation will be searched for.
	 * @param annotationClass
	 *            Type of the annotation to be searched for.
	 * @return An annotation from the passed class (and its inherited classes and interfaces).
	 * @throws EmptyParameterException
	 *             If the passed class is null.
	 */
	public static <AnyAnnotation extends Annotation> AnyAnnotation getAnnotation(final Class<?> clazz,
			final Class<AnyAnnotation> annotationClass) throws EmptyParameterException {
		// If the passed class is null.
		if (clazz == null) {
			// Throws an exception.
			throw new EmptyParameterException("class"); // TODO
		}
		// If the passed annotation class is null.
		if (annotationClass == null) {
			// Throws an exception.
			throw new EmptyParameterException("annotation class");
		}
		// For each superclass/interface.
		for (Class<?> currentClass : ClassHandler.getSuperclasses(clazz)) {
			// Tries to get the desired annotation.
			final AnyAnnotation annotation = currentClass.getAnnotation(annotationClass);
			// If the annotation was correctly recovered.
			if (annotation != null) {
				// Returns it.
				return annotation;
			}
		}
		// If no annotation was found, returns null.
		return null;
	}
}
