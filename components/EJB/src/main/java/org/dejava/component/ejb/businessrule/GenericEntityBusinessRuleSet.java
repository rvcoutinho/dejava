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
	 * @param context
	 *            Context from within the entity should be validated.
	 */
	public void validate(Entity entity, Object... context);

	/**
	 * Validates a collection of entities in a given context.
	 * 
	 * @param entities
	 *            Entities to be validated.
	 * @param context
	 *            Context from within the entity should be validated.
	 */
	public void validate(Collection<Entity> entities, Object... context);

}
