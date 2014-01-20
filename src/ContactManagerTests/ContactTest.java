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

	ContactImpl contactA = new ContactImpl("David North");
	
	/**
	 * Test method for {@link ContactManager.ContactImpl#getId()}.
	 */
	@Test
	public void testGetId() {
		assertEquals("ID incorrect", contactA.getId(), Math.abs(contactA.getName().hashCode()%10000));
		System.out.println(contactA.getId());
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
	public void testGetNotes() {
		assertEquals("Notes not found", contactA.getNotes(), "No notes yet.");
	}

	/**
	 * Test method for {@link ContactManager.ContactImpl#addNotes(java.lang.String)}.
	 */
	@Test
	public void testAddNotes() {
		contactA.addNotes("New note");
		assertEquals("Notes not added", contactA.getNotes(), "New note");
		contactA.addNotes("Another");
		assertEquals("Notes not added", contactA.getNotes(), "New note; Another");
	}

}
