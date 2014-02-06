/**
 * 
 */
package ContactManager;

import java.io.Serializable;

/**
 * @author Dave
 *
 */
public class IdGeneratorImpl implements IdGenerator, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1257209297568770939L;
	private static int counter = -1;

	
	/* (non-Javadoc)
	 * @see ContactManager.IdGenerator#getNewId()
	 */
	@Override
	public int getNewId(){
		counter++;
		return counter;
	}
	
}
