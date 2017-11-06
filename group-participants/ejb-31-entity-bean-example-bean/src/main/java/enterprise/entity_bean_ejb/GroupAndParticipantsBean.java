package enterprise.entity_bean_ejb;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import enterprise.entity_bean_entity.Participant;
import enterprise.entity_bean_entity.Group;

import enterprise.entity_bean_api.GroupAndParticipants;

/**
 * The stateless session bean.
 */
@Stateless
public class GroupAndParticipantsBean implements GroupAndParticipants {

	/**
	 * the reference to the entity manager, which persistence context is "pu1".
	 */
	@PersistenceContext(unitName = "pu1")
	private EntityManager em;


	/**
	 * Create a group.
	 * @param name of the group.
	 * @return the group object created.
	 */
	@Override
	public Group createGroup(final String name) {

		Group group = new Group();
		group.setGid(1);
		group.setName(name);
		return group;
	}

	/**
	 * Create a participant.
	 * @param pseudo of the participant.
	 * @return the participant object created.
	 */
	@Override
	public Participant createParticipant(final String pseudo) {
		Participant participant = new Participant();
		participant.setPid(1);
		participant.setPseudo(pseudo);
		participant.setCurrentLocation(1);
		return participant;
	}

	/**
	 * Add a Participant to the Group.
	 * @param participant to be added.
	 * @param group to add in.
	 * @return the string "OK" if there is no problem.
	 */
	@Override
	public String addParticipantByObject(final Participant participant, final Group group) {
		group.getParticipants().add(participant);
		participant.setGroup(group);
		return "OK";
	}	

	/**
	 * Add a Participant to the Group.
	 * @param pseudo of the participan to be added.
	 * @param name of the group to add in.
	 * @return the string "OK" if there is no problem.
	 */
	@Override
	public String addParticipantByName(final String pseudo, final String name) {
		Participant participant = createParticipant(pseudo);
		Group group = createGroup(name);
		group.getParticipants().add(participant);
		participant.setGroup(group);
		return "OK";
	}

	/**
	 * Get all the partipants.
	 * @param name of the group whose members will be listed.
	 * @return collection of participants in the group.
	 */
	public Collection<Participant> getParticipants(final String name) {
		Query q = em.createQuery("select participants from Group g where g.name = :name");
		q.setParameter("name", name);
		return (Collection<Participant>) q.getSingleResult();
	}

}
