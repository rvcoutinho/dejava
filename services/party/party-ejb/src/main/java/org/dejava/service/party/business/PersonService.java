package org.dejava.service.party.business;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.service.party.dao.PersonDAO;

/**
 * TODO
 */
@Stateless
public class PersonService {
	
	/**
	 * TODO
	 */
	@Inject
	private PersonDAO personDAO;

}
