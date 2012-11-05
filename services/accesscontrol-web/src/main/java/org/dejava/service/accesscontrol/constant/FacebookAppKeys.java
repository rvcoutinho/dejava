package org.dejava.service.accesscontrol.constant;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;

/**
 * Defines the facebook app keys (should exist in the properties file).
 */
@MessageSources(sources = { @MessageSource(availableLocales = { "" }, description = "Facebook app properties", bundleBaseName = "org.dejava.service.accesscontrol.properties.facebook-app", processors = "org.dejava.component.i18n.source.processor.impl.ConstantValuesEntryProcessor") })
public class FacebookAppKeys {

	/**
	 * Facebook connect url key.
	 */
	public static final String APP_CONNECT_URL = "facebook.app.connect.url";

	/**
	 * Facebook app secret key.
	 */
	public static final String APP_SECRET = "facebook.app.secret";

	/**
	 * Facebook app id key.
	 */
	public static final String APP_ID = "facebook.app.id";

	/**
	 * Facebook app scope key.
	 */
	public static final String APP_SCOPE = "facebook.app.scope";

	/**
	 * Facebook app default redirect url key.
	 */
	public static final String APP_DEFAULT_REDIRECT_URL = "facebook.app.redirect.url.default";

}
