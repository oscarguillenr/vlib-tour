package group_participant.api; 

import javax.ejb.Remote;

import group_participant.entity.Participant;

import java.util.Collection;

/**
 * The API of the entity bean.
 */
@Remote
public interface GroupAndParticipants {
	
	/**
	 * Create a group.
	 * @param gid of the group
	 * @return the group object created.
	 */
	String createGroup(String gid);

	/**
	 * Create a participant.
	 * @param pid of the participant.
	 * @param pseudo of the participant.
	 * @return the participant object created.
	 */
	String createParticipant(String pid, String pseudo);

	/**
	 * Add a Participant to the Group.
	 * @param pid of the participant to be added.
	 * @param gid of the group to add in.
	 * @return the string "OK" if there is no problem.
	 */
	String addParticipantToGroup(String pid, String gid);

	/**
	 * Get all the partipants.
	 * @param gid of the group whose members will be listed.
	 * @return collection of participants in the group.
	 */
	Collection<Participant> getParticipants(String gid);
}
