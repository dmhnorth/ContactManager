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
public class MeetingImpl implements Meeting {
	
	private int id;
	private String notes;
	private Calendar dateOfMeeting;
	Set<Contact> meetingContacts = new HashSet<Contact>();
	
	
	public MeetingImpl(Set<Contact> contacts, int year, int month, int date, int hourOfDay, int minute) {
		this.dateOfMeeting.set(year, month, date, hourOfDay, minute);
		this.meetingContacts = contacts;
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
