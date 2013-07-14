package org.dejava.component.i18n.source.processor.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;

import org.dejava.component.i18n.source.processor.MessageSourceEntryProcessor;

/**
 * Processes a class and retrieves entries for field annotations.
 */
public class FieldAnnotationEntryProcessor implements MessageSourceEntryProcessor {

	/**
	 * @see org.dejava.component.i18n.source.processor.MessageSourceEntryProcessor#processClass(javax.lang.model.element.TypeElement,
	 *      javax.lang.model.element.TypeElement)
	 */
	@Override
	public Set<String> processClass(final TypeElement originalClass, final TypeElement currentClass) {
		// Creates an entry set.
		final Set<String> entries = new LinkedHashSet<>();
		// For each enclosed elements of the class.
		for (final Element currentClassElement : currentClass.getEnclosedElements()) {
			// If the element is a public final field.
			if (currentClassElement.getKind() == ElementKind.FIELD) {
				// For each annotation in the field.
				for (final AnnotationMirror currentAnnotation : currentClassElement.getAnnotationMirrors()) {
					// Adds the current field annotation name to the entry set.
					entries.add(originalClass.getSimpleName().toString() + '.'
							+ currentClassElement.getSimpleName().toString() + "."
							+ currentAnnotation.getAnnotationType().asElement().getSimpleName());

				}
			}
		}
		// Returns he retrieved entries.
		return entries;
	}
}
