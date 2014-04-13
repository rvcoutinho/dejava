package org.dejava.service.philanthropy.component;

import static org.dejava.properties.util.FacebookAPIProps.API_PROPERTIES;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.apache.shiro.SecurityUtils;
import org.dejava.properties.constant.FacebookAPIKeys;
import org.dejava.service.philanthropy.businessrule.project.ProjectBusinessRuleSet;
import org.dejava.service.philanthropy.businessrule.share.ProjectShareBusinessRuleSet;
import org.dejava.service.philanthropy.dao.project.ProjectDAO;
import org.dejava.service.philanthropy.dao.share.ProjectShareDAO;
import org.dejava.service.philanthropy.model.Category;
import org.dejava.service.philanthropy.model.project.Project;
import org.dejava.service.philanthropy.model.share.ProjectShare;
import org.dejava.service.philanthropy.util.PhilanthropyCtx;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.User;

/**
 * Philanthropy project EJB component.
 */
@PhilanthropyCtx
@Stateless(name = "Component/Philanthropy/Project")
public class ProjectComponent {

	/**
	 * The project DAO.
	 */
	@Inject
	@PhilanthropyCtx
	private ProjectDAO projectDAO;

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
	private ProjectBusinessRuleSet projectBusinessRuleSet;

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
	public Project getProject(final Integer identifier) {
		return projectDAO.getById(identifier);
	}

	/**
	 * Gets all projects.
	 * 
	 * @param firstResult
	 *            The first result that should be considered.
	 * @param maxResults
	 *            The maximum numbers of results to be considered.
	 * @return All projects.
	 */
	public Collection<Project> getProjects(final Integer firstResult, final Integer maxResults) {
		return projectDAO.getAll(firstResult, maxResults);
	}

	/**
	 * Gets all projects by category.
	 * 
	 * @param category
	 *            The category for the projects.
	 * @param firstResult
	 *            The first result that should be considered.
	 * @param maxResults
	 *            The maximum numbers of results to be considered.
	 * @return Projects by category.
	 */
	public Collection<Project> getProjectsByCategory(final Category category, final Integer firstResult,
			final Integer maxResults) {
		return projectDAO.getByAttribute("category", category, firstResult, maxResults);
	}

	/**
	 * Gets all projects by tag.
	 * 
	 * @param tag
	 *            The tag for the projects.
	 * @param firstResult
	 *            The first result that should be considered.
	 * @param maxResults
	 *            The maximum numbers of results to be considered.
	 * @return Projects by tag.
	 */
	public Collection<Project> getProjectsByTag(final String tag, final Integer firstResult,
			final Integer maxResults) {
		return projectDAO.getProjectsByTag(tag, firstResult, maxResults);
	}

	/**
	 * Gets all projects by category or tag (or none).
	 * 
	 * @param category
	 *            The category for the projects.
	 * @param tag
	 *            The tag for the projects.
	 * @param firstResult
	 *            The first result that should be considered.
	 * @param maxResults
	 *            The maximum numbers of results to be considered.
	 * @return Projects by category or tag (or none).
	 */
	public Collection<Project> getProjectsByCategoryOrTag(final Category category, final String tag,
			final Integer firstResult, final Integer maxResults) {
		// If both the category and tag are null.
		if ((category == null) && (tag == null)) {
			// Returns all projects.
			return getProjects(firstResult, maxResults);
		}
		// If the category is not null.
		else if (category != null) {
			// Returns projects by category.
			return getProjectsByCategory(category, firstResult, maxResults);
		}
		// If the tag is not null.
		else {
			// Returns projects by tag.
			return getProjectsByTag(tag, firstResult, maxResults);
		}
	}

	/**
	 * Gets all not planned project ideas.
	 * 
	 * @param firstResult
	 *            The first result that should be considered.
	 * @param maxResults
	 *            The maximum numbers of results to be considered.
	 * @return All not planned project ideas.
	 */
	public Collection<Project> getIdeas(final Integer firstResult, final Integer maxResults) {
		return projectDAO.getByAttribute("plan", null, firstResult, maxResults);
	}

