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
public class PastMeetingImpl extends MeetingImpl implements PastMeeting {

	private Calendar dateOfMeeting;
	private String notes = "";
	
	public PastMeetingImpl(Set<Contact> contacts, Calendar date) {
		super(contacts, date.YEAR, date.MONTH, date.DAY_OF_MONTH, date.HOUR, date.MINUTE);
	}
	
	
	@Override
	public String getNotes() {
		return notes;
	}

}
