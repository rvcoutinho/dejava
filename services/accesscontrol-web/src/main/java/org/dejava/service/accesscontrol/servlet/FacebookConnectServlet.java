package org.dejava.service.accesscontrol.servlet;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * TODO
 */
@WebServlet(urlPatterns = "/facebookConnect")
public class FacebookConnectServlet extends HttpServlet {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 5882283662574494011L;

	/**
	 * The application secret.
	 */
	private static final String APP_SECRET = "f10e83c5cfed28d950a5127941b84637";

	/**
	 * The application id.
	 */
	private static final String APP_ID = "456694181047424";

	/**
	 * Comma separated permissions that should be granted.
	 */
	private static final String APP_SCOPE = "";

	/**
	 * Default redirect URL.
	 */
	private static final String APP_DEFAULT_REDIRECT_URL = "http://localhost:8080/";

	/**
	 * The plain facebook connect URL.
	 */
	private static final String APP_PLAIN_CONNECT_URL = "https://www.facebook.com/dialog/oauth";

	/**
	 * The name of the state to be used in the facebook state validation.
	 */
	public static final String APP_VALIDATION_STATE_PARAM = "facebookValidationState";

	/**
	 * TODO
	 * 
	 * @param state
	 *            State to be used in the facebook state validation
	 * @param redirectURL
	 *            TODO
	 * 
	 * @return The complete facebook connect URL.
	 */
	private String getConnectURL(final String state, final String redirectURL) {
		// Gets the plain facebook connect URL.
		String connectURL = APP_PLAIN_CONNECT_URL;
		// Adds the application id to the URL.
		connectURL += "?client_id=" + APP_ID;
		// Adds the application secret to the URL.
		connectURL += "&client_secret=" + APP_SECRET;
		// Adds the response type (token) to the URL.
		connectURL += "&response_type=token";
		// Adds the state to the URL.
		connectURL += "&state=" + state;
		// If the scope is define.
		if ((APP_SCOPE != null) && (!APP_SCOPE.isEmpty())) {
			// Adds the desired scope to the URL.
			connectURL += "&scope=" + APP_SCOPE;
		}
		// Adds the redirectURL to the URL.
		connectURL += "&redirect_uri=" + APP_DEFAULT_REDIRECT_URL;
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
		final String state = Integer.toHexString(new Random().nextInt(10000));
		// Sets the state to be used in the facebook state validation.
		request.getSession().setAttribute(APP_VALIDATION_STATE_PARAM, state);
		// Redirects to the facebook connect URL. TODO
		response.sendRedirect(getConnectURL(state, null));
	}
}
