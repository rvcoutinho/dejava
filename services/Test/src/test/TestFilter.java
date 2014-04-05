package test;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 * TODO
 */
@MultipartConfig
public class TestFilter implements Filter {

	/**
	 * TODO
	 */
	private static final String REQUEST_METHOD_POST = "POST";

	/**
	 * TODO
	 */
	private static final String CONTENT_TYPE_MULTIPART = "multipart/";

	/**
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(final FilterConfig arg0) throws ServletException {

	}

	/**
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {

	}

	/**
	 * TODO
	 */
	@Inject
	private Test test;

	/**
	 * Returns true if the given request is a multipart request.
	 * 
	 * @param request
	 *            The request to be checked.
	 * @return True if the given request is a multipart request.
	 */
	public static final boolean isMultipartRequest(final HttpServletRequest request) {
		return REQUEST_METHOD_POST.equalsIgnoreCase(request.getMethod())
				&& (request.getContentType() != null)
				&& request.getContentType().toLowerCase().startsWith(CONTENT_TYPE_MULTIPART);
	}

	/**
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse,
	 *      javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {

		if (isMultipartRequest((HttpServletRequest) request)) {
			//
			for (final Part currentPart : ((HttpServletRequest) request).getParts()) {

				currentPart.getName();
			}
		}

		// TODO Auto-generated method stub

		chain.doFilter(request, response);
	}

}
