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

	private String notes = "";
	
	public PastMeetingImpl(Set<Contact> contacts, Calendar date, int id, String notes) throws EmptyContactException {		
		super(contacts, date, id);
		if (notes != null) {
			this.notes = notes;
		}
	}
	
	
	@Override
	public String getNotes() {
		return notes;
	}

}
