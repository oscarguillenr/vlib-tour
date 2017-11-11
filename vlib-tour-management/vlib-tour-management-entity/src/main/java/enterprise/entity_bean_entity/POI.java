package enterprise.entity_bean_entity;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

/**
 * The entity.
 */
@Entity
@Table(name="POIS")
public class POI implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * the identifier of the POI.
	 */
    @Id
    @Column(name = "POI_ID")
	private String poid;
	/**
	 * name of the POI.
	 */
	private String name;
    /**
     * description of the POI.
     */
    private String description;
    /**
     * location of the POI.
     */
    private GPSPosition gpslocation;

	/**
	 * tours
	 */
	@ManyToMany(mappedBy="pois", cascade=CascadeType.ALL)
    private Collection<Tour> tours = new ArrayList<Tour>();

	/**
	 * gets the identifier.
	 * 
	 * @return the identifier.
	 */
	public String getId() {
		return poid;
	}

	/**
	 * sets the identifier.
	 * 
	 * @param id
	 *            the new identifier.
	 */
	public void setId(final String id) {
		this.poid = id;
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
	 * 
	 * @param name
	 *            the name.
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
     * 
     * @param description
     *            the description.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * gets the gpslocation.
     * 
     * @return the gpslocation.
     */
    public GPSPosition getGpslocation() {
        return gpslocation;
    }

    /**
     * sets the gpslocation.
     * 
     * @param gpslocation
     *            the gpslocation.
     */
    public void setGpslocation(final GPSPosition gpslocation) {
        this.gpslocation = gpslocation;
    }

	/**
	 * gets the tours.
	 * 
	 * @return the tours.
	 */
	public Collection<Tour> getTours() {
		return tours;
	}

	/**
	 * sets the tours.
	 * 
	 * @param tours
	 *            the new tours.
	 */
	public void setTours(final Collection<Tour> tours) {
		this.tours = tours;
	}

    /**
     * equals.
     * 
     * @return if are equals.
     */
    public boolean equals(Object object) {
        if (object instanceof POI) {
          POI otherId = (POI) object;
          return (new String(otherId.poid).equals(this.poid));
        }
        return false;
    }
}	
