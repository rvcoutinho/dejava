package org.dejava.service.message.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.message.model.AppMessage;
import org.dejava.service.message.util.MessageCtx;

/**
 * DAO for the application message.
 */
@MessageCtx
public class AppMessageDAO extends AbstractGenericDAO<AppMessage, Integer> {

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
	 * Gets the application messages for the party.
	 * 
	 * @param partyId
	 *            The party for the messages.
	 * @param firstResult
	 *            The first result that should be considered by the query.
	 * @param maxResults
	 *            The maximum numbers of results to be considered by the query.
	 * @return The application messages for the party
	 */
	public List<AppMessage> getAppMessagesByParty(Integer partyId, final Integer firstResult,
			final Integer maxResults) {
		// Gets the named query for the party application messages.
		final TypedQuery<AppMessage> getAppMessagesByParty = getEntityManager().createNamedQuery(
				"getAppMessagesByParty", AppMessage.class);
		// Sets the party id to the named query.
		getAppMessagesByParty.setParameter("partyId", partyId);
		// Limits the query results.
		limitResultList(getAppMessagesByParty, firstResult, maxResults);
		// Executes the query and gets the messages.
		return getAppMessagesByParty.getResultList();
	}
}
