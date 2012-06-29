package org.dejava.component.util.i18n.test.source.auxiliary;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.lang.model.element.Element;

import org.dejava.component.util.i18n.source.processor.MessageSourceEntryProcessor;

/**
 * Sample message source entry provider.
 */
public class SampleEntryProvider implements MessageSourceEntryProcessor {
	
	/**
	 * Keys for the message source.
	 */
	public static final String[] KEYS = { "test1", "test2", "test.3", "test.3.1" };
	
	/**
	 * @see org.dejava.component.util.i18n.source.processor.MessageSourceEntryProcessor#processClass(javax.lang.model.element.Element)
	 */
	@Override
	public Set<String> processClass(final Element clazz) {
		// Returns the keys.
		return new LinkedHashSet<>(Arrays.asList(KEYS));
	}
	
}
