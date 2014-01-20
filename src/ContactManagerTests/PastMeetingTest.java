/**
 * 
 */
package ContactManagerTests;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ContactManager.Contact;
import ContactManager.ContactImpl;
import ContactManager.EmptyContactException;
import ContactManager.PastMeeting;
import ContactManager.PastMeetingImpl;

/**
 * @author Dave
 *
 */
public class PastMeetingTest {

		private Contact contactA;
		private Contact contactB;
		private Set<Contact> testContactList;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		contactA = new ContactImpl("David North", 0);
		contactB = new ContactImpl("Ian James", 0);
		testContactList = new HashSet<Contact>();
		testContactList.add(contactA);
		testContactList.add(contactB);
		
	
	}

	@Test
	public void testGetEmptyNotes() throws EmptyContactException {
		PastMeeting pastMeeting = new PastMeetingImpl(testContactList, Calendar.getInstance(), 0, "");
		assertEquals(pastMeeting.getNotes(), "");	
	}

	@Test
	public void testGetNullNotes() throws EmptyContactException {
		PastMeeting pastMeeting = new PastMeetingImpl(testContactList, Calendar.getInstance(), 0, null);
		assertEquals(pastMeeting.getNotes(), "");	
	}
	
	@Test
	public void testGetSomeNotes() throws EmptyContactException {
		PastMeeting pastMeeting = new PastMeetingImpl(testContactList, Calendar.getInstance(), 0, "Some");
		assertEquals(pastMeeting.getNotes(), "Some");	
	}
	

}
