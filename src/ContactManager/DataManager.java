package ContactManager;

import java.util.Map;
import java.util.Set;

/**
 * 
 * An interface to create DataManagers that save and load a file for the
 * ContactManagerImpl class 
 * @author Dave
 *
 */
public interface DataManager {
	
	/**
	 * determines whether a data file exists for the contactManager save data
	 * 
	 * @return boolean in respect to whether the data file already exists
	 */
	public abstract boolean dataFileExists();

	/**
	 * loads the fields of a given ContactManager into an array of type Object
	 * 
	 * @return Object[]
	 */
	public abstract Object[] loadData();

	/**
	 * takes the contactMap, idMap, meetingMap and IdGenerator from a given
	 * ContactManager and saves it out to a .txt file
	 */
	public abstract void saveData(Map<String, Set<Contact>> contactMap,
			Map<Integer, Contact> idMap, Map<Integer, Meeting> meetingMap,
			IdGenerator idGenerator);

}