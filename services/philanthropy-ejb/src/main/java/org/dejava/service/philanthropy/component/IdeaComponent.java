package org.dejava.service.philanthropy.component;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.philanthropy.dao.IdeaDAO;
import org.dejava.service.philanthropy.model.Idea;
import org.dejava.service.philanthropy.util.PhilanthropyCtx;

/**
 * TODO
 */
@PhilanthropyCtx
@Stateless(name = "Component/Philanthropy/Idea")
public class IdeaComponent extends AbstractGenericComponent<Idea, Integer> {

	/**
	 * The person DAO.
	 */
	@Inject
	@PhilanthropyCtx
	private IdeaDAO ideaDAO;

	/**
	 * @see org.dejava.component.ejb.component.AbstractGenericComponent#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<Idea, Integer> getEntityDAO() {
		return ideaDAO;
	}

}
