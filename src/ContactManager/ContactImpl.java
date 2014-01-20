package ContactManager;

public class ContactImpl implements Contact {

	private int id;
	private String name;
	private String notes = "No notes yet.";
	
	/*
	 * basic constructor
	 */
	public ContactImpl(String name){
		this.name = name;
		this.id = Math.abs(name.hashCode()%10000);
	}
	
	/*
	 * Contact constructor if you would like to add notes immediately.
	 */
	public ContactImpl(String name, String note){
		this.name = name;
		this.id = Math.abs(name.hashCode()%10000);
		this.notes = note;
	}
	
	/*
	 * returns the Contacts Id
	 */
	@Override
	public int getId() {
		return id;
	}

	/*
	 * returns the Contacts name as a String
	 */
	@Override
	public String getName() {		
		return name;
	}

	/*
	 * returns the Contacts notes as a String
	 */
	@Override
	public String getNotes() {		
		return notes;
	}

	/*
	 * Used to add notes to the meeting in String format
	 */
	@Override
	public void addNotes(String note) {
		if (notes.equals("No notes yet.")) {
			notes = note;
		} else {
			notes += "; " + note;
		}
		
	}

}