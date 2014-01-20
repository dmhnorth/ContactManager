/**
 * 
 */
package ContactManager;

/**
 * @author Dave
 *
 */
public class EmptyContactException extends Exception {
	
	public EmptyContactException() {
		super("The Contact list passed in was empty/null. Meeting not created");
	}
	
}
