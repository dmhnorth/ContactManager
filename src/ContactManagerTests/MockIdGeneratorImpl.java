package ContactManagerTests;

import ContactManager.IdGenerator;

public class MockIdGeneratorImpl implements IdGenerator {

	private int counter = -1;	
	
	@Override
	public int getNewId() {
		
		counter++;
		
		return counter;
	}

}
