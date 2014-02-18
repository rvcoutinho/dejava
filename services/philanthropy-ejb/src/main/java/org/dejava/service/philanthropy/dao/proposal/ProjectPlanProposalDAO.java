package org.dejava.service.philanthropy.dao.proposal;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.philanthropy.model.proposal.ProjectPlanProposal;
import org.dejava.service.philanthropy.util.PhilanthropyCtx;

/**
 * Philanthropy project investment proposal DAO.
 */
@PhilanthropyCtx
public class ProjectPlanProposalDAO extends AbstractGenericDAO<ProjectPlanProposal, Integer> {

	/**
	 * Entity manager being used.
	 */
	@Inject
	@PhilanthropyCtx
	private EntityManager entityManager;

	/**
	 * @see org.dejava.component.ejb.dao.AbstractGenericDAO#getEntityManager()
	 */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
