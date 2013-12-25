package org.dejava.service.soupsocial.controller.user;

import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;

import org.dejava.service.accesscontrol.util.AccessControlCtx;
import org.dejava.service.soupsocial.util.SoupSocialCtx;

/**
 * TODO
 */
@SoupSocialCtx
@ConversationScoped
public class NewUserController implements Serializable {

	/**
	 * TODO
	 */
	private static final long serialVersionUID = 6875495318442075713L;

	/**
	 * TODO
	 */
	@Inject
	@AccessControlCtx
	private org.dejava.service.accesscontrol.controller.NewUserController newUserController;

}
