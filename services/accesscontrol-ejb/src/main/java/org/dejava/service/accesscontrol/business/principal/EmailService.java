package org.dejava.service.accesscontrol.business.principal;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.javaee.dao.AbstractGenericDAO;
import org.dejava.component.javaee.service.AbstractGenericService;
import org.dejava.service.accesscontrol.dao.principal.EmailDAO;
import org.dejava.service.accesscontrol.model.principal.Email;
import org.dejava.service.accesscontrol.util.AccessControl;

/**
 * EJB service for email.
 */
@AccessControl
@Stateless(name = "Service/AccessControl/EmailService")
public class EmailService extends AbstractGenericService<Email, Integer> {

	/**
	 * The email DAO.
	 */
	@Inject
	@AccessControl
	private EmailDAO emailDAO;

	/**
	 * @see org.dejava.component.javaee.service.AbstractGenericService#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<Email, Integer> getEntityDAO() {
		return emailDAO;
	}

}
