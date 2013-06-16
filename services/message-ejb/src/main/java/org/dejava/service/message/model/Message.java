package org.dejava.service.message.model;

import javax.persistence.MappedSuperclass;

import org.dejava.component.ejb.entity.AbstractIdentifiedEntity;

/**
 * Represents a message.
 */
@MappedSuperclass
public abstract class Message extends AbstractIdentifiedEntity {

}
