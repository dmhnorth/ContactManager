/**
 * 
 */
package Impl;

import java.io.Serializable;

import Interfaces.IdGenerator;

/**
 * This implementation of IdGenerator uses a static variable as a counter.
 * For use within the production code.
 */
public class IdGeneratorImpl implements IdGenerator, Serializable {
	
	private static final long serialVersionUID = -1257209297568770939L;
	private static int counter = 0;

	@Override
	public int getNewId(){
		return counter++;
	}
}