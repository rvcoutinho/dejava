package org.dejava.component.i18n.source.processor.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;

import org.dejava.component.i18n.source.processor.MessageSourceEntryProcessor;

/**
 * Processes a class and retrieves entries for its non-static public getters ("classname.fieldname").
 */
public class PublicGettersEntryProcessor implements MessageSourceEntryProcessor {
	
	/**
	 * @see org.dejava.component.i18n.source.processor.MessageSourceEntryProcessor#processClass(javax.lang.model.element.Element)
	 */
	@Override
	public Set<String> processClass(final Element clazz) {
		// Creates an entry set.
		final Set<String> entries = new LinkedHashSet<>();
		// For each enclosed elements of the class.
		for (final Element currentClassElement : clazz.getEnclosedElements()) {
			// If the element is a public method.
			if ((currentClassElement.getKind() == ElementKind.METHOD)
					&& (currentClassElement.getModifiers().contains(Modifier.PUBLIC))) {
				// FIXME Adds the current getter to the entry set.
				entries.add(clazz.getSimpleName().toString() + '.'
						+ currentClassElement.getSimpleName().toString());
			}
		}
		// Returns the retrieved entries.
		return entries;
	}
	
}
