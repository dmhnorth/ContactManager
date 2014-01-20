/**
 * 
 */
package ContactManager;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Dave
 *
 */
public class PastMeetingImpl extends MeetingImpl implements PastMeeting {

	private Calendar dateOfMeeting;
	private int id;
	Set<Contact> contacts = new HashSet<Contact>();
	
	public PastMeetingImpl(Set<Contact> contacts, Calendar date) {
		super(contacts, date.YEAR, date.MONTH, date.DAY_OF_MONTH, date.HOUR, date.MINUTE);
	}
	
	
	@Override
	public String getNotes() {
		// TO DO
		return notes;
	}

}
