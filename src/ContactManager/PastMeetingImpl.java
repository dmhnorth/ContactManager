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
	
	public PastMeetingImpl(Set<Contact> contacts, Calendar date, String notes) {
		super(contacts, date);
		this.notes = notes;
	}
	
	
	@Override
	public String getNotes() {
		return notes;
	}

}
