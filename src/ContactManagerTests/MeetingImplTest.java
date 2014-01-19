/**
 * 
 */
package ContactManagerTests;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ContactManager.MeetingImpl;

/**
 * @author Dave
 *
 */
public class MeetingImplTest {
	
	Calendar birthday2013 = Calendar.getInstance();
	
	MeetingImpl meetingA = new MeetingImpl(2013, 9, 3, 12);//need to add further constructor params
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
		fail("Not yet implemented"); // ASSUMING HASH IS GENERATED ON DATE VARIABLE
	}

	/**
	 * Test method for {@link ContactManager.MeetingImpl#getDate()}.
	 */
	@Test
	public void testGetDate() {
		birthday2013.set(2013, 9, 3, 12, 0);//year,month,day,hour,minute.
		assertEquals("Date incorrect", meetingA.getDate().toString(), birthday2013.toString() );
		System.out.println("Date of this meeting was: " + meetingA.getDate().toString());
	}

	/**
	 * Test method for {@link ContactManager.MeetingImpl#getContacts()}.
	 */
	@Test
	public void testGetContacts() {
		fail("Not yet implemented"); // TODO
	}

}
