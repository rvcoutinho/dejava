package org.dejava.component.i18n.source.processor;

import java.util.Set;

import javax.lang.model.element.TypeElement;

import org.dejava.component.i18n.source.annotation.MessageSources;
import org.dejava.component.i18n.source.processor.impl.ConstantValuesEntryProcessor;
import org.dejava.component.i18n.source.processor.impl.EnumTypesEntryProcessor;
import org.dejava.component.i18n.source.processor.impl.FieldsEntryProcessor;
import org.dejava.component.i18n.source.processor.impl.NameEntryProcessor;
import org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor;
import org.dejava.component.i18n.source.processor.impl.PublicMethodsEntryProcessor;

/**
 * Process a class annotated with {@link MessageSources} and retrieves entries for the defined message
 * sources.
 * 
 * A public constructor with no arguments must be defined.
 * 
 * And there are some predefined entry processors: {@link ConstantValuesEntryProcessor},
 * {@link EnumTypesEntryProcessor}, {@link FieldsEntryProcessor}, {@link NameEntryProcessor},
 * {@link PublicGettersEntryProcessor}, {@link PublicMethodsEntryProcessor}.
 */
public interface MessageSourceEntryProcessor {

	/**
	 * Processes a class that defines message sources.
	 * 
	 * @param originalClass
	 *            The original class being processed.
	 * @param currentClass
	 *            The class from which the entries for the message source will be retrieved.
	 * @return The entries for the message source.
	 */
	Set<String> processClass(final TypeElement originalClass, final TypeElement currentClass);

}
