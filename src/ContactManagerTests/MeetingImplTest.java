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
import ContactManager.MeetingImpl;

/**
 * @author Dave
 *
 */
public class MeetingImplTest {
	
	Calendar birthday2014 = Calendar.getInstance();

	private Contact contactA = new ContactImpl("David North");
	private Contact contactB = new ContactImpl("Ian James", "CEO of WCRS");

	private Set<Contact> testContactList = new HashSet();
	testContactList
	
	
	MeetingImpl meetingA = new MeetingImpl(testContactList, 2014, 9, 3, 12, 0);//need to add further constructor params
	MeetingImpl meetingB = new MeetingImpl();
	MeetingImpl meetingC = new MeetingImpl();
	MeetingImpl meetingD = new MeetingImpl();
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link ContactManager.MeetingImpl#getId()}.
	 */
	@Test
	public void testGetId() {
		assertEquals("ID incorrect", meetingA.getId(), meetingA.getDate().hashCode()%10000);
		System.out.println("The meetingA Id is: " + meetingA.getId());
	}

	/**
	 * Test method for {@link ContactManager.MeetingImpl#getDate()}.
	 */
	@Test
	public void testGetDate() {
		birthday2014.set(2013, 9, 3, 12, 0);//year,month,day,hour,minute.
		assertEquals("Date incorrect", meetingA.getDate().toString(), birthday2014.toString() );
		System.out.println("Date of this meetingA was: " + meetingA.getDate().toString());
	}

	/**
	 * Test method for {@link ContactManager.MeetingImpl#getContacts()}.
	 */
	@Test
	public void testGetContacts() {
		fail("Not yet implemented"); // TODO
	}

}
