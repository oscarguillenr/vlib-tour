package enterprise.entity_bean_entity;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
* Class thats represent a group of participants in a Tour.
*/
@Entity
public class Group implements Serializable {
	/**
	 * the serial number.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * the identifier of the group.
	 */
	private int gid;
	/**
	 * the name of the group.
	 */
	private String name;

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
	public int getGid() {
		return gid;
	}

	/**
	 * sets the identifier.
	 * 
	 * @param gid the new identifier.
	 */
	public void setGid(final int gid) {
		this.gid = gid;
	}

	/**
	 * gets the name of the group.
	 * @return string of the name of the group.
	 */
	public String getName() {
		return name;
	}

	/**
	 * sets the name of the group.
	 * 
	 * @param name the new name.
	 */
	public void setName(final String name) {
		this.name = name;
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
