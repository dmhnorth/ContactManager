package ContactManager;

public class ContactImpl implements Contact {

	private int id;
	private String name;
	private String notes = "No notes yet.";
	
	
	public ContactImpl(String name){
		this.name = name;
		this.id = Math.abs(name.hashCode()%10000);
	}
	
	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getName() {		
		return name;
	}

	@Override
	public String getNotes() {		
		return notes;
	}

	@Override
	public void addNotes(String note) {
		if (notes.equals("No notes yet.")) {
			notes = note;
		} else {
			notes += "; " + note;
		}
		
	}

}
