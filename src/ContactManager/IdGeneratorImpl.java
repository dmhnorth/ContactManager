/**
 * 
 */
package ContactManager;

/**
 * @author Dave
 *
 */
public class IdGeneratorImpl implements IdGenerator {
	
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
