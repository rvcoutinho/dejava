package org.dejava.service.philanthropy.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Philanthropy idea plan.
 */
@Entity
@Table(name = "idea_plan")
public class IdeaPlan extends Idea implements Serializable {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 4361247334778667346L;

	/**
	 * The estimated cost for the idea.
	 */
	private BigDecimal estimatedCost;

	/**
	 * Gets the estimated cost for the idea.
	 * 
	 * @return The estimated cost for the idea.
	 */
	@Column(name = "estimated_cost")
	public BigDecimal getEstimatedCost() {
		return estimatedCost;
	}

	/**
	 * Sets the estimated cost for the idea.
	 * 
	 * @param estimatedCost
	 *            New estimated cost for the idea.
	 */
	public void setEstimatedCost(final BigDecimal estimatedCost) {
		this.estimatedCost = estimatedCost;
	}

}
