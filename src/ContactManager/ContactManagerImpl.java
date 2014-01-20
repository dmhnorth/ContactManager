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

	
	private Set<Contact> contactsList = new HashSet();
	private ArrayList<Meeting> meetingList = new ArrayList<Meeting>();
	private Calendar cNow = Calendar.getInstance();
	
	
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
		
		List result = new ArrayList();
		
		for (Meeting m : meetingList)
		    for (ContactImpl c : m.meetingContacts)//clean up this line so it searches each meeting in the meeting list.
		    	if (c.getId() == contact.getId())
		    		result.add(m);
				
				return result;
	}

	@Override
	public List<Meeting> getFutureMeetingList(Calendar date) {
		
		List result = new ArrayList();
		
		Iterator<MeetingImpl> it = meetingList.iterator();
		while(it.hasNext())
		{
		    MeetingImpl meet = it.next();
		    	if(meet.dateOfMeeting.equals(date)) {
		    		result.add(meet);
		    	}
		}
		
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
		try {
			for (Meeting m : meetingList)
			    if (m.getId() == id) {
	
			    	if (m.notes.equals("No notes yet.")) {
			    		m.notes = text;
			    	} else {
			    		m.notes += "; " + text;
			    	}
			    }
		} catch (Exception e) {
			System.out.println("You didn't add any notes.");
		}
	}
		
	

	@Override
	public void addNewContact(String name, String notes) {
		Contact name1 = new ContactImpl(notes);
		
		
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
