package org.dejava.service.philanthropy.businessrule.share;

import org.dejava.component.ejb.businessrule.AbstractGenericEntityBusinessRuleSet;
import org.dejava.component.validation.method.PreConditions;
import org.dejava.service.philanthropy.constant.InfoKeys;
import org.dejava.service.philanthropy.model.share.ProjectShare;
import org.dejava.service.philanthropy.util.MessageTypes;
import org.dejava.service.philanthropy.util.PhilanthropyCtx;

/**
 * Project share business rule set.
 */
@PhilanthropyCtx
public class ProjectShareBusinessRuleSet extends AbstractGenericEntityBusinessRuleSet<ProjectShare> {

	/**
	 * Validates that a project share is unique.
	 * 
	 * @param similarSharesCount
	 *            The number of similar shares.
	 */
	public void validateUnique(final Long similarSharesCount) {
		PreConditions.assertParamValid(similarSharesCount < new Long(1), MessageTypes.Info.class,
				InfoKeys.PROJECT_ALREADY_SHARED, null);
	}

	/**
	 * Validates the project share.
	 * 
	 * @param projectShare
	 *            The original project share.
	 * @param similarSharesCount
	 *            The number of similar shares.
	 * @param groups
	 *            Groups that should be considered in the validation.
	 */
	public void validate(final ProjectShare projectShare, final Long similarSharesCount,
			final Class<?>... groups) {
		// Validates the project share original state.
		validate(projectShare, groups);
		// Validates that the project share is unique.
		validateUnique(similarSharesCount);
	}

}
