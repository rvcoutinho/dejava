package org.dejava.service.photo.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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

	/**
	 * Gets all photos by album.
	 * 
	 * @param name
	 *            The album name.
	 * @param owner
	 *            The album owner.
	 * @param firstResult
	 *            The first result that should be considered.
	 * @param maxResults
	 *            The maximum numbers of results to be considered.
	 * @return Photos by album.
	 */
	public List<Photo> getByAlbum(final String name, final Integer owner, final Integer firstResult,
			final Integer maxResults) {
		// Gets the named query for the method.
		final TypedQuery<Photo> getPhotosByAlbum = getEntityManager().createNamedQuery("getPhotosByAlbum",
				Photo.class);
		// Sets album info to the named query.
		getPhotosByAlbum.setParameter("albumName", name);
		getPhotosByAlbum.setParameter("albumOwner", owner);
		// Limits the query results.
		limitResultList(getPhotosByAlbum, firstResult, maxResults);
		// Returns the result for the query.
		return getPhotosByAlbum.getResultList();
	}

}
