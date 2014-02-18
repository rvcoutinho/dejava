package org.dejava.service.message.businessrule;

import org.dejava.component.ejb.businessrule.AbstractGenericEntityBusinessRuleSet;
import org.dejava.service.message.model.AppMessage;
import org.dejava.service.message.util.MessageCtx;

/**
 * Application message business rule set.
 */
@MessageCtx
public class AppMessageBusinessRuleSet extends AbstractGenericEntityBusinessRuleSet<AppMessage> {

}
