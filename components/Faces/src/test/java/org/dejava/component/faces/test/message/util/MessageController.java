package org.dejava.component.faces.test.message.util;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import org.dejava.component.faces.test.message.constant.ErrorKeys;
import org.dejava.component.faces.test.message.constant.FatalKeys;
import org.dejava.component.faces.test.message.constant.InfoKeys;
import org.dejava.component.faces.test.message.constant.WarnKeys;
import org.dejava.component.i18n.message.handler.ApplicationMessageHandler;

/**
 * Controller that adds messages to the application.
 */
@Faces
@ManagedBean
public class MessageController {

	/**
	 * The application message handler.
	 */
	@Inject
	@Faces
	private ApplicationMessageHandler applicationMessageHandler;

	/**
	 * Adds an info message to the application.
	 */
	public void addInfoMessage() {
		applicationMessageHandler.addMessage(MessageTypes.Info.class, null, InfoKeys.TEST, null);
	}

	/**
	 * Adds an warn message to the application.
	 */
	public void addWarnMessage() {
		applicationMessageHandler.addMessage(MessageTypes.Warn.class, null, WarnKeys.TEST, null);
	}

	/**
	 * Adds an error message to the application.
	 */
	public void addErrorMessage() {
		applicationMessageHandler.addMessage(MessageTypes.Error.class, null, ErrorKeys.TEST, null);
	}

	/**
	 * Adds an error message (without the severity info) to the application.
	 */
	public void addErrorMessage2() {
		applicationMessageHandler.addMessage(MessageTypes.Error2.class, null, ErrorKeys.TEST, null);
	}

	/**
	 * Adds an fatal message to the application.
	 */
	public void addFatalMessage() {
		applicationMessageHandler.addMessage(MessageTypes.Fatal.class, null, FatalKeys.TEST, null);
	}

}
