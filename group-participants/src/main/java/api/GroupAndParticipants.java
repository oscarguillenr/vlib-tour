package api; 

import javax.ejb.Remote;

import java.util.Collection;

import entity.Group;
import entity.Participant;

/**
 * The API of the entity bean.
 */
@Remote
public interface GroupAndParticipants {
	
	/**
	 * Create a group.
	 * @param name of the group.
	 * @return the group object created.
	 */
	Group createGroup(String name);

	/**
	 * Create a participant.
	 * @param pseudo of the participant.
	 * @return the participant object created.
	 */
	Participant createParticipant(String pseudo);

	/**
	 * Add a Participant to the Group.
	 * @param participant to be added.
	 * @param group to add in.
	 * @return the string "OK" if there is no problem.
	 */
	String addParticipantByObject(Participant participant, Group group);

	/**
	 * Add a Participant to the Group.
	 * @param pseudo of the participan to be added.
	 * @param name of the group to add in.
	 * @return the string "OK" if there is no problem.
	 */
	String addParticipantByName(String pseudo, String name);

	/**
	 * Get all the partipants.
	 * @param name of the group whose members will be listed.
	 * @return collection of participants in the group.
	 */
	Collection<Participant> getParticipants(String name);
}
