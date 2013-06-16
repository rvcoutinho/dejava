package org.dejava.component.ejb.entity;

import java.util.Collection;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.dejava.component.ejb.constant.ErrorKeys;
import org.dejava.component.ejb.constant.ExternalEntityLoaderParamKeys;
import org.dejava.component.exception.localized.unchecked.InvalidParameterException;
import org.dejava.component.reflection.ClassMirror;
import org.dejava.component.reflection.FieldMirror;
import org.dejava.component.reflection.MethodMirror;
import org.dejava.component.reflection.util.MessageTypes;
import org.dejava.component.validation.method.PreConditions;

/**
 * Helps loading entities from external systems into local entities.
 */
public class ExternalEntityLoader {

	/**
	 * Gets an external entity.
	 * 
	 * @param retrieveObjectName
	 *            The JNDI name of the object to be used in order to retrieve the external entity.
	 * @param retrieveMethodName
	 *            The name of the method to be used in order to retrieve the external entity.
	 * @param paramsClasses
	 *            The parameters values for the retrieve method.
	 * @param paramsValues
	 *            The parameters values for the retrieve method.
	 * @return The external entity.
	 */
	public static Object getExternalEntity(final String retrieveObjectName, final String retrieveMethodName,
			final Class<?>[] paramsClasses, final Object[] paramsValues) {
		// Asserts that the retrieve object and method names are not null.
		PreConditions.assertParamNotNull(ExternalEntityLoaderParamKeys.JNDI_RETRIEVE_OBJ_NAME,
				retrieveObjectName);
		PreConditions.assertParamNotNull(ExternalEntityLoaderParamKeys.RETRIEVE_METHOD_NAME,
				retrieveMethodName);
		// Tries to get the JNDI object to retrieve the external entity.
		try {
			final Object entityRetrieveObject = InitialContext.doLookup(retrieveObjectName);
			// Gets the class for the retrieve object.
			final ClassMirror<Object> retrieveObjectClass = new ClassMirror<>(entityRetrieveObject.getClass());
			// Gets the retrieve method from the class.
			final MethodMirror retrieveObjectMethod = retrieveObjectClass.getMethod(retrieveMethodName,
					paramsClasses);
			// Invokes the retrieve method and returns the external entity.
			return retrieveObjectMethod.invokeMethod(entityRetrieveObject, false, paramsValues);
		}
		// If no object is found for the given JNDI name.
		catch (final NamingException exception) {
			// Throws an exception.
			throw new InvalidParameterException(MessageTypes.Error.class,
					ErrorKeys.INVALID_JNDI_RETRIEVE_OBJ_NAME, new Object[] { retrieveObjectName }, exception);
		}

	}

	/**
	 * Loads all entities within the given entity.
	 * 
	 * @param entity
	 *            The entity to get the external entities (fields) loaded.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void loadAllExternalEntities(final Object entity) {
		// Asserts that the entity is not null.
		PreConditions.assertParamNotNull(ExternalEntityLoaderParamKeys.ENTITY, entity);
		// Gets the class of the entity.
		final ClassMirror<?> entityClass = new ClassMirror<>(entity.getClass());
		// Gets the fields represented by external entities.
		final Collection<FieldMirror> extEntitiesFields = entityClass.getFields(ExternalEntity.class);
		// For each external entity.
		for (final FieldMirror currentField : extEntitiesFields) {
			// Gets the external entity annotation for the current field.
			final ExternalEntity externalEntityInfo = currentField.getReflectedField().getAnnotation(
					ExternalEntity.class);
			// Gets the parameters values to retrieve the external entity from the given entity.
			final Object paramsValues = entityClass.getMethod(externalEntityInfo.idsMethod(), null)
					.invokeMethod(entity, true, null);
			// If there is any entity id.
			if (paramsValues != null) {
				// If the field is a collection.
				if (Collection.class.isAssignableFrom(currentField.getReflectedField().getType())) {
					// Gets the external entities default collection implementation.
					final Collection externalEntities = (Collection) currentField.getValue(entity, false,
							true);
					// For each external entity id.
					for (final Object curExtEntityParamsValues : (Collection<?>) paramsValues) {
						// Adds the external entity to collection.
						externalEntities.add(getExternalEntity(externalEntityInfo.retrieveObj(),
								externalEntityInfo.retrieveMethod(),
								externalEntityInfo.retrieveMethodParamsClasses(),
								new Object[] { curExtEntityParamsValues }));
					}
					// Sets the external entities values.
					currentField.setValue(entity, externalEntities, externalEntityInfo.fieldAccess(), true);
				}
				// If the field is not a collection (or array).
				else {
					// Gets the external entity.
					final Object externalEntity = getExternalEntity(externalEntityInfo.retrieveObj(),
							externalEntityInfo.retrieveMethod(),
							externalEntityInfo.retrieveMethodParamsClasses(), new Object[] { paramsValues });
					// Sets the external entity value.
					currentField.setValue(entity, externalEntity, externalEntityInfo.fieldAccess(), true);
				}
			}
		}
	}
}
