package org.dejava.service.message.businessrule;

import org.dejava.component.ejb.businessrule.AbstractGenericEntityBusinessRuleSet;
import org.dejava.service.message.model.EmailMessage;
import org.dejava.service.message.util.MessageCtx;

/**
 * Email message business rule set.
 */
@MessageCtx
public class EmailMessageBusinessRuleSet extends AbstractGenericEntityBusinessRuleSet<EmailMessage> {

}
