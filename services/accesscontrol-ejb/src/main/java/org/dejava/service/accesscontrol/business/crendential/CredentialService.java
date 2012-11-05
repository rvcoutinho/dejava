package org.dejava.service.accesscontrol.business.crendential;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.javaee.dao.AbstractGenericDAO;
import org.dejava.component.javaee.service.AbstractGenericService;
import org.dejava.service.accesscontrol.dao.credential.CredentialDAO;
import org.dejava.service.accesscontrol.model.credential.Credential;

/**
 * EJB service for credential.
 */
@Remote
@Stateless(name = "AccessControl/CredentialService/remote")
public class CredentialService extends AbstractGenericService<Credential, Integer> {

	/**
	 * The credential DAO.
	 */
	@Inject
	private CredentialDAO credentialDAO;

	/**
	 * @see org.dejava.component.javaee.service.AbstractGenericService#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<Credential, Integer> getEntityDAO() {
		return credentialDAO;
	}

}
