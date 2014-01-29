/**
 * 
 */
package ContactManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @author Dave
 *
 */
public class ContactManagerImpl implements ContactManager {
	
	Map<String, Set<Contact>> contactMap;	// purely for duplicate names TODO check where this is being used
	Map<Integer, Contact> idMap; // master contact list
	Map<Integer, Meeting> meetingMap; //Meeting list data structure
	IdGenerator idGenerator;
	
	
	
	public ContactManagerImpl(IdGenerator idgen) {
		contactMap = new HashMap<String, Set<Contact>>();
		idMap = new HashMap<Integer, Contact>();
		meetingMap = new HashMap<Integer, Meeting>();
		idGenerator = idgen;
		
	}
	
	
	@Override
	public int addFutureMeeting(Set<Contact> contacts, Calendar date) throws IllegalArgumentException {
		
		Calendar now = Calendar.getInstance();
		
		if (date.before(now)) { 
			throw new IllegalStateException();
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
	public PastMeeting getPastMeeting(int id) throws IllegalArgumentException {		
		
		Calendar now = Calendar.getInstance();
		Meeting pm = meetingMap.get(id);
		
		if (pm.getDate().after(now)) {
			throw new IllegalArgumentException();
		}
				
		
		return (PastMeeting) pm;
	}

	@Override
	public FutureMeeting getFutureMeeting(int id) throws IllegalArgumentException {
		
		Calendar now = Calendar.getInstance();
		
		if (getMeeting(id).getDate().before(now)) {
			throw new IllegalArgumentException();
		}
		return (FutureMeeting) meetingMap.get(id);
	}

	@Override
	public Meeting getMeeting(int id) {
		return meetingMap.get(id);
	}
	
	private List<Meeting> meetSort(List<Meeting> meeting) throws IllegalArgumentException{
		
		if (meeting == null) {
			throw new IllegalArgumentException("The meeting list is empty.");
		}		
		
		List<Meeting> result = new ArrayList<Meeting>();
		
		result.add(meeting.get(0));
		
		Meeting pointer = result.get(0);
		
		
		for (Meeting m : meeting) {
			if (m.getDate().after(pointer.getDate())) {
				result.add(m);
			} else {
				result.add(0, m);
			}
			
		}
		return result;
		
		
	}
	
	
	
	@Override
	public List<Meeting> getFutureMeetingList(Contact contact) {
		//TODO		
		return null;	    	
	}

	@Override
	public List<Meeting> getFutureMeetingList(Calendar date) {
		//TODO
		return null;
	}

	@Override
	public List<PastMeeting> getPastMeetingList(Contact contact) {
		
		Calendar now = new GregorianCalendar();		
		List<PastMeeting> result = new ArrayList<PastMeeting>();
		List<PastMeeting> pastMeetings = new ArrayList<PastMeeting>();
		
		for (Meeting m: meetingMap.values()) {
			if (m.getDate().before(now)) {
				pastMeetings.add((PastMeeting) m);	//creates a list of meetings before now
			}
			
		for (Meeting m2 : pastMeetings)
			for (Contact c : m2.getContacts()){
		        if (c.equals(contact)) {  
				result.add((PastMeeting) m2);	//if the contact is a contact within these new meetings, add to the result
		        }
			}	
		}
		meetSort((List<Meeting>) (List<?>) result);	// add casting
		return result;
	}

	@Override
	public void addNewPastMeeting(Set<Contact> contacts, Calendar date,	String text) throws IllegalArgumentException, NullPointerException{
		
		if (contacts == null || date == null || text == null){
			throw new NullPointerException();
		}
				
		
		if (!contactExists(contacts)) {
			throw new IllegalArgumentException();
		}
				
		Meeting pastMeeting = null;
		
		try {
			pastMeeting = new PastMeetingImpl(contacts, date, idGenerator.getNewId(), text);
			meetingMap.put(pastMeeting.getId(), pastMeeting);
		} catch (EmptyContactException e) {
			e.printStackTrace();
		}		
	}

	private boolean contactExists(Set<Contact> contacts) {
		boolean contactsExist = false;
		for (Contact contact : contacts) {
				if (idMap.containsValue(contact)) {
					contactsExist = true;
				}	
		}
		return contactsExist;
	}
	
	
	@Override
	public void addMeetingNotes(int id, String text) throws IllegalArgumentException, NullPointerException, IllegalArgumentException {
		
		if (text == null) {
			throw new NullPointerException();
		}
		
		Calendar now = Calendar.getInstance();
		FutureMeeting fm = (FutureMeeting) meetingMap.get(id);		
		
			if (fm == null) {
				throw new IllegalArgumentException();
			}
			
			if (fm.getDate().before(now)) { 
				throw new IllegalStateException();
			}		
		
		PastMeeting pm = null;
		
		try {
			pm = new PastMeetingImpl(fm.getContacts(), fm.getDate(), id, text);
		} catch (EmptyContactException e) {			
			e.printStackTrace();
		}
		
		meetingMap.put(id, pm);
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
	 * Using a contactMap we have now converted the values into Sets so it works as a matrix
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
