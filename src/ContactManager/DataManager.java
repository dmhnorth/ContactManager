package ContactManager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
/**
 * 
 */
import java.util.Set;

/**
 * @author Dave
 *
 */
public class DataManager {
	
	final String FILENAME = "contacts.txt";
	ObjectOutputStream encode = null;
	ObjectInputStream decoder = null;
	
	/*
	 * Loads the data containers in order for ContactManagerImpl
	 */	
	public Object[] loadData() {
		
		
		try {
			decoder = new ObjectInputStream(
					new BufferedInputStream(
							new FileInputStream(FILENAME)));
		} catch (IOException e) {
            e.printStackTrace();
        }
		
		Map<String, Set<Contact>> contactMap = null;
		Map<Integer, Contact> idMap = null;
		Map<Integer, Meeting> meetingMap = null;
		IdGenerator idGenerator = null;

		try {
		contactMap = (Map<String, Set<Contact>>) decoder.readObject();
		idMap = (Map<Integer, Contact>) decoder.readObject();
		meetingMap = (Map<Integer, Meeting>) decoder.readObject();
		idGenerator = (IdGenerator) decoder.readObject();
		} catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		try {
			decoder.close();	//this line is optional
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new Object[]{contactMap, idMap, meetingMap, idGenerator};
	}
	
	
	
	
	/*
	 * Adds an object to the data to be saved
	 */
	public void saveData(Object ...obj) {

		try {
			encode = new ObjectOutputStream(
					new BufferedOutputStream(
							new FileOutputStream(FILENAME)));
		} catch (FileNotFoundException e) {
			System.err.println("encoding... " + e);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			for (Object o : obj) {
				encode.writeObject(o);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		try {
			encode.close();
		} catch (IOException ex2) {
			ex2.printStackTrace();
		}

	}


		/*
		 * closes the ObjectOutputStream encoder	
		 */
		public void close() {
			try {
			encode.close();
			} catch (IOException ex2) {
				ex2.printStackTrace();
			}
		}
}
