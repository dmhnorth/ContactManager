/**
 * 
 */
package ContactManager;

import java.util.Calendar;
import java.util.Set;

/**
 * @author Dave
 *
 */
public class MeetingImpl implements Meeting {
	
	private Calendar dateOfMeeting;
	
	
	public MeetingImpl(int year, int month, int date, int hourOfDay, int minute){
		//uses the parameters from user of the set function within Calendar on javaDoc
	}
	
	
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Calendar getDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Contact> getContacts() {
		// TODO Auto-generated method stub
		return null;
	}

}
