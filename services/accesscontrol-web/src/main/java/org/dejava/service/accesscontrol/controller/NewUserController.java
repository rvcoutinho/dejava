package org.dejava.service.accesscontrol.controller;

import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;

import org.dejava.service.accesscontrol.component.UserComponent;
import org.dejava.service.accesscontrol.model.User;
import org.dejava.service.accesscontrol.util.AccessControlCtx;

/**
 * New user controller.
 */
@AccessControlCtx
@ConversationScoped
public class NewUserController implements Serializable {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 4832645626147509219L;

	/**
	 * New user.
	 */
	private User newUser;

	/**
	 * Gets the new user.
	 * 
	 * @return The new user.
	 */
	public User getNewUser() {
		// If the new user is null.
		if (newUser == null) {
			// Creates a new user.
			newUser = new User();
		}
		// Returns the new user.
		return newUser;
	}

	/**
	 * Gets the new user with facebook information.
	 * 
	 * @param facebookUser
	 *            Facebook user information.
	 * @return The new user with facebook information.
	 */
	public User getNewUser(final com.restfb.types.User facebookUser) {
		// Creates a new user.
		newUser = new User(facebookUser);
		// Returns the new user.
		return newUser;
	}

	/**
	 * Sets the new user.
	 * 
	 * @param newUser
	 *            New new user.
	 */
	public void setNewUser(final User newUser) {
		this.newUser = newUser;
	}

	/**
	 * The person EJB service.
	 */
	@Inject
	@AccessControlCtx
	private UserComponent userComponent;

	/**
	 * Creates a new user.
	 */
	public void createNewUser() {
		// Adds the new user.
		userComponent.addOrUpdate(getNewUser());
		// Adds a success info message. TODO
	}

}
