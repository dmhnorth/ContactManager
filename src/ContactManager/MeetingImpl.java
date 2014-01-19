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
public class MeetingImpl implements Meeting {
	
	private Calendar dateOfMeeting;
	private int id;
	
	
	public MeetingImpl(Set<Contact>, int year, int month, int date, int hourOfDay, int minute) {
		//uses the parameters from user of the set function within Calendar on javaDoc
	}
	
	
	@Override
	public int getId() {
		return id;
	}

	@Override
	public Calendar getDate() {
		return dateOfMeeting;
	}

	@Override
	public Set<Contact> getContacts() {
		// TODO Auto-generated method stub
		return null;
	}

}
