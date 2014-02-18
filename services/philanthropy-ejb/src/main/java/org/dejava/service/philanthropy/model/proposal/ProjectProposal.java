package org.dejava.service.philanthropy.model.proposal;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.dejava.component.ejb.entity.AbstractIdentifiedEntity;
import org.dejava.service.philanthropy.model.party.PhilanthropyParty;
import org.dejava.service.philanthropy.model.project.PhilanthropyProject;

/**
 * A philanthropy project proposal.
 */
@MappedSuperclass
public abstract class ProjectProposal extends AbstractIdentifiedEntity {

	/**
	 * Generated serial
	 */
	private static final long serialVersionUID = -8723836588118326822L;

	/**
	 * The philanthropy project for the proposal.
	 */
	private PhilanthropyProject project;

	/**
	 * Gets the philanthropy project for the proposal.
	 * 
	 * @return The philanthropy project for the proposal.
	 */
	@Column(name = "project")
	public PhilanthropyProject getProject() {
		return project;
	}

	/**
	 * Sets the philanthropy project for the proposal.
	 * 
	 * @param project
	 *            New philanthropy project for the proposal.
	 */
	public void setProject(final PhilanthropyProject project) {
		this.project = project;
	}

	/**
	 * Gets the proposer.
	 * 
	 * @return The proposer.
	 */
	@Transient
	public abstract PhilanthropyParty getProposer();

	/**
	 * Default constructor.
	 */
	public ProjectProposal() {
		super();
	}

	/**
	 * Default constructor.
	 * 
	 * @param projectId
	 *            The philanthropy project identifier for the proposal.
	 */
	public ProjectProposal(final Integer projectId) {
		this.project = new PhilanthropyProject(projectId);
	}
}
