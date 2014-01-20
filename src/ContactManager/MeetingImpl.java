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
	
	private static int meetingCounter = 0;
	
	private int id;
	private String notes;//Unsure if required here or in subclasses at the moment
	private Calendar dateOfMeeting = Calendar.getInstance();
	Set<Contact> meetingContacts = new HashSet<Contact>();
	
	
	/*
	 * meeting constructor used to create all meetings from scratch, Past and Future
	 */
	public MeetingImpl(Set<Contact> contacts, int year, int month, int date, int hourOfDay, int minute) {
		this.dateOfMeeting.set(year, month, date, hourOfDay, minute);
		this.meetingContacts = contacts;
		meetingCounter++;
		this.id = Math.abs(meetingCounter + dateOfMeeting.hashCode()%10000);
	}
	
	
	/*
	 * Constructor for casting Meetings to their respective types
	 */
	public MeetingImpl(Set<Contact> contacts, Calendar date, int id) {
		this.dateOfMeeting = date;
		this.meetingContacts = contacts;
		this.id = id;
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
		return meetingContacts;
	}

}
