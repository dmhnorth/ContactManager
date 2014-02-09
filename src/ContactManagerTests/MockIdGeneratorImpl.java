package ContactManagerTests;

import java.io.Serializable;

import Interfaces.IdGenerator;

public class MockIdGeneratorImpl implements IdGenerator, Serializable {

	/**
	 * A MockIdGenerator built in such a way that IDs are predictable.
	 * Used purely during testing due to counter reset between instantiations.
	 */
	private static final long serialVersionUID = 1942164862752447264L;
	private int counter = 0;	
	
	@Override
	public int getNewId() {
		return counter++;
	}

}
