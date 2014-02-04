/**
 * 
 */
package ContactManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
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
	
	Map<String, Set<Contact>> contactMap;
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
		
	@Override
	public List<Meeting> getFutureMeetingList(Contact contact) throws IllegalArgumentException {
		
		Calendar now = new GregorianCalendar();
		List<Meeting> result = new ArrayList<Meeting>();
		List<FutureMeeting> futureMeetings = new ArrayList<FutureMeeting>();
		
		//checks contacts exist
		HashSet<Contact> contacts = new HashSet<Contact>();
		contacts.add(contact);
		if (!contactExists(contacts)){
			throw new IllegalArgumentException();
		}
		
		//creates a list of all the meetings after now
		now = Calendar.getInstance();
		for (Meeting m: meetingMap.values()) {
			if (m.getDate().after(now)) {
				futureMeetings.add((FutureMeeting) m);
			}
		}
		//if the contact is within the meeting, that meeting is added to the result
		for (Meeting m2: futureMeetings) {
			for (Contact c : m2.getContacts()) {
				if (c.equals(contact)) {
					result.add((FutureMeeting) m2);
				}
			}
		}
		Collections.sort(result, new MeetSort());
		return result;
	}

	/*
	 * Used for checking whether two meetings happen on the same day but disregards the time of day
	 */
	private boolean sameDay(Calendar date1, Calendar date2) {
				
		if (date1.get(Calendar.DAY_OF_YEAR) == date2.get(Calendar.DAY_OF_YEAR) && date1.get(Calendar.MONTH) == date2.get(Calendar.MONTH) && date1.get(Calendar.DATE) == date2.get(Calendar.DATE)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Meeting> getFutureMeetingList(Calendar date) {

		List<Meeting> result = new ArrayList<Meeting>();

		for (Meeting m : meetingMap.values()) {
			if (sameDay(m.getDate(), date)) {
				result.add(m);
			}
		}
		Collections.sort(result, new MeetSort());
		return result;
	}

	/*
	 * Comparator for organizing a Collection of meetings into Chronological order
	 */
	private class MeetSort implements Comparator<Meeting> {

		@Override
		public int compare(Meeting a, Meeting b) {
			if (a.getDate().before(b.getDate())){
				return -1;
			} else if (a.getDate().after(b.getDate())){
				return 1;
			} else {
				return 0;
			}
		}
	}

	@Override
	public List<PastMeeting> getPastMeetingList(Contact contact) throws IllegalArgumentException {

		Calendar now = new GregorianCalendar();
		Set<PastMeeting> result = new HashSet<PastMeeting>();
		List<PastMeeting> resultReturn = new ArrayList<PastMeeting>();
		List<PastMeeting> pastMeetings = new ArrayList<PastMeeting>();

		HashSet<Contact> contacts = new HashSet<Contact>();
		contacts.add(contact);
		if (!contactExists(contacts)){
			throw new IllegalArgumentException();
		}

		//creates a list of meetings before now
		now = Calendar.getInstance();
		for (Meeting m: meetingMap.values()) {
			if (m.getDate().before(now)) {
				pastMeetings.add((PastMeeting) m);
			}

			//if the contact is a contact within these new meetings, add it to the result set
			for (Meeting m2 : pastMeetings)
				for (Contact c : m2.getContacts()){
					if (c.equals(contact)) {  
						result.add((PastMeeting) m2);	
					}
				}	
		}
		resultReturn.addAll(result);
		Collections.sort(resultReturn, new MeetSort());
		return resultReturn;
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

	/*
	 * This Method establishs if a contact exists within the contacts container
	 */
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

		Contact contact = new ContactImpl(name, idGenerator.getNewId());
		contact.addNotes(notes);

		idMap.put(contact.getId(), contact);

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
