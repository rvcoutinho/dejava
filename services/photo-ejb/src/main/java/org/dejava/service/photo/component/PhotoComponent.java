package org.dejava.service.photo.component;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.service.photo.businessrule.PhotoBusinessRuleSet;
import org.dejava.service.photo.dao.PhotoDAO;
import org.dejava.service.photo.util.PhotoCtx;

/**
 * EJB component for photo.
 */
@PhotoCtx
@Stateless(name = "Component/Photo/Photo")
public class PhotoComponent {

	/**
	 * The photo DAO.
	 */
	@Inject
	@PhotoCtx
	private PhotoDAO photoDAO;

	/**
	 * The application message business rule set.
	 */
	@Inject
	@PhotoCtx
	private PhotoBusinessRuleSet photoBusinessRuleSet;

}
