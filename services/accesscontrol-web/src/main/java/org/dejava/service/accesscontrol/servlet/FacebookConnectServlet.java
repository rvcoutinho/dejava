package org.dejava.service.accesscontrol.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.InvalidParameterException;
import java.util.Properties;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dejava.service.accesscontrol.constant.FacebookAppKeys;

/**
 * The servlet that handles the facebook connection.
 */
@WebServlet(urlPatterns = "/facebookConnect")
public class FacebookConnectServlet extends HttpServlet {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 5882283662574494011L;

	/**
	 * The path for the application properties file.
	 */
	private static final String APP_PROPERTIES_PATH = "org/dejava/service/accesscontrol/properties/facebook-app_.properties";

	/**
	 * The application properties file.
	 */
	private static final Properties APP_PROPERTIES = getAppProperties();

	/**
	 * The name of the state to be used in the facebook state validation.
	 */
	public static final String APP_VALIDATION_STATE_PARAM = "facebookValidationState";

	/**
	 * Gets the application properties object (from file).
	 * 
	 * @return The application properties object (from file).
	 */
	private static Properties getAppProperties() {
		// Gets the current thread class loader.
		final ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
		// Creates a new properties object.
		final Properties appProperties = new Properties();
		// Tries to load the properties content.
		try {
			appProperties.load(contextClassLoader.getResourceAsStream(APP_PROPERTIES_PATH));
		}
		// If an exception is raised.
		catch (final Exception exception) {
			// Throws an exception. TODO
			throw new InvalidParameterException();
		}
		// Returns the new properties object.
		return appProperties;
	}

	/**
	 * Gets the complete facebook connect URL.
	 * 
	 * @param state
	 *            State to be used in the facebook state validation.
	 * @param redirectURL
	 *            The URL that the page should be redirected after connecting.
	 * 
	 * @return The complete facebook connect URL.
	 */
	private String getCompleteConnectURL(final String state, final String redirectURL) {
		// Gets the plain facebook connect URL.
		String connectURL = APP_PROPERTIES.getProperty(FacebookAppKeys.APP_CONNECT_URL);
		// Adds the application id to the URL.
		connectURL += "?client_id=" + APP_PROPERTIES.getProperty(FacebookAppKeys.APP_ID);
		// Adds the application secret to the URL.
		connectURL += "&client_secret=" + APP_PROPERTIES.getProperty(FacebookAppKeys.APP_SECRET);
		// Adds the response type (token) to the URL.
		connectURL += "&response_type=token";
		// Adds the state to the URL.
		connectURL += "&state=" + state;
		// If the scope is define.
		if ((APP_PROPERTIES.getProperty(FacebookAppKeys.APP_SCOPE) != null)
				&& (!(APP_PROPERTIES.getProperty(FacebookAppKeys.APP_SCOPE)).isEmpty())) {
			// Adds the desired scope to the URL.
			connectURL += "&scope=" + APP_PROPERTIES.getProperty(FacebookAppKeys.APP_SCOPE);
		}
		// Adds the redirectURL to the URL.
		connectURL += "&redirect_uri=" + redirectURL;
		// Returns the complete connect URL.
		return connectURL;
	}

	/**
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		// Creates a random state to be used in the facebook state validation.
		final String state = Integer.toHexString(new Random().nextInt(100000));
		// Sets the state to be used in the facebook state validation.
		request.getSession().setAttribute(APP_VALIDATION_STATE_PARAM, state);
		// Redirects to the facebook connect URL. TODO
		response.sendRedirect(getCompleteConnectURL(state, URLEncoder.encode(
				APP_PROPERTIES.getProperty(FacebookAppKeys.APP_DEFAULT_REDIRECT_URL), "UTF-8")));
	}
}
