package client;

import javax.naming.InitialContext;

import entity.Participant;
import entity.Group;

import api.GroupAndParticipants;

/**
 * The class of the client.
 */
public final class GroupAndParticipantsClient {
	/**
	 * utility class with no instance.
	 */
	private GroupAndParticipantsClient() {
	}

	/**
	 * the main of the client.
	 * 
	 * @param args
	 *            no command line arguments.
	 */
	public static void main(final String[] args) {
		GroupAndParticipants sb;
		Participant p1;
		Group g1;
		try {
			InitialContext ic = new InitialContext();
			sb = (GroupAndParticipants) ic.lookup("enterprise.entity_bean_api.GroupAndParticipants");
			
			// Creation of a Participant
			System.out.println("Creating the participant Participant1 ");
			p1 = sb.createParticipant("Participant1");

			// Creation of a Group
			System.out.println("Creating the group Group1 ");
			g1 = sb.createGroup("Group1");
			
			// Adding a participant to a group by object
			System.out.println("Adding Participant1 to Group1 ... " + sb.addParticipantByObject(p1, g1));
			// Adding a participant to a group by name
			System.out.println("Adding Participant2 to Group1 ... " + sb.addParticipantByName("Participant2", "Group1"));
			
			// Listing all the participants of a group.
			System.out.println("Members of Group1 ... " + sb.getParticipants("Group1"));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
