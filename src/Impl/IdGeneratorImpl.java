/**
 * 
 */
package Impl;

import java.io.Serializable;

import Interfaces.IdGenerator;

public class IdGeneratorImpl implements IdGenerator, Serializable {
	
	private static final long serialVersionUID = -1257209297568770939L;
	private static int counter = 0;

	@Override
	public int getNewId(){
		return counter++;
	}
	
}
