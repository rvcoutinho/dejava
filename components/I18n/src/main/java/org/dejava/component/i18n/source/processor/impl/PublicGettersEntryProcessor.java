package org.dejava.component.i18n.source.processor.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

import org.dejava.component.i18n.source.processor.MessageSourceEntryProcessor;
import org.dejava.component.reflection.MethodMirror;

/**
 * Processes a class and retrieves entries for its non-static public getters ("classname.fieldname").
 */
public class PublicGettersEntryProcessor implements MessageSourceEntryProcessor {

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
			// If the element is a public method.
			if ((currentClassElement.getKind() == ElementKind.METHOD)
					&& (currentClassElement.getModifiers().contains(Modifier.PUBLIC))) {
				// If the name is from a getter.
				if (MethodMirror.isGetter(currentClassElement.getSimpleName().toString())) {
					// Adds the current field (from the getter) to the entry set.
					entries.add(originalClass.getSimpleName().toString() + '.'
							+ MethodMirror.getFieldName(currentClassElement.getSimpleName().toString()));
				}
			}
		}
		// Returns the retrieved entries.
		return entries;
	}

}
