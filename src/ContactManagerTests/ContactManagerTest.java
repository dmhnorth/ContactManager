/**
 * 
 */
package ContactManagerTests;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.AssertionFailedError;

import org.junit.Before;
import org.junit.BeforeClass;
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

/**
 * @author Dave
 *
 */
public class ContactManagerTest {

	String name = "David North";
	String notes = "Some notes";
	Calendar date;
	ContactManager cm;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		cm = new ContactManagerImpl();	
		date = Calendar.getInstance();
		
		
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
		
		Contact[] sameNameSet = cm.getContacts(0, 1).toArray(new Contact[1]);
		System.out.println(sameNameSet[0]);
		System.out.println(sameNameSet[1]);
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
	
	
	@Test
	public void addAndGetPastMeetingList() {
		Calendar date3 = new GregorianCalendar();
		date3.add(Calendar.DATE, -1);
		
		cm.addNewContact("Jim", "notes");
		cm.addNewContact("Jim", "Some notes");
		
		Set<Contact> contacts = cm.getContacts("Jim");
		
		cm.addNewPastMeeting(contacts, date3, notes);
		
		//cm.getPastMeetingList();
		
		
	}
	
	
	/*
	 * tests addNewPastMeeting()
	 */
	@Test
	public void addNewPastMeetingViaId() {
		
		Calendar date4 = new GregorianCalendar();
		date4.add(Calendar.DATE, -1);
		cm.addNewContact("jim", notes);
		Set<Contact> contacts = cm.getContacts("jim");
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
		cm.addNewPastMeeting(null, date, notes);
		
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
}