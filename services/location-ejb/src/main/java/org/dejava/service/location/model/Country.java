package org.dejava.service.location.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;

/**
 * Country.
 */
@Entity
@Table(name = "country")
@MessageSources(sources = { @MessageSource(bundleBaseName = "org.dejava.service.location.properties.model", processSuperclasses = true, processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }) })
public class Country implements Serializable {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -4538161507269185472L;

	/**
	 * Id for the party.
	 */
	private Integer id;

	/**
	 * Gets the id for the party.
	 * 
	 * @return The id for the party.
	 */
	@Id
	@Column(name = "id")
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id for the party.
	 * 
	 * @param id
	 *            New id for the party.
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * Two letter country code.
	 */
	private String alpha2;

	/**
	 * Gets the alpha2.
	 * 
	 * @return The alpha2.
	 */
	@Column(name = "alpha2", length = 2)
	public String getAlpha2() {
		return alpha2;
	}

	/**
	 * Sets the alpha2.
	 * 
	 * @param alpha2
	 *            New alpha2.
	 */
	public void setAlpha2(final String alpha2) {
		this.alpha2 = alpha2;
	}

	/**
	 * Three letter country code.
	 */
	private String alpha3;

	/**
	 * Gets the alpha3.
	 * 
	 * @return The alpha3.
	 */
	@Column(name = "alpha3", length = 3)
	public String getAlpha3() {
		return alpha3;
	}

	/**
	 * Sets the alpha3.
	 * 
	 * @param alpha3
	 *            New alpha3.
	 */
	public void setAlpha3(final String alpha3) {
		this.alpha3 = alpha3;
	}

	/**
	 * Name of the country.
	 */
	private String name;

	/**
	 * Gets the name of the country.
	 * 
	 * @return The name of the country.
	 */
	@Column(name = "name")
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the country.
	 * 
	 * @param name
	 *            New name of the country.
	 */
	public void setName(final String name) {
		this.name = name;
	}
}