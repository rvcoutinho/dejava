package test;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

/**
 * Test
 */
@SessionScoped
@ManagedBean(name = "test")
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
