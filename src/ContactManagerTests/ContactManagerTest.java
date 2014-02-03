/**
 * 
 */
package ContactManagerTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ContactManager.Contact;
import ContactManager.ContactImpl;
import ContactManager.ContactManager;
import ContactManager.ContactManagerImpl;
import ContactManager.EmptyContactException;
import ContactManager.FutureMeeting;
import ContactManager.IdGenerator;
import ContactManager.Meeting;
import ContactManager.MeetingImpl;
import ContactManager.PastMeeting;
import ContactManager.PastMeetingImpl;

/**
 * @author Dave
 *
 */
public class ContactManagerTest {

	String name = "David North";
	String notes = "Some notes";
	Calendar date;	//date should be checked against the day, not the second!
	ContactManager cm;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		IdGenerator idgen = new MockIdGeneratorImpl();
		
		cm = new ContactManagerImpl(idgen);	
		date = Calendar.getInstance();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		
		cm = null;
		date = null;
	}
	
	@Test
	public void addNewContact() {
		
		cm.addNewContact(name, notes);
		Set<Contact> contactSet = cm.getContacts(name);
		
		Contact[] contacts = contactSet.toArray(new Contact[0]);
		Contact contact = contacts[0];
		
		assertEquals(contact.getName(), name);
		assertEquals(contact.getNotes(), notes);		
	}
	
	
	
	@Test
	public void addNewContactwithNullName() throws NullPointerException {
		thrown.expect(NullPointerException.class);
		cm.addNewContact(null, notes);
	}
	
	@Test
	public void addNewContactwithNullNotes() throws NullPointerException {
		thrown.expect(NullPointerException.class);
		cm.addNewContact(name, null);
	}
	
	@Test
	public void getContactswithNullName() throws NullPointerException {
		thrown.expect(NullPointerException.class);
		String name = null;
		cm.getContacts(name);
	}
	
	@Test
	public void getContactsWithSameName() {
		cm.addNewContact("Jim", notes);
		cm.addNewContact("Jim", notes);	
		
		Set<Contact> sameNameSet = cm.getContacts("Jim");
		assertTrue(sameNameSet.size() == 2);	
		
	}
	
	/*
	 * tests getContactsViaId() but currently doesn't run correctly when run
	 * at the same time as the other tests due to the IllegalArgumentException within
	 * the above method mentioned
	 */
	@Test
	public void getContactsViaId() {
				
		cm.addNewContact("Jim", notes);
		cm.addNewContact("Jim", notes);	
		//this is not working because of the ids set
		Contact[] sameNameSet = cm.getContacts(0, 1).toArray(new Contact[1]);
		assertTrue(sameNameSet[0].getName() == "Jim");	
		assertTrue(sameNameSet[1].getName() == "Jim");	
	}
	
	@Test
	public void getContactsWithUnknownId() throws IllegalArgumentException {
		thrown.expect(IllegalArgumentException.class);
		
		cm.addNewContact("Jim", notes);
		cm.addNewContact("Jim", notes);	
		cm.getContacts(0, 1, 2);
	}
	
	
	/*
	 * tests addFutureMeeting() and getFutureMeeting()
	 */
	@Test
	public void addFutureMeeting() throws EmptyContactException {
		
		
		cm.addNewContact("Jim", "notes"); // Started using the cm methods so exceptions are handled externally to the test
		cm.addNewContact("Jim", "Some notes");
		
		Set<Contact> contacts = cm.getContacts("Jim");
		
		int id = cm.addFutureMeeting(contacts, date); //This statement creates an int! assign id to a variable
		FutureMeeting fMeeting = cm.getFutureMeeting(id); // now use it to return a FutureMeeting
		
		assertEquals(id, fMeeting.getId());
		assertEquals(contacts, fMeeting.getContacts());
		
	}
	
	@Test
	public void addfutureMeetingInPast() throws IllegalArgumentException, InterruptedException {
		thrown.expect(IllegalArgumentException.class);
		Calendar date2 = new GregorianCalendar();
		
		cm.addNewContact("Jim", "notes");
		cm.addNewContact("Jim", "Some notes");
		
		Set<Contact> contacts = cm.getContacts("Jim");
		
		int id = cm.addFutureMeeting(contacts, date2);
		Thread.sleep(100);
		cm.getFutureMeeting(id);		
	}
	
	/*
	 * this tests the getPastMeetingList method
	 * 
	*/
	@Test
	public void getPastMeetingList() throws EmptyContactException { // TODO fix this
		Calendar past = new GregorianCalendar();
		past.add(Calendar.DATE, -1);
		
		Calendar future = new GregorianCalendar();
		future.add(Calendar.DATE, +1);
		
		cm.addNewContact("Jim", "notes");
		Set<Contact> contacts = cm.getContacts("Jim");
		Contact contactjim = contacts.toArray(new Contact[0])[0];
				
		Set<Contact> testList = new HashSet<Contact>();
		testList.add(contactjim);	
		
		cm.addNewPastMeeting(testList, past, notes);
		
		cm.addFutureMeeting(testList, future);
		
		List<PastMeeting> returnedPmList = cm.getPastMeetingList(contactjim);
		Set<Contact> actual = returnedPmList.get(0).getContacts();
		
		assertEquals(actual, testList);	
	}
	
	
	/*
	 * tests addNewPastMeeting()
	 */
	@Test
	public void addNewPastMeetingViaId() {
		
		Calendar date4 = new GregorianCalendar();
		date4.add(Calendar.DATE, -1);
		cm.addNewContact(name, notes);
		Set<Contact> contacts = cm.getContacts(name);
		Contact contact = contacts.toArray(new Contact[0])[0];
				
		cm.addNewPastMeeting(contacts, date4, notes);
		PastMeeting ml = cm.getPastMeetingList(contact).get(0);
		
		assertEquals(ml.getContacts(), contacts);		
				
	}
	
	/*
	 * tests the addNewPastMeetingViaId IllegalArgumentException
	 * if contacts parameter is empty or contact does not exist in database
	 */
	@Test
	public void addNewPastMeetingIllegalArgumentException() {
		thrown.expect(IllegalArgumentException.class);
		
		Set<Contact> contacts = new HashSet<Contact>();
		Contact jonny = new ContactImpl("jonny", 0);
		contacts.add(jonny);
		
		cm.addNewPastMeeting(contacts, date, notes);
		
	}
	
	/*
	 * tests the NullPointerException on addNewPastMeeting
	 */
	@Test
	public void addNewPastMeetingNullPointerException() {
		thrown.expect(NullPointerException.class);
		cm.addNewContact("jim", notes);
		Set<Contact> contacts = cm.getContacts("jim");
		cm.addNewPastMeeting(contacts, null, notes);
	}
	
	
	
	
	
	/*
	 * tests getMeeting() works
	 */
	@Test
	public void testGetMeeting() throws EmptyContactException {
		
		cm.addNewContact("Jim", "notes"); // Started using the cm methods so exceptions are handled externally to the test
		cm.addNewContact("Jim", "Some notes");
		
		Set<Contact> contacts = cm.getContacts("Jim");
		
		int id = cm.addFutureMeeting(contacts, date); //This statement creates an int! assign id to a variable
		Meeting meeting = cm.getMeeting(id); // now use it to return a FutureMeeting
		
		assertEquals(id, meeting.getId());
		assertEquals(contacts, meeting.getContacts());		
	}
		
	/*
	 * Tests if addMeetingNotes() works	
	 */
	@Test
	public void testAddMeetingNotes() {
		cm.addNewContact("Jim", "notes");
		cm.addNewContact("Jim", "Some notes");
		
		Set<Contact> contacts = cm.getContacts("Jim");
		
		date.set(2040, 1, 1);
		int id = cm.addFutureMeeting(contacts, date); //This statement creates an int! assign id to a variable
		FutureMeeting fm = cm.getFutureMeeting(id);
		
		
		
		cm.addMeetingNotes(id, "Add some notes");
		Meeting pm = cm.getMeeting(id);
		
		assertEquals(fm.getContacts(), pm.getContacts());
		assertEquals(fm.getDate(), pm.getDate());
		assertEquals(fm.getId(), pm.getId());
		
	}

	/*
	 * tests addMeetingNotes() IllegalArgumentException
	 */
	@Test
	public void addMeetingNotesIllegalArgumentException(){
		thrown.expect(IllegalArgumentException.class);
		cm.addMeetingNotes(0, notes);
	}
	
	/*
	 * tests addMeetingNotes() NullPointerException
	 */
	@Test
	public void addMeetingNotesNullPointerException(){
		thrown.expect(NullPointerException.class);
		cm.addMeetingNotes(0, null);
	}
	
	
	/*
	 * tests addMeetingNotes() IllegalStateException
	 */
	@Test
	public void addMeetingNotesIllegalStateException(){
		thrown.expect(IllegalStateException.class);
		
		Calendar date = new GregorianCalendar();
		date.add(Calendar.DATE, -1);
		
		cm.addNewContact("Jim", "notes");
		cm.addNewContact("Jim", "Some notes");
		
		Set<Contact> contacts = cm.getContacts("Jim");
		
		
		int id = cm.addFutureMeeting(contacts, date);
		FutureMeeting fm = cm.getFutureMeeting(id);
		
		cm.addMeetingNotes(id, notes);
		
	}
	
	/*
	 * past meeting exception test
	 */
	@Test
	public void getPastMeetingExceptionTest() throws IllegalArgumentException {
		thrown.expect(IllegalArgumentException.class);
		
		cm.addNewContact("Jim", "notes");
		cm.addNewContact("Jim", "Some notes");
		
		Set<Contact> contacts = cm.getContacts("Jim");
		
		Calendar future = Calendar.getInstance();
		future.set(3000, 1, 1);
		
		int id = cm.addFutureMeeting(contacts, future);
		
		
		cm.getPastMeeting(id);
	}
	
	/*
	 * tests the meeting meetSort() method
	 */
	@Test
	public void meetSortTest() throws EmptyContactException {
		
		Calendar pastest = new GregorianCalendar();
		pastest.add(Calendar.DATE, -2);
		
		Calendar past = new GregorianCalendar();
		past.add(Calendar.DATE, -1);
		
		Calendar future = new GregorianCalendar();
		future.add(Calendar.DATE, +1);
		
		
		Calendar futurest = new GregorianCalendar();
		futurest.add(Calendar.DATE, +2);
		
		cm.addNewContact("Jim", "notes");
		//cm.addNewContact("Jim", "Some notes");		
		Set<Contact> contacts = cm.getContacts("Jim");
		
		Contact contactjim = contacts.toArray(new Contact[0])[0];
		
		//vb meetings in the cm, out of order. Currently NewPastMeetings can have a future date TODO
		cm.addNewPastMeeting(contacts, future, "future");
		cm.addNewPastMeeting(contacts, past, "past");
		cm.addNewPastMeeting(contacts, futurest, "futurest");
		cm.addNewPastMeeting(contacts, pastest, "pastest");
		
		//create a sorted PastMeeting list
		List<Meeting> meetingsOrdered = new ArrayList<Meeting>();
		PastMeeting meet1 = new PastMeetingImpl(contacts, pastest, 1, "pastest");
		PastMeeting meet2 = new PastMeetingImpl(contacts, past, 2, "past");
		PastMeeting meet3 = new PastMeetingImpl(contacts, future, 3, "future");
		PastMeeting meet4 = new PastMeetingImpl(contacts, futurest, 4, "futurest");
		

		meetingsOrdered.add(meet1);
		meetingsOrdered.add(meet2);
		meetingsOrdered.add(meet3);
		meetingsOrdered.add(meet4);
		
		PastMeeting actual = cm.getPastMeetingList(contactjim).toArray(new PastMeeting[0])[0];
		PastMeeting control = meetingsOrdered.toArray(new PastMeeting[0])[0];
		
		
		
		assertTrue(cm.getPastMeetingList(contactjim).size() == 2);
		assertEquals(control.getNotes(), actual.getNotes());
		assertTrue(actual.getNotes().equals("pastest"));

	}
	
	@Test
	public void getPastMeetingListException() throws IllegalArgumentException {
		thrown.expect(IllegalArgumentException.class);
		
		Contact bob = new ContactImpl("Bob", 0);
		cm.getPastMeetingList(bob);
		
		
	}
	
	@Test
	public void getFutureMeetingList() {
		
		Calendar past = new GregorianCalendar();
		past.add(Calendar.DATE, -1);
		
		Calendar future = new GregorianCalendar();
		future.add(Calendar.DATE, +1);
				
		Calendar futurest = new GregorianCalendar();
		futurest.add(Calendar.DATE, +2);
		
		cm.addNewContact("Jim", "notes");
		Set<Contact> contacts = cm.getContacts("Jim");
		
		cm.addNewPastMeeting(contacts, past, "some notes");
		cm.addFutureMeeting(contacts, futurest);
		cm.addFutureMeeting(contacts, future);
		
		Contact contactjim = contacts.toArray(new Contact[0])[0];
		assertTrue(cm.getFutureMeetingList(contactjim).size() == 2);
		
		
	}
	
	
	
	
}