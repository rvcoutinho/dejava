package org.dejava.service.accesscontrol.business.crendentials;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.javaee.dao.AbstractGenericDAO;
import org.dejava.component.javaee.service.AbstractGenericService;
import org.dejava.service.accesscontrol.dao.credentials.CredentialDAO;
import org.dejava.service.accesscontrol.model.credentials.Credentials;

/**
 * EJB service for credential.
 */
@Remote
@Stateless(name = "AccessControl/CredentialService/remote")
public class CredentialService extends AbstractGenericService<Credentials, Integer> {

	/**
	 * The credential DAO.
	 */
	@Inject
	private CredentialDAO credentialDAO;

	/**
	 * @see org.dejava.component.javaee.service.AbstractGenericService#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<Credentials, Integer> getEntityDAO() {
		return credentialDAO;
	}

}
