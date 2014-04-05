package org.dejava.service.photo.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.photo.model.Photo;
import org.dejava.service.photo.util.PhotoCtx;

/**
 * DAO for the photo.
 */
@PhotoCtx
public class PhotoDAO extends AbstractGenericDAO<Photo, Integer> {

	/**
	 * Entity manager being used.
	 */
	@Inject
	@PhotoCtx
	private EntityManager entityManager;

	/**
	 * @see org.dejava.component.ejb.dao.AbstractGenericDAO#getEntityManager()
	 */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
