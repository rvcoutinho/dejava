package org.dejava.service.place.dao;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.dejava.component.ejb.constant.DAOParamKeys;
import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.component.validation.method.PreConditions;
import org.dejava.service.place.model.Place;
import org.dejava.service.place.util.PlaceCtx;

/**
 * DAO for place.
 */
@PlaceCtx
public class PlaceDAO extends AbstractGenericDAO<Place, Integer> {

	/**
	 * Entity manager being used.
	 */
	@Inject
	@PlaceCtx
	private EntityManager entityManager;

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
	public Place merge(final Place entity) {
		// Asserts that the entity is not null.
		PreConditions.assertParamNotNull(DAOParamKeys.ENTITY, entity);
		// If the entity has no id (not previously persisted).
		if (entity.getIdentifier() == null) {
			// Tries to get a place with the same name.
			final Collection<Place> similarPlaces = getByAttribute("name", entity.getName(), null, null);
			// If the place has a parent place.
			if (entity.getParentPlace() != null) {
				// Merges the parent place and resets it.
				entity.setParentPlace(merge(entity.getParentPlace()));
			}
			// For each place with the same name. TODO Improve performance.
			for (final Place similarPlace : similarPlaces) {
				// If the place has the same name and parent place.
				if ((entity.getName().equals(similarPlace.getName()))
						&& (((entity.getParentPlace() == null) && (similarPlace.getParentPlace() == null)) || ((entity
								.getParentPlace() != null) && (entity.getParentPlace().equals(similarPlace
								.getParentPlace()))))) {
					// Copies the similar place id (as they are the same).
					entity.setIdentifier(similarPlace.getIdentifier());
					// If the similar place has a reference.
					if (similarPlace.getReference() != null) {
						// Copies the similar place reference (as they are the same).
						entity.setReference(similarPlace.getReference());
					}
					// If the similar place has a short name.
					if (similarPlace.getShortName() != null) {
						// Copies the similar place short name (as they are the same).
						entity.setShortName(similarPlace.getShortName());
					}
					// Stops searching.
					break;
				}
			}

		}
		// Merges and returns the given entity with the persistence context.
		return getEntityManager().merge(entity);
	}
}
