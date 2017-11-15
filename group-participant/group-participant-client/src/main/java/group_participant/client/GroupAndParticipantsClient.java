package group_participant.client;

import javax.naming.InitialContext;

import group_participant.api.GroupAndParticipants;

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
		try {
			InitialContext ic = new InitialContext();
			sb = (GroupAndParticipants) ic.lookup("group_participant.api.GroupAndParticipants");
			
			// Creation of a Participant
			System.out.println("Creating participant Participant1 ... " + sb.createParticipant("Participant1","Participant1"));
			System.out.println("Participant1 created OK");
			System.out.println("Creating participant Participant2 ... " + sb.createParticipant("Participant2","Participant2"));
			
			// Creation of a Group
			System.out.println("Creating group Group1 ... " + sb.createGroup("Group1"));
			
			// Adding a participant to a group by object
			System.out.println("Adding Participant1 to Group1 ... " + sb.addParticipantToGroup("Participant1", "Group1"));
			// Adding a participant to a group by name
			System.out.println("Adding Participant2 to Group1 ... " + sb.addParticipantToGroup("Participant2", "Group1"));
			
			// Listing all the participants of a group.
			System.out.println("Members of Group1 ... " + sb.getParticipants("Group1"));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
