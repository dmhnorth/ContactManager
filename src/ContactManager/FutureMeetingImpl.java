/**
 * 
 */
package ContactManager;

import java.util.Calendar;
import java.util.Set;

/**
 * @author Dave
 *
 */
public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting {

	public FutureMeetingImpl(Set<Contact> contacts, Calendar date, int iD) throws EmptyContactException {
		super(contacts, date, iD);
	}

}
