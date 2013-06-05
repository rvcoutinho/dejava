package org.dejava.service.accesscontrol.component.crendentials;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.accesscontrol.dao.credentials.CredentialDAO;
import org.dejava.service.accesscontrol.model.credentials.Credentials;
import org.dejava.service.accesscontrol.util.AccessControl;

/**
 * EJB service for credential.
 */
@AccessControl
@Stateless(name = "Component/AccessControl/Credential")
public class CredentialComponent extends AbstractGenericComponent<Credentials, Integer> {

	/**
	 * The credential DAO.
	 */
	@Inject
	@AccessControl
	private CredentialDAO credentialDAO;

	/**
	 * @see org.dejava.component.ejb.component.AbstractGenericComponent#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<Credentials, Integer> getEntityDAO() {
		return credentialDAO;
	}

}
