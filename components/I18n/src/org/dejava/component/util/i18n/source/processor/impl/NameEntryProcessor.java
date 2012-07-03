package org.dejava.component.util.i18n.source.processor.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.lang.model.element.Element;

import org.dejava.component.util.i18n.source.processor.MessageSourceEntryProcessor;

/**
 * Processes a class and retrieves an entry for its name ("classname").
 */
public class NameEntryProcessor implements MessageSourceEntryProcessor {
	
	/**
	 * @see org.dejava.component.util.i18n.source.processor.MessageSourceEntryProcessor#processClass(javax.lang.model.element.Element)
	 */
	@Override
	public Set<String> processClass(final Element clazz) {
		// Creates an entry set.
		final Set<String> entries = new LinkedHashSet<>();
		// Adds the current class name as an entry.
		entries.add(clazz.getSimpleName().toString());
		// Returns the retrieved entries.
		return entries;
	}
	
}
