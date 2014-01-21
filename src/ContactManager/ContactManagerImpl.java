/**
 * 
 */
package ContactManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @author Dave
 *
 */
public class ContactManagerImpl implements ContactManager {
	
	Map<String, Set<Contact>> contactMap;
	Map<Integer, Contact> idMap;
	Map<Integer, Meeting> meetingMap; //Meeting list data structure
	IdGenerator idGenerator;
	
	
	
	public ContactManagerImpl() {
		contactMap = new HashMap<String, Set<Contact>>();
		idMap = new HashMap<Integer, Contact>();
		meetingMap = new HashMap<Integer, Meeting>();
		idGenerator = new IdGenerator();
		
	}
	
	
	@Override
	public int addFutureMeeting(Set<Contact> contacts, Calendar date) throws IllegalArgumentException {
		
		Calendar now = Calendar.getInstance();
		
		if (date.before(now)) { 
			throw new IllegalArgumentException();
		}
		
		FutureMeeting meeting = null;
		try {
			meeting =  new FutureMeetingImpl(contacts, date, idGenerator.getNewId());
		} catch (EmptyContactException e) {
			e.printStackTrace();
		}		
		//Add meeting to the data structure
		meetingMap.put(meeting.getId(), meeting);		
		return meeting.getId();
	}

	@Override
	public PastMeeting getPastMeeting(int id) {
		PastMeeting meeting = null;
		
		return meeting;
	}

	@Override
	public FutureMeeting getFutureMeeting(int id) {		
		return (FutureMeeting) meetingMap.get(id);
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
		
		Calendar now = Calendar.getInstance();
		Meeting pastMeeting = null;
		
		try {
			pastMeeting = new PastMeetingImpl(contacts, date, idGenerator.getNewId(), text);
		} catch (EmptyContactException e) {
			e.printStackTrace();
		}
		meetingMap.put(pastMeeting.getId(), pastMeeting);		
	}
	
	//TODO all a bit of a mess at the moment
	@Override
	public void addMeetingNotes(int id, String text) {
	
	}
		
	

	@Override
	public void addNewContact(String name, String notes) throws NullPointerException {
		
		if (name == null || notes == null) { 
			throw new NullPointerException();
		}
		
		Contact contact = new ContactImpl(name, idGenerator.getNewId()); // TODO
		contact.addNotes(notes);
		
		idMap.put(contact.getId(), contact);	//mirrors the contactMap
		
		if (contactMap.get(name) == null) {
			Set<Contact> contacts = new HashSet<Contact>();
			contacts.add(contact);
			contactMap.put(name, contacts);
		} else {
			Set<Contact> contacts = contactMap.get(name);// create variable of the current Contact Set that exists within the map in order to point at it on the next line
			contacts.add(contact);
		}
				
	}

	/*
	 * 
	 */
	@Override
	public Set<Contact> getContacts(int... ids) throws IllegalArgumentException {
		
		Set<Contact> contacts = new HashSet<Contact>();
		for (int id : ids) {
			if (idMap.get(id) == null){
				throw new IllegalArgumentException();
			}
			contacts.add(idMap.get(id));
		}
		
		return contacts;
	}

	
	/*
	 * using the contactMap we have now converted the values into Sets so it works as a matrix
	 */
	@Override
	public Set<Contact> getContacts(String name) throws NullPointerException {
		
		if (name == null) { 
			throw new NullPointerException(); 
		}
		
		return contactMap.get(name);
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

}
