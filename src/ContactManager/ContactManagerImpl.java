/**
 * 
 */
package ContactManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


/**
 * @author Dave
 *
 */
public class ContactManagerImpl implements ContactManager {
	
	
	
	
	@Override
	public int addFutureMeeting(Set<Contact> contacts, Calendar date) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PastMeeting getPastMeeting(int id) {
		PastMeeting meeting = null;
		
		return meeting;
	}

	@Override
	public FutureMeeting getFutureMeeting(int id) {
		FutureMeeting meeting = null;
		
		return meeting;
	}

	@Override
	public Meeting getMeeting(int id) {
		Meeting meeting = null;
		
		return meeting;
	}
	
	@Override
	public List<Meeting> getFutureMeetingList(Contact contact) {
		List<Meeting> result = new ArrayList<Meeting>();
		
		return result;	    	
	}

	@Override
	public List<Meeting> getFutureMeetingList(Calendar date) {
		
		return null;
	}

	@Override
	public List<PastMeeting> getPastMeetingList(Contact contact) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addNewPastMeeting(Set<Contact> contacts, Calendar date,	String text) {
		// TODO Auto-generated method stub
		
	}
	
	//TODO all a bit of a mess at the moment
	@Override
	public void addMeetingNotes(int id, String text) {
	
	}
		
	

	@Override
	public void addNewContact(String name, String notes) {
		
	}

	@Override
	public Set<Contact> getContacts(int... ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Contact> getContacts(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

}
