package org.dejava.service.accesscontrol.business.principal;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.javaee.dao.AbstractGenericDAO;
import org.dejava.component.javaee.service.AbstractGenericService;
import org.dejava.service.accesscontrol.dao.principal.EmailDAO;
import org.dejava.service.accesscontrol.model.principal.Email;

/**
 * EJB service for email.
 */
@Remote
@Stateless(name = "AccessControl/EmailService/remote")
public class EmailService extends AbstractGenericService<Email, Integer> {

	/**
	 * The email DAO.
	 */
	@Inject
	private EmailDAO emailDAO;

	/**
	 * @see org.dejava.component.javaee.service.AbstractGenericService#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<Email, Integer> getEntityDAO() {
		return emailDAO;
	}

}
