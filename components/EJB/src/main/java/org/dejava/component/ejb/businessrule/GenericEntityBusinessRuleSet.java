package org.dejava.component.ejb.businessrule;

import java.util.Collection;

/**
 * A generic entity business rule set.
 * 
 * @param <Entity>
 *            Any entity.
 */
public interface GenericEntityBusinessRuleSet<Entity> {

	/**
	 * Validates an entity in a given context.
	 * 
	 * @param entity
	 *            Entity to be validated.
	 * @param groups
	 *            Groups that should be considered in the validation.
	 */
	public void validate(Entity entity, Class<?>... groups);

	/**
	 * Validates a collection of entities in a given context.
	 * 
	 * @param entities
	 *            Entities to be validated.
	 * @param groups
	 *            Groups that should be considered in the validation.
	 */
	public void validate(Collection<Entity> entities, Class<?>... groups);

}
