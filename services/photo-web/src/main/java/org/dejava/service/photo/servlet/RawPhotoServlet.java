package org.dejava.service.photo.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dejava.service.photo.component.PhotoComponent;
import org.dejava.service.photo.util.PhotoCtx;

/**
 * The servlet that respond with a raw photo.
 */
@WebServlet(urlPatterns = "/a")
public class RawPhotoServlet extends HttpServlet {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 3327509421284420522L;

	/**
	 * The photo EJB component.
	 */
	@Inject
	@PhotoCtx
	private PhotoComponent photoComponent;

	/**
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void service(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		// Gets the owner id.
		final Integer ownerId = Integer.parseInt(request.getParameter("ownerId"));
		// Makes sure the response is clear.
		response.reset();
		// Defines the response header (content type and file name). FIXME
		response.addHeader("Content-Disposition:", "inline; filename=\"" + ownerId + ".jpg\"");
		response.setContentType("image/jpeg");
		// Writes the photo content to the response.
		response.getOutputStream().write(photoComponent.getRawAvatarByOwner(ownerId));
	}
}
