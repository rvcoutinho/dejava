package org.dejava.service.party.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.component.ejb.entity.ExternalEntityLoader;
import org.dejava.service.contact.component.ContactComponent;
import org.dejava.service.contact.util.ContactCtx;
import org.dejava.service.party.model.Party;
import org.dejava.service.party.util.PartyCtx;
import org.dejava.service.place.component.PlaceComponent;
import org.dejava.service.place.util.PlaceCtx;

/**
 * Party DAO.
 */
@PartyCtx
public class PartyDAO extends AbstractGenericDAO<Party, Integer> {

	/**
	 * Entity manager being used.
	 */
	@Inject
	@PartyCtx
	private EntityManager entityManager;

	/**
	 * Contact EJB component.
	 */
	@Inject
	@ContactCtx
	private ContactComponent contactComponent;

	/**
	 * Place EJB component.
	 */
	@Inject
	@PlaceCtx
	private PlaceComponent placeComponent;

	/**
	 * @see org.dejava.component.ejb.dao.AbstractGenericDAO#getEntityManager()
	 */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * @see org.dejava.component.ejb.dao.AbstractGenericDAO#merge(java.lang.Object)
	 */
	@Override
	public Party merge(final Party entity) {
		// Add or update the entity.
		final Party persistedEntity = super.merge(entity);
		// Persists the contacts for the entity.
		persistedEntity.getContacts().addAll(entity.getContacts());
		// Persists the addresses for the entity.
		persistedEntity.getAddresses().addAll(entity.getAddresses());
		// Updates the mapping for the external entities.
		ExternalEntityLoader.updateReverseMappedEntities(persistedEntity);
		// Persists the contacts for the entity.
		persistedEntity.getContacts().addAll(contactComponent.addOrUpdate(entity.getContacts()));
		// Persists the addresses for the entity.
		persistedEntity.getAddresses().addAll(placeComponent.addOrUpdate(entity.getAddresses()));
		// Returns the persisted entity.
		return persistedEntity;
	}

}
