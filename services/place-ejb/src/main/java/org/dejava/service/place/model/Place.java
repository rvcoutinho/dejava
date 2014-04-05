package org.dejava.service.place.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.dejava.component.ejb.entity.AbstractIdentifiedEntity;
import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;
import org.dejava.component.reflection.ClassMirror;
import org.dejava.service.place.util.MessageTypes;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Some place.
 */
@Entity
@Table(name = "place")
@Inheritance(strategy = InheritanceType.JOINED)
@MessageSources(sources = {
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.place.properties.model", entriesAffix = { "", ".description" }, processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }),
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.place.properties.error", processors = { "org.dejava.component.i18n.source.processor.impl.GetterConstraintEntryProcessor" }) })
public class Place extends AbstractIdentifiedEntity {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -9140818548305278163L;

	/**
	 * Google reference for the place.
	 */
	private String reference;

	/**
	 * Gets the google reference for the place.
	 * 
	 * @return The google reference for the place.
	 */
	@Column(name = "reference")
	public String getReference() {
		return reference;
	}

	/**
	 * Sets the google reference for the place.
	 * 
	 * @param reference
	 *            New google reference for the place.
	 */
	public void setReference(final String reference) {
		this.reference = reference;
	}

	/**
	 * Name of the place.
	 */
	private String name;

	/**
	 * Gets the name of the place.
	 * 
	 * @return The name of the place.
	 */
	@Column(name = "name")
	@NotNull(payload = MessageTypes.Error.class, message = "place.name.notnull")
	@NotEmpty(payload = MessageTypes.Error.class, message = "place.name.notempty")
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the place.
	 * 
	 * @param name
	 *            New name of the place.
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Short name of the place.
	 */
	private String shortName;

	/**
	 * Gets the short name of the place.
	 * 
	 * @return The short name of the place.
	 */
	@Column(name = "short_name")
	public String getShortName() {
		// If there is no short name.
		if (shortName == null) {
			// The short name is the regular name.
			shortName = getName();
		}
		// Returns the short name.
		return shortName;
	}

	/**
	 * Sets the short name of the place.
	 * 
	 * @param shortName
	 *            New short name of the place.
	 */
	public void setShortName(final String shortName) {
		this.shortName = shortName;
	}

	/**
	 * Types for the place.
	 */
	private Collection<String> types;

	/**
	 * Gets the types for the place.
	 * 
	 * @return The types for the place.
	 */
	@ElementCollection
	@CollectionTable(name = "type", joinColumns = @JoinColumn(name = "place"))
	@Column(name = "type")
	public Collection<String> getTypes() {
		// If the collection is null.
		if (types == null) {
			// Creates a new list.
			types = new ArrayList<>();
		}
		// Returns the types.
		return types;
	}

	/**
	 * Sets the types for the place.
	 * 
	 * @param types
	 *            New types for the place.
	 */
	public void setTypes(final Collection<String> types) {
		this.types = types;
	}

	/**
	 * Parent place for the place.
	 */
	private Place parentPlace;

	/**
	 * Gets the parent place for the place.
	 * 
	 * @return The parent place for the place.
	 */
	@ManyToOne
	@JoinColumn(name = "parent_place")
	public Place getParentPlace() {
		return parentPlace;
	}

	/**
	 * Sets the parent place for the place.
	 * 
	 * @param parentPlace
	 *            New parent place for the place.
	 */
	public void setParentPlace(final Place parentPlace) {
		this.parentPlace = parentPlace;
	}

	/**
	 * Empty constructor.
	 */
	public Place() {
		super();
	}

	/**
	 * Default constructor.
	 * 
	 * @param reference
	 *            Google reference for the place.
	 * @param name
	 *            Name of the place.
	 * @param shortName
	 *            Short name of the place.
	 * @param types
	 *            Types for the place.
	 * @param parentPlace
	 *            Parent place for the place.
	 */
	public Place(final String reference, final String name, final String shortName,
			final Collection<String> types, final Place parentPlace) {
		super();
		this.reference = reference;
		this.name = name;
		this.shortName = shortName;
		this.parentPlace = parentPlace;
	}

	/**
	 * Creates a new place for the given type.
	 * 
	 * @param type
	 *            The place type to be created.
	 * @param reference
	 *            Google reference for the place.
	 * @param name
	 *            Name of the place.
	 * @param shortName
	 *            Short name of the place.
	 * @param parentPlace
	 *            Parent place for the place.
	 * @param <ConcretePlace>
	 *            A concrete place type.
	 * @return The created place for the type.
	 */
	private static <ConcretePlace extends Place> Place newPlace(final Class<ConcretePlace> type,
			final String reference, final String name, final String shortName, final Place parentPlace) {
		// Crates a new place for the type.
		final ConcretePlace newPlace = new ClassMirror<>(type).getConstructor(null).newInstance(null, true);
		// Sets the attributes.
		newPlace.setName(name);
		newPlace.setShortName(shortName);
		newPlace.setReference(reference);
		newPlace.setParentPlace(parentPlace);
		// Returns the new place.
		return newPlace;
	}

	/**
	 * Gets the complete short name for the place (including all parent places short names).
	 * 
	 * @return The complete short name for the place (including all parent places short names).
	 */
	@Transient
	public String getCompleteShortName() {
		// The complete name starts with the places name.
		final StringBuffer completeName = new StringBuffer(getShortName());
		// For each parent place.
		for (Place curParentPlace = getParentPlace(); curParentPlace != null; curParentPlace = curParentPlace
				.getParentPlace()) {
			// Adds the name for the current parent place to the complete name.
			completeName.append("," + curParentPlace.getShortName());
		}
		// Returns the complete name for the place.
		return completeName.toString();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Transient
	@Override
	public String toString() {
		// Returns the complete short name for the place.
		return getCompleteShortName();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((name == null) ? 0 : name.hashCode());
		result = (prime * result) + ((parentPlace == null) ? 0 : parentPlace.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Place other = (Place) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (parentPlace == null) {
			if (other.parentPlace != null) {
				return false;
			}
		} else if (!parentPlace.equals(other.parentPlace)) {
			return false;
		}
		return true;
	}

}
