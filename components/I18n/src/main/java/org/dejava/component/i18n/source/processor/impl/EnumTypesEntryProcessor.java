package org.dejava.component.i18n.source.processor.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;

import org.dejava.component.i18n.source.processor.MessageSourceEntryProcessor;

/**
 * Processes an enumerated and retrieves entries for its constants ("enumname.constantname").
 */
public class EnumTypesEntryProcessor implements MessageSourceEntryProcessor {

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
			// If the element is a enumerated type.
			if (currentClassElement.getKind() == ElementKind.ENUM_CONSTANT) {
				// Adds the enumerated type to the entry set.
				entries.add(originalClass.getSimpleName().toString() + '.'
						+ currentClassElement.getSimpleName().toString());
			}
		}
		// Returns he retrieved entries.
		return entries;

	}

}
