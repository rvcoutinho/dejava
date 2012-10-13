package org.dejava.service.accesscontrol.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;

/**
 * Represents a person.
 */
@Entity
@Table(name = "user")
@MessageSources(sources = { @MessageSource(bundleBaseName = "org.dejava.service.accesscontrol.properties.model", processSuperclasses = true, processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }) })
public class User implements Serializable {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -2762159417756393897L;
}
