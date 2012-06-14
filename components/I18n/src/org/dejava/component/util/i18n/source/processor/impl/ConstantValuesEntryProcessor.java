package org.dejava.component.util.i18n.source.processor.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.VariableElement;

import org.dejava.component.util.i18n.source.processor.MessageSourceEntryProcessor;

/**
 * Processes a class and retrieves entries for its public constant values.
 */
public class ConstantValuesEntryProcessor implements MessageSourceEntryProcessor {
	
	/**
	 * @see org.dejava.component.util.i18n.source.processor.MessageSourceEntryProcessor#processClass(javax.lang.model.element.Element)
	 */
	@Override
	public Set<String> processClass(final Element clazz) {
		// Creates an entry set.
		final Set<String> entries = new LinkedHashSet<>();
		// For each enclosed elements of the class.
		for (final Element currentClassElement : clazz.getEnclosedElements()) {
			// If the element is a public final field.
			if ((currentClassElement.getKind() == ElementKind.FIELD)
					&& (currentClassElement.getModifiers().contains(Modifier.FINAL))
					&& (currentClassElement.getModifiers().contains(Modifier.PUBLIC))) {
				// Adds the field value in the entry set.
				entries.add(((VariableElement) currentClassElement).getConstantValue().toString());
			}
		}
		// Returns he retrieved entries.
		return entries;
	}
}
