package org.dejava.service.accesscontrol.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidParameterException;
import java.util.Properties;
import java.util.Random;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.dejava.service.accesscontrol.authc.FacebookUserToken;
import org.dejava.service.accesscontrol.business.UserService;
import org.dejava.service.accesscontrol.business.principal.FacebookService;
import org.dejava.service.accesscontrol.constant.FacebookAppKeys;
import org.dejava.service.accesscontrol.model.User;
import org.dejava.service.accesscontrol.util.AccessControl;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;

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
	 * The application properties file.
	 */
	private static final Properties APP_PROPERTIES = getAppProperties();

	/**
	 * Gets the OAuth exchange code URL.
	 * 
	 * @param state
	 *            State to be used in the facebook state validation.
	 * @param redirectURL
	 *            The URL that the page should be redirected after connecting.
	 * 
	 * @return The OAuth exchange code URL.
	 */
	private String getOAuthExchangeCodeURL(final String state, final String redirectURL) {
		// Gets the plain facebook connect URL.
		String exchangeCodeURL = APP_PROPERTIES.getProperty(FacebookAppKeys.APP_LOGIN_DIALOG_URL);
		// Adds the application id to the URL.
		exchangeCodeURL += "?" + APP_PROPERTIES.getProperty(FacebookAppKeys.APP_CLIENT_ID_PARAM) + "="
				+ APP_PROPERTIES.getProperty(FacebookAppKeys.APP_ID);
		// Adds the application secret to the URL.
		exchangeCodeURL += "&" + APP_PROPERTIES.getProperty(FacebookAppKeys.APP_CLIENT_SECRET_PARAM) + "="
				+ APP_PROPERTIES.getProperty(FacebookAppKeys.APP_SECRET);
		// Adds the state to the URL.
		exchangeCodeURL += "&" + APP_PROPERTIES.getProperty(FacebookAppKeys.APP_STATE_PARAM) + "=" + state;
		// If the scope is define.
		if ((APP_PROPERTIES.getProperty(FacebookAppKeys.APP_SCOPE) != null)
				&& (!(APP_PROPERTIES.getProperty(FacebookAppKeys.APP_SCOPE)).isEmpty())) {
			// Adds the desired scope to the URL.
			exchangeCodeURL += "&" + APP_PROPERTIES.getProperty(FacebookAppKeys.APP_SCOPE_PARAM) + "="
					+ APP_PROPERTIES.getProperty(FacebookAppKeys.APP_SCOPE);
		}
		// Adds the redirect URI to the URL.
		exchangeCodeURL += "&" + APP_PROPERTIES.getProperty(FacebookAppKeys.APP_REDIRECT_URI_PARAM) + "="
				+ redirectURL;
		// Returns the exchange code URL.
		return exchangeCodeURL;
	}

	/**
	 * Requests an OAuth exchange code (from the facebook API).
	 * 
	 * @param request
	 *            the {@link HttpServletRequest} object that contains the request the client made of the
	 *            servlet.
	 * @param response
	 *            the {@link HttpServletResponse} object that contains the response the servlet returns to the
	 *            client.
	 * @exception IOException
	 *                If an input or output error occurs while the servlet is handling the HTTP request.
	 * @exception ServletException
	 *                If the HTTP request cannot be handled.
	 */
	private void requestOAuthExchangeCode(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		// Creates a random state to be used in the facebook state validation.
		final String state = Integer.toHexString(new Random().nextInt(100000000));
		// Sets the state to be used in the facebook state validation.
		request.getSession().setAttribute(APP_PROPERTIES.getProperty(FacebookAppKeys.APP_STATE_ATTR), state);
		// Redirects to the facebook login dialog URL.
		response.sendRedirect(getOAuthExchangeCodeURL(state, URLEncoder.encode(
				APP_PROPERTIES.getProperty(FacebookAppKeys.APP_DEFAULT_REDIRECT_URL), "UTF-8")));
	}

	/**
	 * Gets the OAuth access token URL.
	 * 
	 * @param redirectURL
	 *            The URL that the page should be redirected after connecting.
	 * @param oAuthCode
	 *            The OAuth exchange code to be used.
	 * @return The OAuth access token URL.
	 */
	private String getOAuthAccessTokenURL(final String redirectURL, final String oAuthCode) {
		// Gets the plain facebook connect URL.
		String exchangeCodeURL = APP_PROPERTIES.getProperty(FacebookAppKeys.APP_TOKEN_EXCHANGE_URL);
		// Adds the application id to the URL.
		exchangeCodeURL += "?" + APP_PROPERTIES.getProperty(FacebookAppKeys.APP_CLIENT_ID_PARAM) + "="
				+ APP_PROPERTIES.getProperty(FacebookAppKeys.APP_ID);
		// Adds the application secret to the URL.
		exchangeCodeURL += "&" + APP_PROPERTIES.getProperty(FacebookAppKeys.APP_CLIENT_SECRET_PARAM) + "="
				+ APP_PROPERTIES.getProperty(FacebookAppKeys.APP_SECRET);
		// Adds the redirect URI to the URL.
		exchangeCodeURL += "&" + APP_PROPERTIES.getProperty(FacebookAppKeys.APP_REDIRECT_URI_PARAM) + "="
				+ redirectURL;
		// Adds the OAuth exchange code to the URL.
		exchangeCodeURL += "&" + APP_PROPERTIES.getProperty(FacebookAppKeys.APP_OAUTH_CODE_PARAM) + "="
				+ oAuthCode;
		// Returns the exchange code URL.
		return exchangeCodeURL;
	}

	/**
	 * Requests an OAuth access token (from the facebook API).
	 * 
	 * @param request
	 *            the {@link HttpServletRequest} object that contains the request the client made of the
	 *            servlet.
	 * @param response
	 *            the {@link HttpServletResponse} object that contains the response the servlet returns to the
	 *            client.
	 * @exception IOException
	 *                If an input or output error occurs while the servlet is handling the HTTP request.
	 * @exception ServletException
	 *                If the HTTP request cannot be handled.
	 */
	private void requestOAuthAccessToken(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		// If the saved state is the same in the request.
		if (request.getSession().getAttribute(APP_PROPERTIES.getProperty(FacebookAppKeys.APP_STATE_ATTR))
				.equals(request.getParameter(APP_PROPERTIES.getProperty(FacebookAppKeys.APP_STATE_PARAM)))) {
			// Gets the URL for the access token exchange.
			final URL oAuthAccessToken = new URL(getOAuthAccessTokenURL(URLEncoder.encode(
					APP_PROPERTIES.getProperty(FacebookAppKeys.APP_DEFAULT_REDIRECT_URL), "UTF-8"),
					request.getParameter(APP_PROPERTIES.getProperty(FacebookAppKeys.APP_OAUTH_CODE_PARAM))));
			// Gets the access token information
			final String accessTokenInfo = new BufferedReader(new InputStreamReader(
					oAuthAccessToken.openStream())).readLine();
			// Redirects to the login with the access token information.
			response.sendRedirect(APP_PROPERTIES.getProperty(FacebookAppKeys.APP_DEFAULT_REDIRECT_URL) + "?"
					+ accessTokenInfo);
		}
		// Otherwise. TODO
	}

	/**
	 * The facebook EJB service.
	 */
	@Inject
	@AccessControl
	private FacebookService facebookService;

	/**
	 * The user EJB service.
	 */
	@Inject
	@AccessControl
	private UserService userService;

	/**
	 * Log the facebook user in.
	 * 
	 * @param request
	 *            the {@link HttpServletRequest} object that contains the request the client made of the
	 *            servlet.
	 * @param response
	 *            the {@link HttpServletResponse} object that contains the response the servlet returns to the
	 *            client.
	 * @exception IOException
	 *                If an input or output error occurs while the servlet is handling the HTTP request.
	 * @exception ServletException
	 *                If the HTTP request cannot be handled.
	 */
	private void login(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		// Gets the facebook client with the given access token.
		final FacebookClient fbClient = new DefaultFacebookClient(
				request.getParameter(FacebookConnectServlet.APP_PROPERTIES
						.getProperty(FacebookAppKeys.APP_ACCESS_TOKEN_PARAM)));
		// Gets the current facebook user.
		final com.restfb.types.User fbUser = fbClient.fetchObject("me", com.restfb.types.User.class);
		// If there is no facebook user.
		if (fbUser == null) {
			// Redirects to the facebook connect servlet.
			response.sendRedirect(APP_PROPERTIES.getProperty(FacebookAppKeys.APP_DEFAULT_REDIRECT_URL));
		}
		// If there is logged facebook user.
		else {
			// If there is not a facebook principal for the facebook user id.
			// Think is this is the right place. TODO
			if (facebookService.getEntityByAttribute("identifier", fbUser.getId()) == null) {
				// Adds a new user with the facebook user information.
				userService.addOrUpdate(new User(fbUser));
			}
			// Creates a new facebook authentication token.
			final FacebookUserToken facebookToken = new FacebookUserToken(fbUser.getId());
			// Tries to log the user in.
			SecurityUtils.getSubject().login(facebookToken);
		}
	}

	/**
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void service(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		// If there is an access token in the request URL.
		if (request.getParameter(APP_PROPERTIES.getProperty(FacebookAppKeys.APP_ACCESS_TOKEN_PARAM)) != null) {
			// Logs the facebook user in.
			login(request, response);
		}
		// If there is an OAuth exchange token.
		else if (request.getParameter(APP_PROPERTIES.getProperty(FacebookAppKeys.APP_OAUTH_CODE_PARAM)) != null) {
			// Requests an OAuth access token.
			requestOAuthAccessToken(request, response);
		}
		// Otherwise.
		else {
			// Requests an OAuth exchange code.
			requestOAuthExchangeCode(request, response);
		}
	}
}
