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
	 * Tests saving and loading an array several times
	 */
	@Test
	public void flushPersistanceTestSingleLoadArray() {
		
		List<Integer> al = new ArrayList<Integer>();
		al.add(1);
		al.add(2);
		al.add(3);
		
		dm.saveData(al);
			
		dm.close();
		
		List<Integer> aSecondList = (List<Integer>) dm.loadData(al);
		
		assertTrue(aSecondList.get(0) == 1);
		assertTrue(aSecondList.get(2) == 3);
		
		aSecondList.add(4);
		dm.saveData(aSecondList);
		dm.close();
		
		List<Integer> aThirdList = (List<Integer>) dm.loadData(aSecondList);
		assertTrue(aThirdList.get(3) == 4);		
		
	}
	
	/*
	 * Tests saving and loading a contact list
	 */
	@Test
	public void flushPersistanceTestSingleLoadContact() {
		
		Set<Contact> contacts = new HashSet<Contact>();
		Contact person1 = new ContactImpl("Person1", 1);
		Contact person2 = new ContactImpl("Person2", 2);
		contacts.add(person1);
		contacts.add(person2);
		
		dm.saveData(contacts);
		dm.close();
				
		HashSet<Contact> contactsReload = (HashSet<Contact>) dm.loadData(contacts);
		
		Contact[] contactArray = contacts.toArray(new Contact[0]);
		Contact contact1 = contactArray[0];
		
		Contact[] contactArrayLoad = contactsReload.toArray(new Contact[0]);
		Contact contact2 = contactArray[0];
		
		
		assertEquals(contact1, contact2);		
		
	}
	
	/*
	 * Tests saving and loading two entirely different objects
	 */
	@Test
	public void flushPersistanceTestDoubleLoad() {
		
		List<Integer> al = new ArrayList<Integer>();
		al.add(1);
		al.add(2);
		al.add(3);
		
		Set<Contact> contacts = new HashSet<Contact>();
		Contact person1 = new ContactImpl("Person1", 1);
		Contact person2 = new ContactImpl("Person2", 2);
		contacts.add(person1);
		contacts.add(person2);
		
		dm.saveData(al, contacts);
		dm.close();
		
		
		HashSet<Contact> contactsReload = (HashSet<Contact>) dm.loadData(contacts);
		List<Integer> arrayReload = (List<Integer>) dm.loadData(al);
		
		Contact[] contactArray = contacts.toArray(new Contact[0]);
		Contact contact1 = contactArray[0];
		
		Contact[] contactArrayLoad = contactsReload.toArray(new Contact[0]);
		Contact contact2 = contactArray[0];
		
		
		assertEquals(contact1, contact2);
		assertTrue(arrayReload.get(0) == 1);
		assertTrue(arrayReload.get(2) == 3);
		
		
	}

	/*
	 * Tests the using the contact manager
	 *
	@Test
	public void flushPersistanceTestSave() {
		
		Calendar future = new GregorianCalendar();
		future.add(Calendar.DATE, +10);
				
		cm.addNewContact("Potato James", "Some notes about Ian.");
		cm.addNewContact("Paul Willy", "Some notes about Paul");
		Set<Contact> contacts = cm.getContacts(0, 1);
		
		cm.addFutureMeeting(contacts, future);
		cm.addNewPastMeeting(contacts, future, "text");
		
		
		cm.flush();
		
		
	}
	 */

}
