package org.dejava.component.i18n.test.message.util;

import org.dejava.component.i18n.message.annotation.MessageBundle;
import org.dejava.component.i18n.message.annotation.MessageBundles;

/**
 * Some annotated interface.
 */
@MessageBundles(defaultType = "interface", messageBundles = { @MessageBundle(type = "interface", baseName = "org.dejava.component.i18n.test.message.properties.interface") })
public interface AnnotatedInterface {

}
