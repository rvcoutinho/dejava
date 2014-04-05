package org.dejava.service.philanthropy.model.proposal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;
import org.dejava.service.philanthropy.model.party.NonProfitOrg;
import org.dejava.service.philanthropy.model.project.plan.ProjectPlan;

/**
 * A project plan proposal from a non profit organization.
 */
/**
 * TODO
 */
@Entity
@Table(name = "project_plan_proposal")
@MessageSources(sources = {
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.philanthropy.properties.model", entriesAffix = { "", ".description" }, processSuperclasses = true, processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }),
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.philanthropy.properties.error", processSuperclasses = true, processors = { "org.dejava.component.i18n.source.processor.impl.GetterConstraintEntryProcessor" }) })
public class ProjectPlanProposal extends ProjectProposal {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -1312209078451076921L;

	/**
	 * The proposer.
	 */
	private NonProfitOrg proposer;

	/**
	 * @see org.dejava.service.philanthropy.model.proposal.ProjectProposal#getProposer()
	 */
	@Override
	public NonProfitOrg getProposer() {
		return proposer;
	}

	/**
	 * Sets the proposer.
	 * 
	 * @param proposer
	 *            New proposer.
	 */
	public void setProposer(final NonProfitOrg proposer) {
		this.proposer = proposer;
	}

	/**
	 * Project plan for the proposal.
	 */
	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "project_plan")
	private ProjectPlan projectPlan;

	/**
	 * Gets the project plan for the proposal.
	 * 
	 * @return The project plan for the proposal.
	 */
	public ProjectPlan getProjectPlan() {
		return projectPlan;
	}

	/**
	 * Sets the project plan for the proposal.
	 * 
	 * @param projectPlan
	 *            New project plan for the proposal.
	 */
	public void setProjectPlan(final ProjectPlan projectPlan) {
		this.projectPlan = projectPlan;
	}

	/**
	 * Default constructor.
	 */
	public ProjectPlanProposal() {
		super();
	}

	/**
	 * Default constructor.
	 * 
	 * @param projectId
	 *            The philanthropy project identifier for the proposal.
	 * @param proposerId
	 *            The proposer identifier.
	 * @param projectPlan
	 *            The project plan for the proposal.
	 */
	public ProjectPlanProposal(final Integer projectId, final Integer proposerId,
			final ProjectPlan projectPlan) {
		super(projectId);
		this.proposer = new NonProfitOrg(projectId);
		this.projectPlan = projectPlan;
	}

}
