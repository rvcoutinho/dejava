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
	 * The default identifier sufix.
	 */
	private static final String ID_SUFIX = "Id";

	/**
	 * The default identifiers sufix.
	 */
	private static final String IDS_SUFIX = "Ids";

	/**
	 * The default identifier getter for entities.
	 */
	private static final String ENTITY_ID_GETTER = "getIdentifier";

	/**
	 * The direct retrieve method name (if the external entity is mapped directly).
	 */
	public static final String DIR_RET_METHOD = "getById";

	/**
	 * The reverse retrieve method name (if the external entity maps the relationship).
	 */
	public static final String REV_RET_METHOD = "getByAttribute";

	/**
	 * The direct retrieve method parameters classes (if {@link ExternalEntity#mappedBy()} is empty).
	 */
	public static final Class<?>[] DIR_RET_METHOD_PARAMS_CLASSES = { Integer.class };

	/**
	 * The reverse retrieve method parameters classes (if {@link ExternalEntity#mappedBy()} is not empty) for a single
	 * external entity.
	 */
	public static final Class<?>[] REV_SINGLE_RET_METHOD_PARAMS_CLASSES = { String.class, Object.class };

	/**
	 * The reverse retrieve method parameters classes (if {@link ExternalEntity#mappedBy()} is not empty) for multiple
	 * external entities.
	 */
	public static final Class<?>[] REV_MULTI_RET_METHOD_PARAMS_CLASSES = { String.class, Object.class,
			Integer.class, Integer.class };

	/**
	 * Gets the parameters values for the method used to retrieve the external entities.
	 * 
	 * @param entity
	 *            The entity whose external entities are to be retrieved within.
	 * @param externalEntityInfo
	 *            The external entity annotation.
	 * @param fieldName
	 *            The entity field whose external entities are to be retrieved within.
	 * @return The parameters values for the method used to retrieve the external entities.
	 */
	private static Object getParamsValues(final Object entity, final ExternalEntity externalEntityInfo,
			final String fieldName) {
		// By default, there are no parameters values.
		Object paramsValues = null;
		// Gets the class of the entity.
		final ClassMirror<?> entityClass = new ClassMirror<>(entity.getClass());
		// Gets the given parameters values retrieve method.
		final String paramValuesMethod = externalEntityInfo.paramsValuesMethod();
		// If no method is given.
		if (paramValuesMethod.isEmpty()) {
			// If the external entity is mapped directly.
			if (externalEntityInfo.mappedBy().isEmpty()) {
				// If the mapping is for a single external entity.
				if (externalEntityInfo.singleEntity()) {
					// Gets the parameter values from a method named <annotatedFieldGetter>Id.
					paramsValues = entityClass.getMethod(FieldMirror.getGetterName(fieldName) + ID_SUFIX,
							null).invokeMethod(entity, true, null);
				}
				// If the mapping is for multiple external entities.
				else {
					// Gets the parameter values from a method named <annotatedFieldGetter>Ids.
					paramsValues = entityClass.getMethod(FieldMirror.getGetterName(fieldName) + IDS_SUFIX,
							null).invokeMethod(entity, true, null);
				}
			}
			// If the external entity is reverse mapped.
			else {
				// If the mapping is for a single external entity.
				if (externalEntityInfo.singleEntity()) {
					// The parameters values are the mapped field name and the entity id (and null start and
					// offset parameters).
					paramsValues = new Object[] { externalEntityInfo.mappedBy(),
							entityClass.getMethod(ENTITY_ID_GETTER, null).invokeMethod(entity, true, null) };
				}
				// If the mapping is for multiple external entities.
				else {
					// The parameters values are the mapped field name and the entity id.
					paramsValues = new Object[] { externalEntityInfo.mappedBy(),
							entityClass.getMethod(ENTITY_ID_GETTER, null).invokeMethod(entity, true, null),
							null, null };
				}
			}
		}
		// If the method is given.
		else {
			// The parameters are retrieved using the given method name.
			paramsValues = entityClass.getMethod(paramValuesMethod, null).invokeMethod(entity, true, null);
		}
		// Returns the parameters values.
		return paramsValues;
	}

	/**
	 * Gets the retrieve method name from an external entity annotation.
	 * 
	 * @param externalEntityInfo
	 *            The external entity annotation.
	 * @return The retrieve method name from an external entity annotation.
	 */
	private static String getRetrieveMethodName(final ExternalEntity externalEntityInfo) {
		// Gets the given method name.
		String methodName = externalEntityInfo.retrieveMethod();
		// If the method name is not given.
		if (methodName.isEmpty()) {
			// If the external entity is mapped directly.
			if (externalEntityInfo.mappedBy().isEmpty()) {
				// The default method name for the context is used.
				methodName = DIR_RET_METHOD;
			}
			// If the external entity is reverse mapped.
			else {
				// The default method name for the context is used.
				methodName = REV_RET_METHOD;
			}
		}
		// Returns the method name.
		return methodName;
	}

	/**
	 * Gets the parameters classes for the retrieve method from an external entity annotation.
	 * 
	 * @param externalEntityInfo
	 *            The external entity annotation.
	 * @return The parameters classes for the retrieve method from an external entity annotation.
	 */
	private static Class<?>[] getRetrieveMethodParamsClasses(final ExternalEntity externalEntityInfo) {
		// Gets the given parameters classes.
		Class<?>[] paramsClasses = externalEntityInfo.retrieveMethodParamsClasses();
		// If the parameters classes are not given via the annotation.
		if (paramsClasses.length == 0) {
			// If the external entity is mapped directly.
			if (externalEntityInfo.mappedBy().isEmpty()) {
				// The default parameters classes for the context are used.
				paramsClasses = DIR_RET_METHOD_PARAMS_CLASSES;
			}
			// If the external entity is reverse mapped.
			else {
				// If the mapping is for a single entity.
				if (externalEntityInfo.singleEntity()) {
					// The default parameters classes for the context are used.
					paramsClasses = REV_SINGLE_RET_METHOD_PARAMS_CLASSES;
				}
				// If the mapping is for a multiple entities.
				else {
					// The default parameters classes for the context are used.
					paramsClasses = REV_MULTI_RET_METHOD_PARAMS_CLASSES;
				}
			}
		}
		// Returns the parameters classes.
		return paramsClasses;
	}

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
	 * @param ignoreLazyLoading
	 *            If the lazy loading attribute for the annotation must be ignored.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void loadAllExternalEntities(final Object entity, final Boolean ignoreLazyLoading) {
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
			// If lazy loading should be ignored or if the external entity should not be lazy loaded.
			if ((ignoreLazyLoading) || (!externalEntityInfo.lazyLoading())) {
				// Gets the parameters values to retrieve the external entity from the given entity.
				Object paramsValues = getParamsValues(entity, externalEntityInfo, currentField
						.getReflectedField().getName());
				// If there is any entity id.
				if (paramsValues != null) {
					// If the there is a single entity to be retrieved (single id) or if the external entity
					// maps the relationship.
					if ((externalEntityInfo.singleEntity()) || (!externalEntityInfo.mappedBy().isEmpty())) {
						// If the parameters values are not mapped as an array.
						if (!(paramsValues instanceof Object[])) {
							// Put the parameters in a array.
							paramsValues = new Object[] { paramsValues };
						}
						// Gets the external entity.
						final Object externalEntity = getExternalEntity(externalEntityInfo.retrieveObj(),
								getRetrieveMethodName(externalEntityInfo),
								getRetrieveMethodParamsClasses(externalEntityInfo), (Object[]) paramsValues);
						// Sets the external entity value.
						currentField.setValue(entity, externalEntity, externalEntityInfo.fieldAccess(), true);
					}
					// If the field is not a collection (or array).
					else {
						// Gets the external entities default collection implementation.
						final Collection externalEntities = (Collection) currentField.getValue(entity, false,
								true);
						// For each external entity id.
						for (final Object curExtEntityParamsValues : (Collection<?>) paramsValues) {
							// Adds the external entity to collection.
							externalEntities.add(getExternalEntity(externalEntityInfo.retrieveObj(),
									getRetrieveMethodName(externalEntityInfo),
									getRetrieveMethodParamsClasses(externalEntityInfo),
									new Object[] { curExtEntityParamsValues }));
						}
						// Sets the external entities values.
						currentField.setValue(entity, externalEntities, externalEntityInfo.fieldAccess(),
								true);
					}
				}
			}
		}
	}

	/**
	 * Update the external entities with the actual mapping information (when relationship is mapped by the
	 * external entity).
	 * 
	 * @param entity
	 *            To entity to have the external entities mapping updated.
	 */
	public static void updateReverseMappedEntities(final Object entity) {
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
			// If the current external entity maps the relationship.
			if (!externalEntityInfo.mappedBy().isEmpty()) {
				// Gets the external entities for the current field.
				final Object externalEntities = currentField.getValue(entity, false, true);
				// If the field value is not null.
				if (externalEntities != null) {
					// Gets the identifier for the given entity.
					final Object entityId = entityClass.getMethod(ENTITY_ID_GETTER, null).invokeMethod(
							entity, true, null);
					// If the field represents a single external entity.
					if (externalEntityInfo.singleEntity()) {
						// Gets the external entity class.
						final ClassMirror<?> extEntityClass = new ClassMirror<>(externalEntities.getClass());
						// Gets the field that maps the relationship.
						final FieldMirror extEntityMappedField = extEntityClass.getField(externalEntityInfo
								.mappedBy());
						// Sets the field value with the id for the given entity.
						extEntityMappedField.setValue(externalEntities, entityId, false, true);
					}
					// If the field represents multiple external entities.
					else {
						// For each external entity.
						for (final Object currentExtEntity : (Collection<?>) externalEntities) {
							// Gets the external entity class.
							final ClassMirror<?> extEntityClass = new ClassMirror<>(
									currentExtEntity.getClass());
							// Gets the field that maps the relationship.
							final FieldMirror extEntityMappedField = extEntityClass
									.getField(externalEntityInfo.mappedBy());
							// Sets the field value with the id for the given entity.
							extEntityMappedField.setValue(currentExtEntity, entityId, false, true);
						}
					}
				}
			}
		}
	}
}
