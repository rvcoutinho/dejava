package org.dejava.service.philanthropy.dao.proposal;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.philanthropy.model.proposal.ProjectInvestmentProposal;
import org.dejava.service.philanthropy.util.PhilanthropyCtx;

/**
 * Philanthropy project plan proposal DAO.
 */
@PhilanthropyCtx
public class ProjectInvestmentProposalDAO extends AbstractGenericDAO<ProjectInvestmentProposal, Integer> {

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
