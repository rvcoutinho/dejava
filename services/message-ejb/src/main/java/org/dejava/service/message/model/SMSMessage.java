package org.dejava.service.message.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * SMS message.
 */
@Entity
@Table(name = "sms_message")
@Inheritance(strategy = InheritanceType.JOINED)
public class SMSMessage extends Message {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 4461770040372376096L;

}
