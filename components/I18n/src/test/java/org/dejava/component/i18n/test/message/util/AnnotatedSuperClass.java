package org.dejava.component.i18n.test.message.util;

import org.dejava.component.i18n.message.annotation.MessageBundle;
import org.dejava.component.i18n.message.annotation.MessageBundles;

/**
 * Some annotated super class.
 */
@MessageBundles(messageBundles = { @MessageBundle(type = "superclass", baseName = "org.dejava.component.i18n.test.message.properties.superclass") })
public class AnnotatedSuperClass {
}
