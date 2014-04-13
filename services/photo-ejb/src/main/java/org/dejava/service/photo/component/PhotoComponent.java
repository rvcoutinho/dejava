package org.dejava.service.photo.component;

import static org.dejava.properties.util.FacebookAPIProps.API_PROPERTIES;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.shiro.SecurityUtils;
import org.dejava.component.exception.localized.unchecked.InvalidParameterException;
import org.dejava.properties.constant.FacebookAPIKeys;
import org.dejava.service.photo.businessrule.PhotoBusinessRuleSet;
import org.dejava.service.photo.constant.PhotoAlbums;
import org.dejava.service.photo.dao.PhotoDAO;
import org.dejava.service.photo.model.Photo;
import org.dejava.service.photo.util.MessageTypes;
import org.dejava.service.photo.util.PhotoCtx;

/**
 * EJB component for photo.
 */
@PhotoCtx
@Stateless(name = "Component/Photo/Photo")
public class PhotoComponent {

	/**
	 * The photo DAO.
	 */
	@Inject
	@PhotoCtx
	private PhotoDAO photoDAO;

	/**
	 * The application message business rule set.
	 */
	@Inject
	@PhotoCtx
	private PhotoBusinessRuleSet photoBusinessRuleSet;

	/**
	 * Creates a new photo.
	 * 
	 * @param photo
	 *            The photo to be created.
	 * 
	 * @return The created photo.
	 */
	public Photo createPhoto(final Photo photo) {
		// Validates the photo to be added.
		photoBusinessRuleSet.validate(photo);
		// Adds the new photo.
		return photoDAO.persist(photo);
	}

	/**
	 * Gets the photo by its identifier.
	 * 
	 * @param identifier
	 *            The photo identifier.
	 * @return The photo by its identifier.
	 */
	public Photo getPhotoById(final Integer identifier) {
		return photoDAO.getById(identifier);
	}

	/**
	 * Gets the raw photo by its identifier.
	 * 
	 * @param identifier
	 *            The photo identifier.
	 * @return The raw photo by its identifier.
	 */
	public byte[] getRawPhotoById(final Integer identifier) {
		return photoDAO.getById(identifier).getContent();
	}

	/**
	 * Gets the avatar of an owner.
	 * 
	 * @param owner
	 *            The owner identifier.
	 * @return The avatar of an owner.
	 */
	public Photo getAvatarByOwner(final Integer owner) {
		// The owner avatar is null by default.
		Photo ownerAvatar = null;
		// Gets the first photo in the owner avatar album.
		final List<Photo> avatarAlbum = photoDAO.getByAlbum(PhotoAlbums.AVATAR_ALBUM, owner, 0, 1);
		// If there are any photos in the album.
		if ((avatarAlbum != null) && (!avatarAlbum.isEmpty())) {
			// The owner avatar is the first photo.
			ownerAvatar = avatarAlbum.get(0);
		}
		// Returns the current avatar of the owner.
		return ownerAvatar;
	}

	/**
	 * Gets the raw avatar of an owner.
	 * 
	 * @param owner
	 *            The owner identifier.
	 * @return The raw avatar of an owner.
	 */
	public byte[] getRawAvatarByOwner(final Integer owner) {
		// The default avatar content is null. FIXME
		byte[] avatarContent = null;
		// Gets the owner avatar.
		final Photo avatar = getAvatarByOwner(owner);
		// If the retrieved avatar is not null.
		if (avatar != null) {
			// Updates the avatar identifier with the retrieved content.
			avatarContent = avatar.getContent();
		}
		// Returns the avatar content.
		return avatarContent;
	}

	/**
	 * Gets the facebook user avatar content.
	 * 
	 * @return The facebook user avatar content.
	 */
	public byte[] getFacebookUserAvatar() {
		// The URL for facebook user avatar.
		URL fbUserAvatarURL = null;
		// Tries to get the user avatar content. TODO Check for access token
		try {
			// Gets the URL for facebook user avatar.
			fbUserAvatarURL = new URL(API_PROPERTIES.getProperty(FacebookAPIKeys.APP_USER_AVATAR_URL)
					+ "?"
					+ API_PROPERTIES.getProperty(FacebookAPIKeys.APP_AVATAR_HEIGHT_PARAM)
					+ "="
					+ API_PROPERTIES.getProperty(FacebookAPIKeys.APP_DEFAULT_AVATAR_HEIGHT)
					+ "&"
					+ API_PROPERTIES.getProperty(FacebookAPIKeys.APP_ACCESS_TOKEN_PARAM)
					+ "="
					+ SecurityUtils.getSubject().getSession()
							.getAttribute(API_PROPERTIES.getProperty(FacebookAPIKeys.APP_ACCESS_TOKEN_PARAM)));
			// Creates a facebook user avatar stream.
			final ByteArrayOutputStream userAvatarOutputStream = new ByteArrayOutputStream();
			// Gets the facebook user avatar stream.
			final InputStream userAvatarInputStream = fbUserAvatarURL.openStream();
			// Creates a buffer for the stream.
			final byte[] streamBuffer = new byte[4096];
			// Until the input stream is completely read.
			for (Integer streamBufferLength = userAvatarInputStream.read(streamBuffer); streamBufferLength > 0; streamBufferLength = userAvatarInputStream
					.read(streamBuffer)) {
				// Writes the buffer to the output stream.
				userAvatarOutputStream.write(streamBuffer, 0, streamBufferLength);
			}
			// Returns the avatar content.
			return userAvatarOutputStream.toByteArray();
		}
		// If there is a problem to get the avatar content.
		catch (final IOException exception) {
			// Throws an invalid parameter exception. FIXME
			throw new InvalidParameterException(MessageTypes.Error.class, "",
					new Object[] { fbUserAvatarURL }, exception);
		}
	}

}
