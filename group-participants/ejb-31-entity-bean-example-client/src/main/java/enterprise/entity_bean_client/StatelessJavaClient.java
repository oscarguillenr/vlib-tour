
package enterprise.entity_bean_client;

import javax.naming.InitialContext;

import enterprise.entity_bean_entity.Customer;

import enterprise.entity_bean_api.StatelessSession;

/**
 * The class of the client.
 */
public final class StatelessJavaClient {
	/**
	 * utility class with no instance.
	 */
	private StatelessJavaClient() {
	}

	/**
	 * the main of the client.
	 * 
	 * @param args
	 *            no command line arguments.
	 */
	public static void main(final String[] args) {
		StatelessSession sb;
		Customer c;
		try {
			InitialContext ic = new InitialContext();
			sb = (StatelessSession) ic.lookup("enterprise.entity_bean_api.StatelessSession");
			System.out.println("Inserting Customer and Orders... " + sb.testInsert());
			// Test query and navigation
			System.out.println("Verifying that all are inserted... " + sb.verifyInsert());
			// Get a detached instance
			c = sb.findCustomer("Bat Man");
			// Remove entity
			System.out.println("Removing entity... " + sb.testDelete(c));
			// Query the results
			System.out.println("Verifying that all are removed... " + sb.verifyDelete());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
