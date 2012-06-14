package org.dejava.component.util.i18n.source.processor;

import java.util.Set;

import javax.lang.model.element.Element;

import org.dejava.component.util.i18n.source.annotation.MessageSources;

/**
 * Process a class annotated with {@link MessageSources} and retrieves entries for the defined message
 * sources.
 * 
 * A public constructor with no arguments must be defined.
 */
public interface MessageSourceEntryProcessor {
	
	/**
	 * Processes a class that defines message sources.
	 * 
	 * @param clazz
	 *            The class from which the entries for the message source will be retrieved.
	 * @return The entries for the message source.
	 */
	public Set<String> processClass(final Element clazz);
}
