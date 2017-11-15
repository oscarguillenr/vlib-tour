package group_participant.bean;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import group_participant.api.GroupAndParticipants;
import group_participant.entity.Group;
import group_participant.entity.Participant;

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
	 * @param gid of the group.
	 * @return the group object created.
	 */
	@Override
	public String createGroup(final String gid) {

		Group group = new Group();
		group.setGid(gid);
		em.persist(group);
		return "OK";
	}

	/**
	 * Create a participant.
	 * @param pid of the participant.
	 * @param pseudo of the participant.
	 * @return the participant object created.
	 */
	@Override
	public String createParticipant(final String pid, final String pseudo) {
		Participant participant = new Participant();
		participant.setPid(pid);
		participant.setPseudo(pseudo);
		participant.setCurrentLocation(1);
		em.persist(participant);
		return "OK";
	}

	/**
	 * Add a Participant to the Group.
	 * @param pid of the participant to be added.
	 * @param gid of the group to add in.
	 * @return the string "OK" if there is no problem.
	 */
	@Override
	public String addParticipantToGroup(final String pid, final String gid) {
		Query q = em.createQuery("select g from Group g where g.gid = :gid");
		q.setParameter("gid", gid);
		List results = q.getResultList();
		if (results == null || results.size() == 0) {
			throw new RuntimeException("group doesn't exist.");
		}
		Group group = (Group) q.getSingleResult();
		
		q = em.createQuery("select p from Participant p where p.pid = :pid");
		q.setParameter("pid", pid);
		results = q.getResultList();
		if (results == null || results.size() == 0) {
			throw new RuntimeException("participant doesn't exist.");
		}
		Participant participant = (Participant) q.getSingleResult();
		group.getParticipants().add(participant);
		participant.setGroup(group);
		return "OK";
	}	

	/**
	 * Get all the partipants.
	 * @param gid of the group whose members will be listed.
	 * @return collection of participants in the group.
	 */
	@Override
	public Collection<Participant> getParticipants(final String gid) {
		Query q = em.createQuery("select g from Group g where g.gid = :gid");
		q.setParameter("gid", gid);
		Group group = (Group) q.getSingleResult();
		Collection<Participant> participants = group.getParticipants();
		return participants;
	}

}
