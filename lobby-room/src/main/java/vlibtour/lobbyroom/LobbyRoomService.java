package vlibtour.lobbyroom;

/**
 * the API of the service.
 */
public interface LobbyRoomService {

	/**  
	 *	Creates a group for a tour instance and joins it.		
	 *	
	 * @param groupId id of the group to join.
	 * @param userId id of the participant.
	 * 
	 * @return an URL, which is used to connect to the infrastructure for 
	 * 		    the group communication dedicated to the visit. 
	 */
	String createGroupAndJoinIt(String groupId, String userId);

	/** 
	 * Joins a group for a tour instance, that is a visit. 
	 * 
	 * @param groupId groupId id of the group to join.
	 * @param userId userId id of the participant.
	 *
	 * @return returns an URL, which is used to connect to the infrastructure 
	 * 		    for the dedicated group communication. 
	 */
	String joinAGroup(String groupId, String userId);

}
