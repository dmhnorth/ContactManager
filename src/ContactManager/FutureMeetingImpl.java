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
public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting{

	private Calendar dateOfMeeting = Calendar.getInstance();
	Set<Contact> meetingContacts = new HashSet<Contact>();

	public FutureMeetingImpl(Set<Contact> contacts, Calendar date) {
		super(contacts, date.YEAR, date.MONTH, date.DAY_OF_MONTH, date.HOUR, date.MINUTE);
		
		this.dateOfMeeting = date;
		this.meetingContacts = contacts;
	
	}
	
	/*
	 * Constructor for casting
	 */
	public FutureMeetingImpl(MeetingImpl meeting) {
		super(meetingContacts, dateOfMeeting, id);
		this.dateOfMeeting = meeting.getDate();
		this.meetingContacts = meeting.meetingContacts;
	}

}
