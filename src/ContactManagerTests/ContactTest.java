/**
 * 
 */
package ContactManagerTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ContactManager.ContactImpl;

/**
 * @author Dave
 *
 */
public class ContactTest {

	ContactImpl contactA;
	
	
	@Before
	public void setUp(){
		contactA = new ContactImpl("David North", 0);
	}
	
	
	/**
	 * Test method for {@link ContactManager.ContactImpl#getId()}.
	 */
	@Test
	public void testGetId() {
		assertEquals(contactA.getId(), 0);
	}

	/**
	 * Test method for {@link ContactManager.ContactImpl#getName()}.
	 */
	@Test
	public void testGetName() {		
		assertEquals("Name not found", contactA.getName(), "David North");
	}

	/**
	 * Test method for {@link ContactManager.ContactImpl#getNotes()}.
	 */
	@Test
	public void testGetEmptyNotes() {
		assertEquals(contactA.getNotes(), "");
	}

	/**
	 * Test method for {@link ContactManager.ContactImpl#addNotes(java.lang.String)}.
	 */
	@Test
	public void testAddNotes() {
		contactA.addNotes("New note");
		assertEquals(contactA.getNotes(), "New note");
		contactA.addNotes("; Another");
		assertEquals(contactA.getNotes(), "New note; Another");
	}

}
