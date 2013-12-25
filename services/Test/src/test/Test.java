package test;

import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

/**
 * Test
 */
@ConversationScoped
@Named("test")
public class Test implements Serializable {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 7684658950396621485L;

	/**
	 * TODO
	 */
	private Part part;

	/**
	 * Gets the part.
	 * 
	 * @return The part.
	 */
	public Part getPart() {
		return part;
	}

	/**
	 * Sets the part.
	 * 
	 * @param part
	 *            New part.
	 */
	public void setPart(Part part) {
		this.part = part;
	}

	/**
	 * TODO
	 */
	public void test() {
		System.out.println(getClass());
	}
}
