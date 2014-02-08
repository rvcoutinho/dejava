package org.dejava.service.message.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.message.model.AppNotification;
import org.dejava.service.message.util.MessageCtx;

/**
 * DAO for the application notification.
 */
@MessageCtx
public class AppNotificationDAO extends AbstractGenericDAO<AppNotification, Integer> {

	/**
	 * Entity manager being used.
	 */
	@Inject
	@MessageCtx
	private EntityManager entityManager;

	/**
	 * @see org.dejava.component.ejb.dao.AbstractGenericDAO#getEntityManager()
	 */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Counts the unread notifications by the recipient.
	 * 
	 * @param recipient
	 *            The recipient for the notification.
	 * @return The unread notifications by the recipient.
	 */
	public Long countUnreadByRecipient(final Integer recipient) {
		// Gets the named query for the recipient application notifications.
		final TypedQuery<Long> countUnreadByRecipient = getEntityManager().createNamedQuery(
				"countUnreadAppNotificationsByRecipient", Long.class);
		// Sets the recipient to the named query.
		countUnreadByRecipient.setParameter("recipient", recipient);
		// Executes the query and gets the unread count.
		return countUnreadByRecipient.getSingleResult();
	}

}