	/**
	 * Creates a new idea.
	 * 
	 * @param idea
	 *            The new idea.
	 */
	public void createIdea(final Project idea) {
		// Validates the idea to be added.
		projectBusinessRuleSet.validate(idea);
		// Adds the idea.
		projectDAO.persist(idea);
	}

	/**
	 * Gets all planned projects.
	 * 
	 * @param firstResult
	 *            The first result that should be considered.
	 * @param maxResults
	 *            The maximum numbers of results to be considered.
	 * 
	 * @return All project plans.
	 */
	public Collection<Project> getPlannedProjects(final Integer firstResult, final Integer maxResults) {
		return projectDAO.getPlannedProjects(firstResult, maxResults);
	}

	/**
	 * Creates a new planned project.
	 * 
	 * @param plannedProject
	 *            The new planned project.
	 */
	public void createPlannedProject(final Project plannedProject) {
		// Validates the project to be added.
		projectBusinessRuleSet.validate(plannedProject);
		// Adds the planned project.
		projectDAO.persist(plannedProject);
	}

	/**
	 * Gets all ongoing projects.
	 * 
	 * @param firstResult
	 *            The first result that should be considered.
	 * @param maxResults
	 *            The maximum numbers of results to be considered.
	 * 
	 * @return All project plans.
	 */
	public Collection<Project> getOngoingProjects(final Integer firstResult, final Integer maxResults) {
		return projectDAO.getOngoingProjects(firstResult, maxResults);
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
		return projectDAO.countProjectsCreatedBySupporter(supporterId);
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
	public Collection<Project> getProjectsCreatedBySupporter(final Integer supporterId,
			final Integer firstResult, final Integer maxResults) {
		return projectDAO.getProjectsCreatedBySupporter(supporterId, firstResult, maxResults);
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
	public Collection<Project> getProjectsSharedBySupporter(final Integer supporterId,
			final Integer firstResult, final Integer maxResults) {
		return projectDAO.getProjectsSharedBySupporter(supporterId, firstResult, maxResults);
	}

	/**
	 * Shares a project on facebook.
	 * 
	 * @param projectId
	 *            The identifier of the project to be shared.
	 * @param supporterId
	 *            The identifier of the supporter sharing the project.
	 * @return The current number of friends for the user.
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	private Integer shareProjectOnFacebook(final Integer projectId, final Integer supporterId) {
		// Gets the access token.
		final String accessToken = (String) SecurityUtils.getSubject().getSession()
				.getAttribute(API_PROPERTIES.getProperty(FacebookAPIKeys.APP_ACCESS_TOKEN_PARAM));
		// Gets the facebook client with the given access token.
		final FacebookClient fbClient = new DefaultFacebookClient(accessToken);
		// Shares the project in the user time line.
		fbClient.publish("me/feed", FacebookType.class,
				Parameter.with("link", "http://www.soupsocial.com/projeto/" + projectId),
				Parameter.with("description", "Description"), Parameter.with("message", "Message")); // TODO
		// Returns the current user number of friends (on facebook).
		return fbClient.fetchConnection("me/friends", User.class).getData().size();
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
		// Shares the project on facebook.
		final Integer fbFriends = shareProjectOnFacebook(projectId, supporterId);
		// Creates a new project share.
		final ProjectShare projectShare = new ProjectShare(projectId, supporterId);
		// Gets the project for the share
		final Project project = projectDAO.getById(projectId);
		// Updates the number project impacts.
		project.setPossibleImpacts(project.getPossibleImpacts() + fbFriends);
		// Validates the share to be added.
		shareBusinessRuleSet.validate(projectShare,
				shareDAO.countSharesByProjectAndSupporter(projectId, supporterId));
		// Persists the share.
		shareDAO.persist(projectShare);
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
