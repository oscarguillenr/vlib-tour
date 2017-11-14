package enterprise.entity_bean_entity;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.persistence.*;

/**
 * The entity.
 */
@Entity
@Table(name="TOURS")
public class Tour implements Serializable {
	/**
	 * the identifier of the tour.
	 */
    @Id
    @Column(name = "TOUR_ID")
	private String tid;
	/**
	 * the name of the tour.
	 */
	private String name;
	/**
	 * the description of the tour.
	 */
	private String description;
	/**
	 * the collection of pois.
	 */
	private Collection<POI> pois = new ArrayList<POI>();
	
	/**
	 * gets the identifier.
	 * 
	 * @return the identifier.
	 */
	public String getId() {
		return tid;
	}

	/**
	 * sets the identifier.
	 * 
	 * @param id
	 *            the new identifier.
	 */
	public void setId(final String id) {
		this.tid = id;
	}

	/**
	 * gets the name.
	 * 
	 * @return the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * sets the name.
	 * @param name
	 *            the new name.
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * gets the description.
	 * 
	 * @return the description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * sets the description.
	 * @param description
	 *            the new description.
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * gets the collection of POIs.
	 * 
	 * @return the collection.
	 */
	@ManyToMany(mappedBy = "tours")
    @JoinTable(name = "TOURS_POIS",
        joinColumns = 
            @JoinColumn(name = "DB_TOUR_ID",
                referencedColumnName = "TOUR_ID"),
        inverseJoinColumns = 
            @JoinColumn(name = "DB_POI_ID",
                referencedColumnName = "POI_ID"))
	public Collection<POI> getPOIs() {
    	return pois;
	}

	/**
	 * sets the collection of pois.
	 * 
	 * @param newValue
	 *            the new collection.
	 */
	public void setPOIs(final Collection<POI> newValue) {
		this.pois = newValue;
	}

    /**
     * printable version.
     * 
     * @return string representation of the tour.
     */
    public String toString() {
        String out = tid + " " + name;
        for (Iterator<POI> iterator = pois.iterator(); iterator.hasNext();) {
            out += "\n    -- > " + iterator.next().toString();
        }
        return out;
    }

    /**
     * equals.
     * 
     * @return if are equals.
     */
    public boolean equals(Object object) {
		if (object instanceof Tour) {
		  Tour otherId = (Tour) object;
		  return (new String(otherId.tid).equals(this.tid));
		}
		return false;
	}
}
