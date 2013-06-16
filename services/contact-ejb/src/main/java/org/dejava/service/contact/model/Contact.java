package org.dejava.service.contact.model;

import javax.persistence.MappedSuperclass;

import org.dejava.component.ejb.entity.AbstractIdentifiedEntity;

/**
 * Represents a contact.
 */
@MappedSuperclass
public abstract class Contact extends AbstractIdentifiedEntity {

	/**
	 * Generated serial
	 */
	private static final long serialVersionUID = 8317664521891805070L;

}
