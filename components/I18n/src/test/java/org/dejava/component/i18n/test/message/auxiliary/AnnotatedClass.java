package org.dejava.component.i18n.test.message.auxiliary;

import org.dejava.component.i18n.message.annotation.MessageBundle;
import org.dejava.component.i18n.message.annotation.MessageBundles;

/**
 * Some annotated class.
 */
@MessageBundles(defaultType = "class", messageBundles = { @MessageBundle(type = "class", baseName = "org.dejava.component.i18n.test.message.properties.class") })
public class AnnotatedClass extends AnnotatedSuperClass implements AnnotatedInterface {

}
