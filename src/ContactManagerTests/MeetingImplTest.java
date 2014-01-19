/**
 * 
 */
package ContactManagerTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ContactManager.MeetingImpl;

/**
 * @author Dave
 *
 */
public class MeetingImplTest {
	
	MeetingImpl meetingA = new MeetingImpl();
	
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
		assertEquals("ID incorrect", meetingA.getId(), meetingA.getDate().hashCode());
		fail("Not yet implemented"); // ASSUMING HASH IS GENERATED ON DATE VARIABLE
	}

	/**
	 * Test method for {@link ContactManager.MeetingImpl#getDate()}.
	 */
	@Test
	public void testGetDate() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link ContactManager.MeetingImpl#getContacts()}.
	 */
	@Test
	public void testGetContacts() {
		fail("Not yet implemented"); // TODO
	}

}
