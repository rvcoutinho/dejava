package org.dejava.component.util.accesscontrol;

import org.dejava.component.util.accesscontrol.model.Function;
import org.dejava.component.util.accesscontrol.model.User;

/**
 * Defines how to check functionality access for system users.
 */
public interface PermissionChecker {

	/**
	 * Checks the permission for a user to access a functionality in a given
	 * context.
	 * 
	 * @param user
	 *            User to test the access permission.
	 * @param function
	 *            Function to check the access permission.
	 * @param context
	 *            Context of the functionality is being checked.
	 */
	void checkPermission(final User user, final Function function,
			final Object... context);
}
