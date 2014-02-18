package org.dejava.service.philanthropy.component;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.dejava.service.philanthropy.businessrule.project.PhilanthropyProjectBusinessRuleSet;
import org.dejava.service.philanthropy.businessrule.share.ProjectShareBusinessRuleSet;
import org.dejava.service.philanthropy.dao.project.PhilanthropyProjectDAO;
import org.dejava.service.philanthropy.dao.share.ProjectShareDAO;
import org.dejava.service.philanthropy.model.project.PhilanthropyProject;
import org.dejava.service.philanthropy.model.share.ProjectShare;
import org.dejava.service.philanthropy.util.PhilanthropyCtx;

/**
 * Philanthropy project EJB component.
 */
@PhilanthropyCtx
@Stateless(name = "Component/Philanthropy/PhilanthropyProject")
public class PhilanthropyProjectComponent {

	/**
	 * The project DAO.
	 */
	@Inject
	@PhilanthropyCtx
	private PhilanthropyProjectDAO projectDAO;

	/**
	 * The project share DAO.
	 */
	@Inject
	@PhilanthropyCtx
	private ProjectShareDAO shareDAO;

	/**
	 * The project business rule set.
	 */
	@Inject
	@PhilanthropyCtx
	private PhilanthropyProjectBusinessRuleSet projectBusinessRuleSet;

	/**
	 * The project share DAO.
	 */
	@Inject
	@PhilanthropyCtx
	private ProjectShareBusinessRuleSet shareBusinessRuleSet;

	/**
	 * Gets a project by its identifier.
	 * 
	 * @param identifier
	 *            The identifier of the project.
	 * @return A project by its identifier.
	 */
	public PhilanthropyProject getProject(final Integer identifier) {
		return projectDAO.getById(identifier);
	}

	/**
	 * Gets all ideas.
	 * 
	 * @param firstResult
	 *            The first result that should be considered.
	 * @param maxResults
	 *            The maximum numbers of results to be considered.
	 * 
	 * @return All not planned project ideas.
	 */
	public Collection<PhilanthropyProject> getIdeas(final Integer firstResult, final Integer maxResults) {
		return projectDAO.getByAttribute("plan", null, firstResult, maxResults);
	}

	/**
	 * Creates a new idea.
	 * 
	 * @param idea
	 *            The new idea.
	 */
	public void createIdea(final PhilanthropyProject idea) {
		// Validates the idea to be added.
		projectBusinessRuleSet.validate(idea);
		// Adds the idea.
		projectDAO.merge(idea);
	}

	/**
	 * Gets all planned project.
	 * 
	 * @param firstResult
	 *            The first result that should be considered.
	 * @param maxResults
	 *            The maximum numbers of results to be considered.
	 * 
	 * @return All project plans.
	 */
	public Collection<PhilanthropyProject> getPlannedProjects(final Integer firstResult,
			final Integer maxResults) {
		// FIXME
		return new ArrayList<>();
	}

	/**
	 * Creates a new planned project.
	 * 
	 * @param plannedProject
	 *            The new planned project.
	 */
	public void createPlannedProject(final PhilanthropyProject plannedProject) {
		// Validates the project to be added.
		projectBusinessRuleSet.validate(plannedProject);
		// Adds the planned project.
		projectDAO.merge(plannedProject);
	}

	/**
	 * Proposes a project plan.
	 * 
	 * @param projectId
	 *            The identifier of the project for the proposal.
	 * @param proposerId
	 *            The identifier of the proposer.
	 */
	public void proposeProjectPlan(final Integer projectId, final Integer proposerId) {

	}

	/**
	 * Counts the number of ideas created by a supporter.
	 * 
	 * @param supporterId
	 *            The supporter id.
	 * @return The number of ideas created by a supporter.
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Long countProjectsCreatedBySupporter(final Integer supporterId) {
		return projectDAO.countCreatedBySupporter(supporterId);
	}

	/**
	 * Gets the ideas and projects created by a supporter.
	 * 
	 * @param supporterId
	 *            The supporter id.
	 * @param firstResult
	 *            The first result that should be considered by the query.
	 * @param maxResults
	 *            The maximum numbers of results to be considered by the query.
	 * @return The projects created by a supporter.
	 */
	public Collection<PhilanthropyProject> getProjectsCreatedBySupporter(final Integer supporterId,
			final Integer firstResult, final Integer maxResults) {
		return projectDAO.getCreatedBySupporter(supporterId, firstResult, maxResults);
	}

	/**
	 * Counts the number of shares for a project.
	 * 
	 * @param projectId
	 *            The project id.
	 * @return The number of shares for a project.
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Long countProjectSharesByProject(final Integer projectId) {
		return shareDAO.countSharesByProject(projectId);
	}

	/**
	 * Counts the number of shares for a supporter.
	 * 
	 * @param supporterId
	 *            The supporter id.
	 * @return The number of shares for a supporter.
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Long countProjectSharesBySupporter(final Integer supporterId) {
		return shareDAO.countSharesBySupporter(supporterId);
	}

	/**
	 * Gets the projects shared by a supporter.
	 * 
	 * @param supporterId
	 *            The supporter id.
	 * @param firstResult
	 *            The first result that should be considered by the query.
	 * @param maxResults
	 *            The maximum numbers of results to be considered by the query.
	 * @return The projects shared by a supporter.
	 */
	public Collection<PhilanthropyProject> getProjectsSharedBySupporter(final Integer supporterId,
			final Integer firstResult, final Integer maxResults) {
		return projectDAO.getSharedBySupporter(supporterId, firstResult, maxResults);
	}

	/**
	 * Shares a project.
	 * 
	 * @param projectId
	 *            The identifier of the project to be shared.
	 * @param supporterId
	 *            The identifier of the supporter sharing the project.
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	private void shareProject(final Integer projectId, final Integer supporterId) {
		// Creates a new project share.
		final ProjectShare projectShare = new ProjectShare(projectId, supporterId);
		// Validates the share to be added.
		shareBusinessRuleSet.validate(projectShare,
				shareDAO.countSharesByProjectAndSupporter(projectId, supporterId));
		// Persists the share.
		shareDAO.merge(projectShare);
	}

	/**
	 * Shares a project and counts the new numbers of shares.
	 * 
	 * @param projectId
	 *            The identifier of the project to be shared.
	 * @param supporterId
	 *            The identifier of the supporter sharing the project.
	 * @return Returns the updated number of shares for the project.
	 */
	public Long shareProjectAndCount(final Integer projectId, final Integer supporterId) {
		// Shares the project.
		shareProject(projectId, supporterId);
		// Returns the updated number of shares.
		return countProjectSharesByProject(projectId);
	}

}
