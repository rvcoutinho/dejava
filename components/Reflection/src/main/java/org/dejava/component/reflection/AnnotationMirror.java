package org.dejava.component.reflection;

import java.lang.annotation.Annotation;

import org.dejava.component.exception.localized.unchecked.EmptyParameterException;
import org.dejava.component.reflection.constant.AnnotationParamKeys;
import org.dejava.component.validation.method.PreConditions;

/**
 * TODO
 * 
 * @param <Reflected>
 *            The annotation to be reflected.
 */
public class AnnotationMirror<Reflected extends Annotation> {

	/**
	 * Annotation being reflected.
	 */
	private Reflected reflectedAnnotation;

	/**
	 * Gets the annotation being reflected.
	 * 
	 * @return The annotation being reflected.
	 */
	public Reflected getReflectedAnnotation() {
		return reflectedAnnotation;
	}

	/**
	 * Sets the annotation being reflected.
	 * 
	 * @param reflectedAnnotation
	 *            New annotation being reflected.
	 */
	public void setReflectedAnnotation(final Reflected reflectedAnnotation) {
		this.reflectedAnnotation = reflectedAnnotation;
	}

	/**
	 * Public constructor for the reflection class.
	 * 
	 * @param reflectedAnnotation
	 *            Annotation being reflected.
	 * @throws EmptyParameterException
	 *             If the reflected annotation is not given.
	 */
	public AnnotationMirror(final Reflected reflectedAnnotation) throws EmptyParameterException {
		// Asserts that the annotation is not null.
		PreConditions.assertParamNotNull(AnnotationParamKeys.ANNOTATION, reflectedAnnotation);
		// Sets the main fields.
		this.reflectedAnnotation = reflectedAnnotation;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((reflectedAnnotation == null) ? 0 : reflectedAnnotation.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final AnnotationMirror<?> other = (AnnotationMirror<?>) obj;
		if (reflectedAnnotation == null) {
			if (other.reflectedAnnotation != null) {
				return false;
			}
		} else if (!reflectedAnnotation.equals(other.reflectedAnnotation)) {
			return false;
		}
		return true;
	}
}
