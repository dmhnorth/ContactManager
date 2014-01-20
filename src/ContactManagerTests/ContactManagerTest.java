/**
 * 
 */
package ContactManagerTests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ContactManager.Contact;
import ContactManager.ContactManager;
import ContactManager.ContactManagerImpl;

/**
 * @author Dave
 *
 */
public class ContactManagerTest {

	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void addNewContact() {
		ContactManager cm = new ContactManagerImpl();
		String name = "David North";
		String notes = "Some notes";
		cm.addNewContact(name, notes);
		Set<Contact> contactSet = cm.getContacts(name);
		
		Contact[] contacts = contactSet.toArray(new Contact[0]);
		Contact contact = contacts[0];
		
		assertEquals(contact.getName(), name);
		assertEquals(contact.getNotes(), notes);
		
	}
}
