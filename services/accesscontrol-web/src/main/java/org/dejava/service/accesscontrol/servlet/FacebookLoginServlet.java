package org.dejava.service.accesscontrol.servlet;

import static org.dejava.service.accesscontrol.util.FacebookAppProps.APP_PROPERTIES;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.dejava.service.accesscontrol.authc.FacebookUserToken;
import org.dejava.service.accesscontrol.component.UserComponent;
import org.dejava.service.accesscontrol.constant.FacebookAppKeys;
import org.dejava.service.accesscontrol.model.User;
import org.dejava.service.accesscontrol.util.AccessControlCtx;
import org.dejava.service.party.component.PartyComponent;
import org.dejava.service.party.model.Person;
import org.dejava.service.party.util.PartyCtx;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;

/**
 * The servlet that handles the facebook connection.
 */
@WebServlet(urlPatterns = "/login/facebook")
public class FacebookLoginServlet extends HttpServlet {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 5882283662574494011L;

	/**
	 * Current subject.
	 */
	@Inject
	@AccessControlCtx
	private Subject subject;

	/**
	 * Gets the OAuth exchange code URL.
	 * 
	 * @param state
	 *            State to be used in the facebook state validation.
	 * @param redirectURL
	 *            The URL that the page should be redirected after connecting.
	 * @return The OAuth exchange code URL.
	 * @exception IOException
	 *                If an input or output error occurs while the servlet is handling the HTTP request.
	 */
	private String getOAuthExchangeCodeURL(final String state, final String redirectURL) throws IOException {
		// Gets the plain facebook connect URL.
		final StringBuffer exchangeCodeURL = new StringBuffer(
				APP_PROPERTIES.getProperty(FacebookAppKeys.APP_LOGIN_DIALOG_URL));
		// Adds the application id to the URL.
		exchangeCodeURL.append("?" + APP_PROPERTIES.getProperty(FacebookAppKeys.APP_CLIENT_ID_PARAM) + "="
				+ APP_PROPERTIES.getProperty(FacebookAppKeys.APP_ID));
		// Adds the application secret to the URL.
		exchangeCodeURL.append("&" + APP_PROPERTIES.getProperty(FacebookAppKeys.APP_CLIENT_SECRET_PARAM)
				+ "=" + APP_PROPERTIES.getProperty(FacebookAppKeys.APP_SECRET));
		// Adds the state to the URL.
		exchangeCodeURL.append("&" + APP_PROPERTIES.getProperty(FacebookAppKeys.APP_STATE_PARAM) + "="
				+ state);
		// If the scope is define.
		if ((APP_PROPERTIES.getProperty(FacebookAppKeys.APP_SCOPE) != null)
				&& (!(APP_PROPERTIES.getProperty(FacebookAppKeys.APP_SCOPE)).isEmpty())) {
			// Adds the desired scope to the URL.
			exchangeCodeURL.append("&" + APP_PROPERTIES.getProperty(FacebookAppKeys.APP_SCOPE_PARAM) + "="
					+ APP_PROPERTIES.getProperty(FacebookAppKeys.APP_SCOPE));
		}
		// Adds the redirect URI to the URL.
		exchangeCodeURL.append("&" + APP_PROPERTIES.getProperty(FacebookAppKeys.APP_REDIRECT_URI_PARAM) + "="
				+ URLEncoder.encode(redirectURL, "UTF-8"));
		// Returns the exchange code URL.
		return exchangeCodeURL.toString();
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
		// Sets the redirect URL to be used in the login.
		request.getSession().setAttribute(APP_PROPERTIES.getProperty(FacebookAppKeys.APP_REDIRECT_URI_PARAM),
				request.getHeader("Referer"));
		// Redirects to the facebook login dialog URL.
		response.sendRedirect(getOAuthExchangeCodeURL(state, request.getRequestURL().toString()));
	}

