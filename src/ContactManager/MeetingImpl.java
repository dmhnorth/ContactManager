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
	
	private static int counter = 0;
	
	private int id;
	private Calendar date;
	Set<Contact> contacts;
	
	
	/*
	 * meeting constructor used to create all meetings from scratch, Past and Future
	 */
	public MeetingImpl(Set<Contact> contacts, Calendar date) {
		this.date = date;
		this.contacts = contacts;
		this.id = MeetingImpl.counter;
		MeetingImpl.counter++;
	}
	
	
	@Override
	public int getId() {
		return id;
	}

	@Override
	public Calendar getDate() {
		return date;
	}

	@Override
	public Set<Contact> getContacts() {
		return contacts;
	}

}
