package org.dejava.service.philanthropy.model.proposal;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;
import org.dejava.service.philanthropy.model.party.Sponsor;

/**
 * A project investment proposal from a sponsor.
 */
@Entity
@Table(name = "project_investment_proposal")
@MessageSources(sources = {
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.philanthropy.properties.model", entriesAffix = { "", ".description" }, processSuperclasses = true, processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }),
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.philanthropy.properties.error", processSuperclasses = true, processors = { "org.dejava.component.i18n.source.processor.impl.GetterConstraintEntryProcessor" }) })
public class ProjectInvestmentProposal extends ProjectProposal {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -4894191311317233129L;

	/**
	 * The proposer.
	 */
	private Sponsor proposer;

	/**
	 * @see org.dejava.service.philanthropy.model.proposal.ProjectProposal#getProposer()
	 */
	@Override
	public Sponsor getProposer() {
		return proposer;
	}

	/**
	 * Sets the proposer.
	 * 
	 * @param proposer
	 *            New proposer.
	 */
	public void setProposer(final Sponsor proposer) {
		this.proposer = proposer;
	}

	/**
	 * Default constructor.
	 */
	public ProjectInvestmentProposal() {
		super();
	}

	/**
	 * Default constructor.
	 * 
	 * @param projectId
	 *            The philanthropy project identifier for the proposal.
	 * @param proposerId
	 *            The proposer identifier.
	 */
	public ProjectInvestmentProposal(final Integer projectId, final Integer proposerId) {
		super(projectId);
		this.proposer = new Sponsor(projectId);
	}

}
