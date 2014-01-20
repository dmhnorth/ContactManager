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
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ContactManager.Contact;
import ContactManager.ContactImpl;
import ContactManager.EmptyContactException;
import ContactManager.MeetingImpl;
import ContactManager.Meeting;

/**
 * @author Dave
 *
 */
public class MeetingTest {
	
	Calendar birthday2014 = Calendar.getInstance();
	private Contact contactA;
	private Contact contactB;
	private Set<Contact> testContactList;
	Meeting meeting;
	
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws EmptyContactException {
		contactA = new ContactImpl("David North", 0);
		contactB = new ContactImpl("Ian James", 0);
		testContactList = new HashSet<Contact>();
		testContactList.add(contactA);
		testContactList.add(contactB);
		meeting = new MeetingImpl(testContactList, birthday2014, 0);
	}

	/**
	 * Test method for {@link ContactManager.MeetingImpl#getId()}.
	 */
	@Test
	public void testGetId() {
		assertEquals("ID incorrect", meeting.getId(), 0);
	}

	/**
	 * Test method for {@link ContactManager.MeetingImpl#getDate()}.
	 */
	@Test
	public void testGetDate() {
		assertEquals("Date incorrect", meeting.getDate(), birthday2014);
	}

	/**
	 * Test method for {@link ContactManager.MeetingImpl#getContacts()}.
	 */
	@Test
	public void testGetContacts() {
		assertEquals("Contacts not found", testContactList, meeting.getContacts());
	}
	
	/**
	 * Test method for {@link ContactManager.MeetingImpl#getContacts()}.
	 */
	@Test
	public void testGetNullContacts() throws EmptyContactException {
		thrown.expect(EmptyContactException.class);
		new MeetingImpl(null, birthday2014, 0);
	}
	
	/**
	 * Test method for {@link ContactManager.MeetingImpl#getContacts()}.
	 */
	@Test
	public void testGetNoContacts() throws EmptyContactException {
		thrown.expect(EmptyContactException.class);
		new MeetingImpl(new HashSet<Contact>(), birthday2014, 0);
	}
	

}
