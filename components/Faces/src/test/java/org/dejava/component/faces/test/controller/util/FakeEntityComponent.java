package org.dejava.component.faces.test.controller.util;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.ejb.businessrule.GenericEntityBusinessRuleSet;
import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.ejb.dao.GenericDAO;

/**
 * Fake entity stateless component.
 */
@Faces
@Stateless(name = "Component/Test/FakeEntityComponent")
public class FakeEntityComponent extends AbstractGenericComponent<FakeEntity, Integer> {

	/**
	 * The fake entity DAO.
	 */
	@Faces
	@Inject
	private FakeEntityDAO entityDAO;

	/**
	 * @see org.dejava.component.ejb.component.AbstractGenericComponent#getEntityDAO()
	 */
	@Override
	public GenericDAO<FakeEntity, Integer> getEntityDAO() {
		return entityDAO;
	}

	/**
	 * The fake entity rule set.
	 */
	@Faces
	@Inject
	private FakeEntityBusinessRuleSet fakeEntityBusinessRuleSet;

	/**
	 * @see org.dejava.component.ejb.component.AbstractGenericComponent#getEntityBusinessRuleSet()
	 */
	@Override
	public GenericEntityBusinessRuleSet<FakeEntity> getEntityBusinessRuleSet() {
		return fakeEntityBusinessRuleSet;
	}

	/**
	 * Gets all entities with the given attribute value.
	 * 
	 * @param attributeName
	 *            The attribute name.
	 * @param attributeValue
	 *            The attribute value.
	 * @param firstResult
	 *            The first result that should be considered by the query.
	 * @param maxResults
	 *            The maximum numbers of results to be considered by the query.
	 * @return All entities with the given attribute value.
	 */
	public Collection<FakeEntity> getByAttribute(final String attributeName, final Object attributeValue,
			final Integer firstResult, final Integer maxResults) {
		// Tries to get the entities.
		return getEntityDAO().getByAttribute(attributeName, attributeValue, firstResult, maxResults);
	}

	/**
	 * Gets an entity by its attribute.
	 * 
	 * @param attributeName
	 *            The attribute name.
	 * @param attributeValue
	 *            The attribute value.
	 * @return The entity by its attribute.
	 */
	public FakeEntity getByAttribute(final String attributeName, final Object attributeValue) {
		// Tries to get the entity.
		return getEntityDAO().getByAttribute(attributeName, attributeValue);
	}

}
