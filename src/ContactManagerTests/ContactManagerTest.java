/**
 * 
 */
package ContactManagerTests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ContactManager.Contact;
import ContactManager.ContactManager;
import ContactManager.ContactManagerImpl;
import ContactManager.EmptyContactException;

/**
 * @author Dave
 *
 */
public class ContactManagerTest {

	String name = "David North";
	String notes = "Some notes";
	ContactManager cm;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		cm = new ContactManagerImpl();
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
		
		Set<Contact> sameNameSet = cm.getContacts(0, 1);
		assertTrue(sameNameSet.size() == 2);
	}
	
}
