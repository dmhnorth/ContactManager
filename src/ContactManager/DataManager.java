package ContactManager;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;
/**
 * 
 */

/**
 * @author Dave
 *
 */
public class DataManager {
	
	final String FILENAME = "save_data.xml";
	XMLEncoder encode = null;
	XMLDecoder decoder = null;
	Scanner sc = null;
	
	/*
	 * Loads an object from the data
	 */	
	public Object loadData(Object obj) {
		
		/*
		 * purely to print out things
		try {
			sc = new Scanner(
					new BufferedInputStream(
							new FileInputStream(FILENAME)));
		} catch (FileNotFoundException e) {
			System.err.println("reading... " + e);
		}
		
		while (sc.hasNext())
				System.out.println(sc.next());
		
		sc.close();
		*/
		
		
		try {
			decoder = new XMLDecoder(
					new BufferedInputStream(
							new FileInputStream(FILENAME)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Object result = (Object) decoder.readObject();
		
//		decoder.close();
		return result;
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
