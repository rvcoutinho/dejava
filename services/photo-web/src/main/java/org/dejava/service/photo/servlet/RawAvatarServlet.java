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
@WebServlet(urlPatterns = "/p")
public class RawAvatarServlet extends HttpServlet {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 966380904161796852L;

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
		// Gets the photo id.
		Integer photoId = Integer.parseInt(request.getParameter("photoId"));
		// Makes sure the response is clear.
		response.reset();
		// Defines the response header (content type and file name). FIXME
		response.addHeader("Content-Disposition:", "inline; filename=\"" + photoId + ".jpg\"");
		response.setContentType("image/jpeg");
		// Writes the photo content to the response.
		response.getOutputStream().write(photoComponent.getRawPhotoById(photoId));
	}
}
