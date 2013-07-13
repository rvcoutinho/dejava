package org.dejava.component.validation.object;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.Payload;
import javax.validation.metadata.ConstraintDescriptor;

/**
 * Decorator for the constraint descriptor.
 * 
 * @param <AnyAnnotation>
 *            Any constraint annotation.
 */
public class ConstraintDescriptorImpl<AnyAnnotation extends Annotation> implements
		ConstraintDescriptor<AnyAnnotation> {

	/**
	 * Wrapped constraint descriptor.
	 */
	private final ConstraintDescriptor<AnyAnnotation> decoratedConstraintDescriptor;

	/**
	 * @see javax.validation.metadata.ConstraintDescriptor#getAnnotation()
	 */
	@Override
	public AnyAnnotation getAnnotation() {
		return decoratedConstraintDescriptor.getAnnotation();
	}

	/**
	 * @see javax.validation.metadata.ConstraintDescriptor#getGroups()
	 */
	@Override
	public Set<Class<?>> getGroups() {
		return decoratedConstraintDescriptor.getGroups();
	}

	/**
	 * @see javax.validation.metadata.ConstraintDescriptor#getPayload()
	 */
	@Override
	public Set<Class<? extends Payload>> getPayload() {
		return decoratedConstraintDescriptor.getPayload();
	}

	/**
	 * @see javax.validation.metadata.ConstraintDescriptor#getConstraintValidatorClasses()
	 */
	@Override
	public List<Class<? extends ConstraintValidator<AnyAnnotation, ?>>> getConstraintValidatorClasses() {
		return decoratedConstraintDescriptor.getConstraintValidatorClasses();
	}

	/**
	 * @see javax.validation.metadata.ConstraintDescriptor#getAttributes()
	 */
	@Override
	public Map<String, Object> getAttributes() {
		return decoratedConstraintDescriptor.getAttributes();
	}

	/**
	 * @see javax.validation.metadata.ConstraintDescriptor#getComposingConstraints()
	 */
	@Override
	public Set<ConstraintDescriptor<?>> getComposingConstraints() {
		return decoratedConstraintDescriptor.getComposingConstraints();
	}

	/**
	 * @see javax.validation.metadata.ConstraintDescriptor#isReportAsSingleViolation()
	 */
	@Override
	public boolean isReportAsSingleViolation() {
		return decoratedConstraintDescriptor.isReportAsSingleViolation();
	}

	/**
	 * Decorator constructor.
	 * 
	 * @param constraintDescriptor
	 *            Constraint descriptor to be wrapped.
	 */
	public ConstraintDescriptorImpl(final ConstraintDescriptor<AnyAnnotation> constraintDescriptor) {
		this.decoratedConstraintDescriptor = constraintDescriptor;
	}

}
