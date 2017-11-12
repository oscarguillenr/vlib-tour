package vlibtour.communicationsystem;

/**
 * the API of the service.
 */
public interface CommunicationSystemService {

	/**  
	 * Send a message to the group.		
	 *
	 * @param receiverId of the participant that receives the message.
	 *		all for message broadcast.
	 * 	    userId for a specific user.
	 * @param message to be publish
	 * 
	 */
	void publishMessage(final String receiverId, final String message);
	
	/**  
	 * Receives messages that arrive to the queue.		
	 *
	 */
	void receiveMessage();

}
