package group_participant.entity;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
* Class thats represent a group of participants in a Tour.
*/
@Entity
@Table(name = "GROUP_TABLE")
public class Group implements Serializable {
	/**
	 * the serial number.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * the identifier of the group.
	 */
	private String gid;

	/**
	 * whether all the participants in the group arrived to the POI.
	 */
	private boolean arrivedAtPOI = false;

	/**
	 * The collection of participants in the group.
	*/
	private Collection<Participant> participants = new ArrayList<Participant>();

	/**
	 * gets the identifier.
	 * 
	 * @return the identifier.
	 */
	@Id
	@Column(name = "GROUP_ID")
	public String getGid() {
		return gid;
	}

	/**
	 * sets the identifier.
	 * 
	 * @param gid the new identifier.
	 */
	public void setGid(final String gid) {
		this.gid = gid;
	}

	/**
	 * Indicate whether all the members of the group
	 * arrived to the POI.
	 * @return true if all the participants arrived.
	 */
	public boolean getArrivedAtPOI() {
		return arrivedAtPOI;
	}

	/**
	 * Set whether all the participants of the group
	 * arrived to the POI.
	 * 
	 * @param arrived true if all of the participan arrived.
	 */
	public void setArrivedAtPOI(final boolean arrived) {
		this.arrivedAtPOI = arrived;
	}

	/**
	 * gets the collection of participants.
	 * 
	 * @return the collection.
	 */
	@OneToMany(cascade = ALL, mappedBy = "group")
	public Collection<Participant> getParticipants() {
		return participants;
	}

	/**
	 * sets the collection of participants.
	 * 
	 * @param newValue the new collection.
	 */
	public void setParticipants(final Collection<Participant> newValue) {
		this.participants = newValue;
	}

}
