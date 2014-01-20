/**
 * 
 */
package ContactManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Dave
 *
 */
public class ContactManagerImpl implements ContactManager {

	
	private Set<Contact> contactsList = new HashSet();
	private ArrayList<Meeting> meetingList = new ArrayList<Meeting>();
	
	
	@Override
	public int addFutureMeeting(Set<Contact> contacts, Calendar date) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PastMeeting getPastMeeting(int id) {
		try {
			for (Meeting m : meetingList)
				if (m.getId() == id)
			return (PastMeeting) m;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public FutureMeeting getFutureMeeting(int id) {
		try {
			for (Meeting m : meetingList)
			    if (m.getId() == id)
			return (FutureMeeting) m;		
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Meeting getMeeting(int id) {
		try {
			for (Meeting m : meetingList)
			    if (m.getId() == id)
			return (MeetingImpl) m;
		} catch (Exception e) {
			return null;					
		}
	}

	@Override
	public List<Meeting> getFutureMeetingList(Contact contact) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Meeting> getFutureMeetingList(Calendar date) {
		// TODO Auto-generated method stub
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

	@Override
	public void addMeetingNotes(int id, String text) {
		if (MeetingImpl.notes.equals("No notes yet.")) {
			MeetingImpl.notes = note;
		} else {
			MeetingImpl.notes += "; " + note;
		}
		
	}
		
	}

	@Override
	public void addNewContact(String name, String notes) {
		// TODO Auto-generated method stub
		
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
