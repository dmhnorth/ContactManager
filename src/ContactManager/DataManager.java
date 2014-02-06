package ContactManager;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Scanner;
/**
 * 
 */
import java.util.Set;

/**
 * @author Dave
 *
 */
public class DataManager {
	
	final String FILENAME = "contacts.xml";
	XMLEncoder encode = null;
	XMLDecoder decoder = null;
	Scanner sc = null;
	
	/*
	 * Loads an object from the data VERIOSN2
	 */	
	public Object[] loadData() {
		
		Map<String, Set<Contact>> contactMap;
		Map<Integer, Contact> idMap;
		Map<Integer, Meeting> meetingMap;
		IdGenerator idGenerator;
		
		try {
			decoder = new XMLDecoder(
					new BufferedInputStream(
							new FileInputStream(FILENAME)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		contactMap = (Map<String, Set<Contact>>) decoder.readObject();
		idMap = (Map<Integer, Contact>) decoder.readObject();
		meetingMap = (Map<Integer, Meeting>) decoder.readObject();
		idGenerator = (IdGenerator) decoder.readObject();
		
		decoder.close();	//this line is optional
		
		return new Object[]{contactMap, idMap, meetingMap, idGenerator};
	}
	
	
	
	
	/*
	 * Adds an object to the data to be saved
	 */
	public void saveData(Object ...obj) {
		try {
			encode = new XMLEncoder(
					new BufferedOutputStream(
							new FileOutputStream(FILENAME)));
		} catch (FileNotFoundException e) {
			System.err.println("encoding contact manager... " + e);
		}
		for (Object o : obj) {
			encode.writeObject(o);
		};
	}


	/*
	 * closes the XMLEncoder	
	 */
	public void close() {
		
		encode.close();
		
	}
}