	/**
	 * Gets the OAuth access token URL.
	 * 
	 * @param redirectURL
	 *            The URL that the page should be redirected after connecting.
	 * @param oAuthCode
	 *            The OAuth exchange code to be used.
	 * @return The OAuth access token URL.
	 * @exception IOException
	 *                If an input or output error occurs while the servlet is handling the HTTP request.
	 */
	private String getOAuthAccessTokenURL(final String redirectURL, final String oAuthCode)
			throws IOException {
		// Gets the plain facebook connect URL.
		final StringBuffer accessTokenURL = new StringBuffer(
				APP_PROPERTIES.getProperty(FacebookAppKeys.APP_TOKEN_EXCHANGE_URL));
		// Adds the application id to the URL.
		accessTokenURL.append("?" + APP_PROPERTIES.getProperty(FacebookAppKeys.APP_CLIENT_ID_PARAM) + "="
				+ APP_PROPERTIES.getProperty(FacebookAppKeys.APP_ID));
		// Adds the application secret to the URL.
		accessTokenURL.append("&" + APP_PROPERTIES.getProperty(FacebookAppKeys.APP_CLIENT_SECRET_PARAM) + "="
				+ APP_PROPERTIES.getProperty(FacebookAppKeys.APP_SECRET));
		// Adds the redirect URI to the URL.
		accessTokenURL.append("&" + APP_PROPERTIES.getProperty(FacebookAppKeys.APP_REDIRECT_URI_PARAM) + "="
				+ URLEncoder.encode(redirectURL, "UTF-8").toString());
		// Adds the OAuth exchange code to the URL.
		accessTokenURL.append("&" + APP_PROPERTIES.getProperty(FacebookAppKeys.APP_OAUTH_CODE_PARAM) + "="
				+ oAuthCode);
		// Returns the access token URL.
		return accessTokenURL.toString();
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
			final URL oAuthAccessToken = new URL(getOAuthAccessTokenURL(request.getRequestURL().toString(),
					request.getParameter(APP_PROPERTIES.getProperty(FacebookAppKeys.APP_OAUTH_CODE_PARAM))));
			// Gets the access token information
			final String accessTokenInfo = new BufferedReader(new InputStreamReader(
					oAuthAccessToken.openStream())).readLine();
			// Redirects to the login with the access token information.
			response.sendRedirect(request.getRequestURL().toString() + "?" + accessTokenInfo);
		}
		// Otherwise. TODO
	}

	/**
	 * The user EJB component.
	 */
	@Inject
	@AccessControlCtx
	private UserComponent userComponent;

	/**
	 * The party EJB component.
	 */
	@Inject
	@PartyCtx
	private PartyComponent partyComponent;

	/**
	 * Creates a new user and person for the given facebook user. FIXME
	 * 
	 * @param fbUser
	 *            The facebook user.
	 */
	private void createNewUserPerson(final com.restfb.types.User fbUser) {
		// Persists a new user with the facebook user information.
		final User newUser = userComponent.addOrUpdate(new User(fbUser));
		// Creates a new person for the facebook user.
		final Person newPerson = new Person(fbUser);
		// Sets the user for the person.
		newPerson.setUser(newUser);
		// Persists the new person.
		partyComponent.addOrUpdate(newPerson);
	}

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
		// Gets the access token.
		final String accessToken = request.getParameter(APP_PROPERTIES
				.getProperty(FacebookAppKeys.APP_ACCESS_TOKEN_PARAM));
		// Gets the facebook client with the given access token.
		final FacebookClient fbClient = new DefaultFacebookClient(accessToken);
		// Gets the current facebook user.
		final com.restfb.types.User fbUser = fbClient.fetchObject("me", com.restfb.types.User.class);
		// If there is no facebook user.
		if (fbUser == null) {
			// Redirects to the facebook connect servlet.
			response.sendRedirect(request.getRequestURL().toString());
		}
		// If there is a logged facebook user.
		else {
			// If there is not a facebook principal for the facebook user id.
			if (userComponent.getByFacebookUser(fbUser) == null) {
				// Creates a new user and person for the facebook user.
				createNewUserPerson(fbUser);
			}
			// Creates a new facebook authentication token.
			final FacebookUserToken facebookToken = new FacebookUserToken(fbUser.getId());
			// Tries to log the user in.
			subject.login(facebookToken);
			// Sets the access token to the subject session (so it can be accessed by other applications).
			subject.getSession().setAttribute(
					APP_PROPERTIES.getProperty(FacebookAppKeys.APP_ACCESS_TOKEN_PARAM), accessToken);
			// Redirects to the facebook original request URL.
			response.sendRedirect((String) request.getSession().getAttribute(
					APP_PROPERTIES.getProperty(FacebookAppKeys.APP_REDIRECT_URI_PARAM)));
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
