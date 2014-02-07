/**
 * 
 */
package Impl;

import java.util.Calendar;
import java.util.Set;

import Interfaces.Contact;
import Interfaces.FutureMeeting;

/**
 * @author Dave
 *
 */
public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6410188274623775033L;

	public FutureMeetingImpl(Set<Contact> contacts, Calendar date, int iD) throws EmptyContactException {
		super(contacts, date, iD);
	}

}
