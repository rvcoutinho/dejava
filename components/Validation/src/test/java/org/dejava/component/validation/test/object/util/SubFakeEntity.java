package org.dejava.component.validation.test.object.util;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;

/**
 * Fake entity.
 */
@MessageSources(sources = { @MessageSource(sourcePath = "src/test/resources", processSuperclasses = true, bundleBaseName = "org.dejava.component.validation.test.properties.error", processors = { "org.dejava.component.i18n.source.processor.impl.FieldAnnotationEntryProcessor" }) })
public class SubFakeEntity extends FakeEntity {

	/**
	 * Default constructor.
	 * 
	 * @param email
	 *            Email address.
	 * @param age
	 *            Age.
	 */
	public SubFakeEntity(String email, Integer age) {
		super(email, age);
	}
}
