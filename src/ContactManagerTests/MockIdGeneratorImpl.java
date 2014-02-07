package ContactManagerTests;

import java.io.Serializable;

import Interfaces.IdGenerator;

public class MockIdGeneratorImpl implements IdGenerator, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1942164862752447264L;
	private int counter = 0;	
	
	@Override
	public int getNewId() {
		return counter++;
	}

}
