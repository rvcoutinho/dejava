package org.dejava.service.soupsocial.controller.philanthropy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.servlet.http.Part;

import org.dejava.component.i18n.message.handler.ApplicationMessageHandler;
import org.dejava.service.philanthropy.model.Category;
import org.dejava.service.philanthropy.model.project.Project;
import org.dejava.service.philanthropy.util.MessageTypes;
import org.dejava.service.soupsocial.util.SoupSocialCtx;

/**
 * The controller for creating new projects.
 */
public abstract class AbstractCreateProjectController implements Serializable {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 7684658950396621485L;

	/**
	 * The faces message handler.
	 */
	@Inject
	@SoupSocialCtx
	protected ApplicationMessageHandler messageHandler;

	/**
	 * The new project.
	 */
	private Project newProject;

	/**
	 * Gets the new project.
	 * 
	 * @return New project.
	 */
	public Project getNewProject() {
		// If the idea is null.
		if (newProject == null) {
			// Creates a new project.
			newProject = new Project();
		}
		// Returns the project.
		return newProject;
	}

	/**
	 * Sets the new project.
	 * 
	 * @param newProject
	 *            New new project.
	 */
	public void setNewProject(final Project newProject) {
		this.newProject = newProject;
	}

	/**
	 * The address reference.
	 */
	private String addressReference;

	/**
	 * Gets the address reference.
	 * 
	 * @return The address reference.
	 */
	public String getAddressReference() {
		return addressReference;
	}

	/**
	 * Sets the address reference.
	 * 
	 * @param addressReference
	 *            New address reference.
	 */
	public void setAddressReference(final String addressReference) {
		this.addressReference = addressReference;
	}

	/**
	 * The display image for the project.
	 */
	private Part displayImage;

	/**
	 * Gets the display image for the project.
	 * 
	 * @return The display image for the project.
	 */
	public Part getDisplayImage() {
		return displayImage;
	}

	/**
	 * Sets the display image for the project.
	 * 
	 * @param displayImage
	 *            New display image for the project.
	 */
	public void setDisplayImage(final Part displayImage) {
		this.displayImage = displayImage;
	}

	/**
	 * A goal for the idea.
	 */
	private String goal1;

	/**
	 * Gets the goal (1) for the idea.
	 * 
	 * @return The goal (1) for the idea.
	 */
	public String getGoal1() {
		return goal1;
	}

	/**
	 * Sets the goal (1) for the idea.
	 * 
	 * @param goal1
	 *            New goal (1) for the idea.
	 */
	public void setGoal1(final String goal1) {
		this.goal1 = goal1;
	}

	/**
	 * A goal for the idea.
	 */
	private String goal2;

	/**
	 * Gets the goal (2) for the idea.
	 * 
	 * @return The goal (2) for the idea.
	 */
	public String getGoal2() {
		return goal2;
	}

	/**
	 * Sets the goal (2) for the idea.
	 * 
	 * @param goal2
	 *            New goal (2) for the idea.
	 */
	public void setGoal2(final String goal2) {
		this.goal2 = goal2;
	}

	/**
	 * A goal for the idea.
	 */
	private String goal3;

	/**
	 * Gets the goal (3) for the idea.
	 * 
	 * @return The goal (3) for the idea.
	 */
	public String getGoal3() {
		return goal3;
	}

	/**
	 * Sets the goal (3) for the idea.
	 * 
	 * @param goal3
	 *            New goal (3) for the idea.
	 */
	public void setGoal3(final String goal3) {
		this.goal3 = goal3;
	}

	/**
	 * Gets the goals for the project.
	 * 
	 * @return The goals for the project.
	 */
	protected List<String> getGoals() {
		// Creates a new list for the goals.
		final List<String> goals = new ArrayList<>();
		// If the goal (1) is given.
		if ((getGoal1() != null) && (!getGoal1().trim().isEmpty())) {
			// Adds the goal to the goals list.
			goals.add(getGoal1().trim());
		}
		// If the goal (2) is given.
		if ((getGoal2() != null) && (!getGoal2().trim().isEmpty())) {
			// Adds the goal to the goals list.
			goals.add(getGoal2().trim());
		}
		// If the goal (3) is given.
		if ((getGoal3() != null) && (!getGoal3().trim().isEmpty())) {
			// Adds the goal to the goals list.
			goals.add(getGoal3().trim());
		}
		// Returns the goals.
		return goals;
	}

	/**
	 * Gets the possible category as a list of select items.
	 * 
	 * @return The possible category as a list of select items
	 */
	public List<SelectItem> getCategoryOptions() {
		// Creates a new list for the category options.
		final List<SelectItem> categoryOptions = new ArrayList<>();
		// Adds the empty option.
		categoryOptions.add(new SelectItem(null, ""));
		// For each category.
		for (final Category currentCategory : Category.values()) {
			// Adds the current category and its localized name in the select item list.
			categoryOptions.add(new SelectItem(currentCategory, messageHandler.getMessage(
					MessageTypes.Model.class, null, currentCategory.toString(), null)));
		}
		// Returns the category options as a list of select items.
		return categoryOptions;
	}

	/**
	 * Clears the current project.
	 */
	protected void clear() {
		// Clears the current project.
		setNewProject(null);
		setAddressReference(null);
		setDisplayImage(null);
		setGoal1(null);
		setGoal2(null);
		setGoal3(null);
	}

}
