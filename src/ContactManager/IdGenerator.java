/**
 * 
 */
package ContactManager;

/**
 * @author Dave
 *
 */
public class IdGenerator {
	
	private static int counter = -1;

	
	public int getNewId(){
		counter++;
		return counter;
	}
	
}
