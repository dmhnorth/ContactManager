/**
 * 
 */
package ContactManagerTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ContactManager.Contact;
import ContactManager.ContactImpl;
import ContactManager.ContactManager;
import ContactManager.ContactManagerImpl;
import ContactManager.DataManager;
import ContactManager.IdGenerator;

/**
 * @author Dave
 *
 */
public class DataManagerTests {
	
	ContactManager cm;
	DataManager dm;


	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	
		IdGenerator idgen = new MockIdGeneratorImpl();
		
		dm = new DataManager();
		cm = new ContactManagerImpl(idgen, dm);	
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		
		cm = null;
		dm = null;
	}
	
	


	/*
	 * Tests the using the contact manager
	 */
	@Test
	public void flushPersistanceTestSave() {
		
		Contact contact1 = new ContactImpl();
		Contact contact2 = new ContactImpl();
		
		//set up a date
		Calendar future = new GregorianCalendar();
		future.add(Calendar.DATE, +10);
		
		//set up some contacts and create a set of them
		cm.addNewContact("Potato James", "Some notes about Ian.");
		cm.addNewContact("Paul Willy", "Some notes about Paul");
		Set<Contact> contacts = cm.getContacts(0, 1);
		
		//create some meetings
		cm.addFutureMeeting(contacts, future);
		cm.addNewPastMeeting(contacts, future, "text");
		
		//first contact put in array
		Contact[] contactArray = contacts.toArray(new Contact[0]);
		contact1 = contactArray[0];
		
		//save everything
		cm.flush();
		
		//close the cm
		cm = null;
		
		//set up and open a new cm
		IdGenerator idgen = new MockIdGeneratorImpl();
		cm = new ContactManagerImpl(idgen, dm);
		
		//load the first contact from this new cm into contact2
		Contact[] contactsLoaded = cm.getContacts("Potato James").toArray(new Contact[0]);
		contact2 = contactsLoaded[0];
		
		//check the first contact is the same as the previous one
		assertEquals(contact1.getName(), contact2.getName());
		assertEquals(contact1.getId(), contact2.getId());
				
	}	
}
