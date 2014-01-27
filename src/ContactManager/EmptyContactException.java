/**
 * 
 */
package ContactManager;

/**
 * @author Dave
 *
 */
public class EmptyContactException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyContactException() {
		super("The Contact list passed in was empty/null. Meeting not created");
	}
	
}
