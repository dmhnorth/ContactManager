/**
 * 
 */
package ContactManager;

import java.util.Calendar;
import java.util.Set;

/**
 * @author Dave
 * Check the top of this class 
 */
public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting{

	
	public FutureMeetingImpl(Set<Contact> contacts, int year, int month, int date, int hourOfDay, int minute) {
		super(contacts, year, month, date, hourOfDay, minute);
	}
	
	/*
	 * mostly unneeded
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Calendar getDate() {
		super();
		return this.dateOfMeeting;
	}

	@Override
	public Set<Contact> getContacts() {
		// TODO Auto-generated method stub
		return null;
	}
	*/
}
