package org.dejava.service.message.component;

import static org.dejava.properties.util.MailConfigProps.MAIL_CONFIG_PROPERTIES;

import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.dejava.service.message.model.EmailMessage;

/**
 * MDB for the email sender.
 */
@MessageDriven(mappedName = "/Queue/Message/Send")
public class EmailMessageSender implements MessageListener {

	/**
	 * The message EJB component.
	 */
	@Inject
	private EmailMessageComponent messageComponent;

	/**
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	@Override
	public void onMessage(final Message message) {
		// Tries to send the email message.
		try {
			// Gets the original message.
			EmailMessage originalMessage = message.getBody(EmailMessage.class);
			// Gets the mail session instance.
			Session mailSession = Session.getInstance(MAIL_CONFIG_PROPERTIES);
			// Creates a default MimeMessage object.
			MimeMessage mailMessage = new MimeMessage(mailSession);
			// Sets the "From" field of the header.
			mailMessage.setFrom(new InternetAddress(originalMessage.getSender()));
			// Sets the "To" field of the header.
			mailMessage.addRecipient(javax.mail.Message.RecipientType.TO,
					new InternetAddress(originalMessage.getRecipient()));
			// Sets the "Subject" field of the header.
			mailMessage.setSubject(originalMessage.getSubject());
			// Sets the email message content.
			mailMessage.setContent(originalMessage.getContent(), "text/html");
			// Sends the message
			Transport.send(mailMessage);
			// Adds the sent message.
			messageComponent.addOrUpdate(originalMessage);
		}
		// If the JMS message cannot be processed.
		catch (JMSException exception) {
			// TODO Auto-generated catch block
		} catch (AddressException exception) {
			// TODO Auto-generated catch block
		} catch (MessagingException exception) {
			// TODO Auto-generated catch block
		}
	}
}
