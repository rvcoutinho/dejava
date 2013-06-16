package org.dejava.service.accesscontrol.component.principal;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.accesscontrol.dao.principal.EmailDAO;
import org.dejava.service.accesscontrol.model.principal.Email;
import org.dejava.service.accesscontrol.util.AccessControlCtx;

/**
 * EJB service for email.
 */
@AccessControlCtx
@Stateless(name = "Component/AccessControl/Email")
public class EmailComponent extends AbstractGenericComponent<Email, Integer> {

	/**
	 * The email DAO.
	 */
	@Inject
	@AccessControlCtx
	private EmailDAO emailDAO;

	/**
	 * @see org.dejava.component.ejb.component.AbstractGenericComponent#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<Email, Integer> getEntityDAO() {
		return emailDAO;
	}

}
