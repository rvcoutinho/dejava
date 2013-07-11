package org.dejava.component.faces.message.test;

import java.io.File;
import java.net.URL;

import javax.faces.context.FacesContext;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.arquillian.warp.Activity;
import org.jboss.arquillian.warp.Inspection;
import org.jboss.arquillian.warp.Warp;
import org.jboss.arquillian.warp.WarpTest;
import org.jboss.arquillian.warp.jsf.AfterPhase;
import org.jboss.arquillian.warp.jsf.Phase;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Tests for the faces message handler.
 */
@RunWith(Arquillian.class)
@WarpTest
@RunAsClient
public class FacesMessageHandlerTest {

	/**
	 * Browser simulator.
	 */
	@Drone
	WebDriver browser;

	/**
	 * Application context path.
	 */
	@ArquillianResource
	URL contextPath;

	/**
	 * Defines what should be done during deployment (before tests).
	 * 
	 * @return The configuration for the archive.
	 */
	@Deployment
	public static Archive<?> createTestArchive() {
		// Gets the maven dependency resolver.
		final MavenDependencyResolver dependencyResolver = DependencyResolvers.use(
				MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");
		// Defines and returns the archive definition.
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addAsResource(
						new File(
								"src/test/resources/org/dejava/component/faces/message/test/properties/info_pt_BR.properties"),
						"org/dejava/component/faces/message/test/properties/info_pt_BR.properties")
				.addAsResource(
						new File(
								"src/test/resources/org/dejava/component/faces/message/test/properties/warn_pt_BR.properties"),
						"org/dejava/component/faces/message/test/properties/warn_pt_BR.properties")
				.addAsResource(
						new File(
								"src/test/resources/org/dejava/component/faces/message/test/properties/error_pt_BR.properties"),
						"org/dejava/component/faces/message/test/properties/error_pt_BR.properties")
				.addAsResource(
						new File(
								"src/test/resources/org/dejava/component/faces/message/test/properties/fatal_pt_BR.properties"),
						"org/dejava/component/faces/message/test/properties/fatal_pt_BR.properties")
				.addAsLibraries(
						dependencyResolver.artifacts("org.jboss.arquillian.extension:arquillian-warp-jsf",
								"org.dejava.component:ejb", "org.dejava.component:validation",
								"org.dejava.component:exception", "org.dejava.component:i18n",
								"org.dejava.component:reflection").resolveAsFiles())
				.addPackages(true, "org.dejava.component.faces.message")
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				.addAsWebResource(new File("src/test/resources/WEB-INF/index.xhtml"))
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource("test-ds.xml", "test-ds.xml");
	}

	/**
	 * Info message.
	 */
	public static final String INFO_MSG = "info";

	/**
	 * Warn message.
	 */
	public static final String WARN_MSG = "warn";

	/**
	 * Error message.
	 */
	public static final String ERROR_MSG = "error";

	/**
	 * Fatal message.
	 */
	public static final String FATAL_MSG = "fatal";

	/**
	 * Represents a button click activity.
	 */
	class ButtonClickAcitivity implements Activity {

		/**
		 * The CSS selector for the button to be clicked.
		 */
		private final String cssSelector;

		/**
		 * @see org.jboss.arquillian.warp.Activity#perform()
		 */
		@Override
		public void perform() {
			// Clicks the add info message button.
			browser.findElement(By.cssSelector(cssSelector)).click();
		}

		/**
		 * Default constructor.
		 * 
		 * @param cssSelector
		 *            The CSS selector for the button to be clicked.
		 */
		public ButtonClickAcitivity(final String cssSelector) {
			this.cssSelector = cssSelector;
		}
	}

	/**
	 * Inspects if the given messages are in the context.
	 */
	class MessageInpection extends Inspection {

		/**
		 * Messages that should be in the application context (messages should be in correct order).
		 */
		private final String[] messages;

		/**
		 * Generated serial.
		 */
		private static final long serialVersionUID = -6034024916544529861L;

		/**
		 * Faces context.
		 */
		@ArquillianResource
		FacesContext facesContext;

		/**
		 * Asserts that the messages have been added.
		 */
		@AfterPhase(Phase.RENDER_RESPONSE)
		public void assertMessageAdded() {
			// If there are no messages.
			if (messages == null) {
				// Asserts that there are no messages in the context.
				Assert.assertEquals(0, facesContext.getMessageList().size());
			}
			// If there are any messages.
			else {
				// Asserts that the number of messages in the context is correct.
				Assert.assertEquals(messages.length, facesContext.getMessageList().size());
				// For each message.
				for (Integer currentMsgIdx = 0; currentMsgIdx < messages.length; currentMsgIdx++) {
					// Asserts that the message is the expected one.
					Assert.assertEquals(messages[currentMsgIdx],
							facesContext.getMessageList().get(currentMsgIdx).getDetail());
				}
			}
		}

		/**
		 * Default constructor.
		 * 
		 * @param messages
		 *            Messages that should be in the application context (messages should be in correct
		 *            order).
		 */
		public MessageInpection(final String[] messages) {
			this.messages = messages;
		}

	}

	/**
	 * Tests the add info message method.
	 */
	@Test
	public void testAddInfoMessage() {
		// Goes to the index page.
		browser.navigate().to(contextPath + "index.jsf");
		// Adds a info message to the application.
		Warp.initiate(new ButtonClickAcitivity(".infoMessage"))
		// Inspect to assert that message has been successfully added.
				.inspect(new MessageInpection(new String[] { INFO_MSG }));
	}

	/**
	 * Tests the add warn message method.
	 */
	@Test
	public void testAddWarnMessage() {
		// Goes to the index page.
		browser.navigate().to(contextPath + "index.jsf");
		// Adds a warn message to the application.
		Warp.initiate(new ButtonClickAcitivity(".warnMessage"))
		// Inspect to assert that message has been successfully added.
				.inspect(new MessageInpection(new String[] { WARN_MSG }));
	}

	/**
	 * Tests the add error message method.
	 */
	@Test
	public void testAddErrorMessage() {
		// Goes to the index page.
		browser.navigate().to(contextPath + "index.jsf");
		// Adds a error message to the application.
		Warp.initiate(new ButtonClickAcitivity(".errorMessage"))
		// Inspect to assert that message has been successfully added.
				.inspect(new MessageInpection(new String[] { ERROR_MSG }));
	}

	/**
	 * Tests the add error message (without the severity info) method.
	 */
	@Test
	public void testAddErrorMessageWithoutSeverity() {
		// Goes to the index page.
		browser.navigate().to(contextPath + "index.jsf");
		// Adds a error message to the application.
		Warp.initiate(new ButtonClickAcitivity(".errorMessage2"))
		// Inspect to assert that message has been successfully added.
				.inspect(new MessageInpection(new String[] { ERROR_MSG }));
	}

	/**
	 * Tests the add fatal message method.
	 */
	@Test
	public void testAddFatalMessage() {
		// Goes to the index page.
		browser.navigate().to(contextPath + "index.jsf");
		// Adds a fatal message to the application.
		Warp.initiate(new ButtonClickAcitivity(".fatalMessage"))
		// Inspect to assert that message has been successfully added.
				.inspect(new MessageInpection(new String[] { FATAL_MSG }));
	}

}
