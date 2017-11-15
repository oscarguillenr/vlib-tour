package group_participant.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

/**
 * Participant in a Group.
 **/
@Entity
public class Participant implements Serializable {
	/**
	 * the serial number.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * the identifier of the participant.
	 */
	private String pid;
	/**
	 * the pseudo of the customer.
	 **/
	private String pseudo;
	/**
	 * the current location of the participant.
	 **/
	private int currentLocation;

	/**
	 * whether the participant arrived to the POI.
	 **/
	private boolean arrivedAtPOI = false; 

	/**
	 * the group of the the participant belongs to.
	 **/
	private Group group;

	/**
	 * gets the identifier.
	 * 
	 * @return the identifier.
	 */
	@Id
	@Column(name = "PARTICIPANT_ID")
	public String getPid() {
		return pid;
	}

	/**
	 * sets the identifier.
	 * 
	 * @param pid the new identifier.
	 */
	public void setPid(final String pid) {
		this.pid = pid;
	}

	/**
	 * gets the pseudo of the participant.
	 * 
	 * @return the pseudo.
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * sets the pseudo of the participant.
	 * 
	 * @param pseudo of the participant.
	 */
	public void setPseudo(final String pseudo) {
		this.pseudo = pseudo;
	}

	/**
	 * Indicate wheter the participant arrived to the POI.
	 * 
	 * @return true if the participant arrived.
	 */
	public boolean getArrivedAtPOI() {
		return arrivedAtPOI;
	}

	/**
	 * sets whether the participant arrived to the POI.
	 * 
	 * @param arrived true if the partipant arrived.
	 */
	public void setArrivedAtPOI(final boolean arrived) {
		this.arrivedAtPOI = arrived;
	}

	/**
	 * gets the current location of the participant.
	 * 
	 * @return the location.
	 */
	public int getCurrentLocation() {
		return currentLocation;
	}

	/**
	 * sets the current location of the participant.
	 * 
	 * @param currentLocation of the participant.
	 */
	public void setCurrentLocation(final int currentLocation) {
		this.currentLocation = currentLocation;
	}

	/**
	 * gets the group.
	 *
	 * @return the group.
	 **/
	@ManyToOne()
	@JoinColumn(name = "GROUP_ID")
	public Group getGroup() {
		return group;
	}

	/**
	 * sets the group.
	 * 
	 * @param group the group.
	 */
	public void setGroup(final Group group)  {
		this.group = group;
	}

}
