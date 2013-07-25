package org.dejava.service.party.component;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.component.ejb.entity.ExternalEntityLoader;
import org.dejava.service.contact.component.ContactComponent;
import org.dejava.service.contact.util.ContactCtx;
import org.dejava.service.party.dao.PartyDAO;
import org.dejava.service.party.model.Party;
import org.dejava.service.party.util.PartyCtx;
import org.dejava.service.place.component.PlaceComponent;
import org.dejava.service.place.util.PlaceCtx;

/**
 * Party EJB component.
 */
@PartyCtx
@Stateless(name = "Component/Party/Party")
public class PartyComponent extends AbstractGenericComponent<Party, Integer> {

	/**
	 * The party DAO.
	 */
	@Inject
	@PartyCtx
	private PartyDAO partyDAO;

	/**
	 * The contact EJB component.
	 */
	@Inject
	@ContactCtx
	private ContactComponent contactComponent;

	/**
	 * The place EJB component.
	 */
	@Inject
	@PlaceCtx
	private PlaceComponent placeComponent;

	/**
	 * @see org.dejava.component.ejb.component.AbstractGenericComponent#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<Party, Integer> getEntityDAO() {
		return partyDAO;
	}

	/**
	 * @see org.dejava.component.ejb.component.AbstractGenericComponent#addOrUpdate(java.lang.Object)
	 */
	@Override
	public Party addOrUpdate(final Party entity) {
		// Add or update the entity.
		final Party persistedEntity = super.addOrUpdate(entity);
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
