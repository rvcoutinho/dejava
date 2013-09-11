package org.dejava.service.philanthropy.component.share;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.philanthropy.dao.share.ProjectShareDAO;
import org.dejava.service.philanthropy.model.party.Supporter;
import org.dejava.service.philanthropy.model.project.Project;
import org.dejava.service.philanthropy.model.share.ProjectShare;
import org.dejava.service.philanthropy.util.PhilanthropyCtx;

/**
 * Project share EJB component.
 */
@PhilanthropyCtx
@Stateless(name = "Component/Philanthropy/ProjectShare")
public class ProjectShareComponent extends AbstractGenericComponent<ProjectShare, Integer> {

	/**
	 * The project share DAO.
	 */
	@Inject
	@PhilanthropyCtx
	private ProjectShareDAO projectShareDAO;

	/**
	 * @see org.dejava.component.ejb.component.AbstractGenericComponent#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<ProjectShare, Integer> getEntityDAO() {
		return projectShareDAO;
	}

	/**
	 * Shares a project.
	 * 
	 * @param project
	 *            The project to be shared.
	 * @param supporter
	 *            The supporter sharing the project.
	 * @return Returns the updated number of shares for the project.
	 */
	public Long share(final Project project, final Supporter supporter) {
		// TODO Validate.
		// Creates a new project share.
		final ProjectShare projectShare = new ProjectShare(project, supporter);
		// Persists the share.
		addOrUpdate(projectShare);
		// Returns the updated number of shares.
		return countProjectShares(project.getIdentifier());
	}

	/**
	 * Counts the number of shares for a project.
	 * 
	 * @param projectId
	 *            The project id.
	 * @return The number of shares for a project.
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Long countProjectShares(final Integer projectId) {
		return projectShareDAO.countProjectShares(projectId);
	}
}
