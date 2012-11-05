package org.dejava.component.i18n.test.message.auxiliary;

import org.dejava.component.i18n.message.annotation.MessageBundle;
import org.dejava.component.i18n.message.annotation.MessageBundles;

/**
 * Some annotated super class.
 */
@MessageBundles(defaultType = "superclass", messageBundles = { @MessageBundle(type = "superclass", baseName = "org.dejava.component.i18n.test.message.properties.superclass") })
public class AnnotatedSuperClass {
}
