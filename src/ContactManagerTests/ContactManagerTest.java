/**
 * 
 */
package ContactManagerTests;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

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
	
	@Test
	public void getVariableNumberOfContactsViaId() {
		cm.addNewContact("Jim", notes);
		cm.addNewContact("Jim", notes);	
		
		Contact[] sameNameSet = cm.getContacts(0, 1).toArray(new Contact[1]);
		System.out.println(sameNameSet[0]);
		System.out.println(sameNameSet[1]);
		assertTrue(sameNameSet[0].getName() == "Jim");	
		assertTrue(sameNameSet[1].getName() == "Jim");	
	}
	
	@Test
	public void getVariableNumberOfContactsUnknownId() throws IllegalArgumentException {
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
	public void futureMeetingInPast() throws IllegalArgumentException {
		thrown.expect(IllegalArgumentException.class);
		Calendar date = new GregorianCalendar();
		date.add(Calendar.DATE, -1);
		
		cm.addNewContact("Jim", "notes");
		cm.addNewContact("Jim", "Some notes");
		
		Set<Contact> contacts = cm.getContacts("Jim");
		
		cm.addFutureMeeting(contacts, date);
	}
	
	/*
	 * tests addNewPastMeeting() and getPastMeeting()
	 */
	@Test
	public void addAndGetPastMeetingViaId() {
		
		Calendar date = new GregorianCalendar();
		date.add(Calendar.DATE, -1);
		
		cm.addNewContact("Jim", "notes");
		cm.addNewContact("Jim", "Some notes");
		
		Set<Contact> contacts = cm.getContacts("Jim");
		
		cm.addNewPastMeeting(contacts, date, notes);
		Meeting pastMeet = cm.getPastMeeting(0);
		
		assertEquals(pastMeet, cm.getMeeting(pastMeet.getId()));
		
		
	}
}