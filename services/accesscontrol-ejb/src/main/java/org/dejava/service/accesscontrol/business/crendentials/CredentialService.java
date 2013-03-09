package org.dejava.service.accesscontrol.business.crendentials;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.javaee.dao.AbstractGenericDAO;
import org.dejava.component.javaee.service.AbstractGenericService;
import org.dejava.service.accesscontrol.dao.credentials.CredentialDAO;
import org.dejava.service.accesscontrol.model.credentials.Credentials;
import org.dejava.service.accesscontrol.util.AccessControl;

/**
 * EJB service for credential.
 */
@AccessControl
@Stateless(name = "Service/AccessControl/CredentialService")
public class CredentialService extends AbstractGenericService<Credentials, Integer> {

	/**
	 * The credential DAO.
	 */
	@Inject
	@AccessControl
	private CredentialDAO credentialDAO;

	/**
	 * @see org.dejava.component.javaee.service.AbstractGenericService#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<Credentials, Integer> getEntityDAO() {
		return credentialDAO;
	}

}
