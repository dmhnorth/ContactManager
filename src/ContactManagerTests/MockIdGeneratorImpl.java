package ContactManagerTests;

import java.io.Serializable;

import ContactManager.IdGenerator;

public class MockIdGeneratorImpl implements IdGenerator, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1942164862752447264L;
	private int counter = -1;	
	
	@Override
	public int getNewId() {
		
		counter++;
		
		return counter;
	}

}
