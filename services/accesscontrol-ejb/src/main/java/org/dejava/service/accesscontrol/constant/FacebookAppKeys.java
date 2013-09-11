package org.dejava.service.accesscontrol.constant;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;

/**
 * Defines the facebook app keys (should exist in the properties file).
 */
@MessageSources(sources = { @MessageSource(availableLocales = { "" }, description = "Facebook app properties", bundleBaseName = "org.dejava.service.accesscontrol.properties.facebook-app", processors = "org.dejava.component.i18n.source.processor.impl.ConstantValuesEntryProcessor") })
public class FacebookAppKeys {

	/**
	 * Facebook login dialog URL key.
	 */
	public static final String APP_LOGIN_DIALOG_URL = "facebook.app.login.dialog.url";

	/**
	 * Facebook access token exchange URL key.
	 */
	public static final String APP_TOKEN_EXCHANGE_URL = "facebook.app.login.token.exchange.url";

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
	 * The key of the session attribute (facebookValidationState) to be used in the facebook state validation.
	 */
	public static final String APP_STATE_ATTR = "facebook.app.attr.state";

	/**
	 * The key of parameter (client_id) to be used in the facebook connect URL.
	 */
	public static final String APP_CLIENT_ID_PARAM = "facebook.app.param.client.id";

	/**
	 * The key of parameter (client_secret) to be used in the facebook connect URL.
	 */
	public static final String APP_CLIENT_SECRET_PARAM = "facebook.app.param.client.secret";

	/**
	 * The key of parameter (state) to be used in the facebook connect URL.
	 */
	public static final String APP_STATE_PARAM = "facebook.app.param.state";

	/**
	 * The key of parameter (scope) to be used in the facebook connect URL.
	 */
	public static final String APP_SCOPE_PARAM = "facebook.app.param.scope";

	/**
	 * The key of parameter (redirect_uri) to be used in the facebook connect URL.
	 */
	public static final String APP_REDIRECT_URI_PARAM = "facebook.app.param.redirect.url";

	/**
	 * The key of parameter (connect_url) to be used in the facebook connect service.
	 */
	public static final String APP_CONNECT_URL_PARAM = "facebook.app.param.connect.url";

	/**
	 * The key of parameter (code) to be used in the facebook connect URL.
	 */
	public static final String APP_OAUTH_CODE_PARAM = "facebook.app.param.oauth.code";

	/**
	 * The key of parameter (access_token) to be used in the facebook connect URL.
	 */
	public static final String APP_ACCESS_TOKEN_PARAM = "facebook.app.param.oauth.access.token";

}
