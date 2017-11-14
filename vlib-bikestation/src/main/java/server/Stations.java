/**
This file is part of the course CSC5002.

Copyright (C) 2017 Télécom SudParis

This is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This software platform is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public License
along with the muDEBS platform. If not, see <http://www.gnu.org/licenses/>.

Initial developer(s): Chantal Taconet
Contributor(s): Denis Conan
 */
package server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Stations REST server.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Stations {
    /**
     * the collection of Stations.
     */
    @XmlElementWrapper(name = "stations")
    @XmlElement(name = "station")
    private List<Station> stations = new ArrayList<Station>();

    /**
     * default constructor.
     */
    public Stations() {
    }

    /**
     * constructs the object with the given collection of Stations.
     * 
     * @param Stations
     *            the collection of Stations.
     */
    public Stations(final List<Station> Stations) {
        this.stations = Stations;
    }

    /**
     * add a Station to the collection.
     * 
     * @param Station
     *            the Station to add.
     */
    public void add(final Station Station) {
        stations.add(Station);
    }
    /**
     * adds a Station to the collection.
     * 
     * @param Station
     *            the Station to add.
     */
    public void addStation(final Station Station) {
        stations.add(Station);
    }

    public int size() {
        return stations.size();
    }

    /**
     * searches for the stations with a given number.
     * 
     * @param number
     *            the number for the search.
     * @return the collection of stations matching the criteria.
     */
    public Station lookupNumber(final String number) {
        Station station;
        for (Iterator<Station> it = stations.iterator(); it.hasNext();) {
            station = it.next();
            if (station.containsInItsNumber(Long.parseLong(number))) {
                return station;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String output = "";
        for(Station station : stations) {
            output = output + station.toString();
        }
        return output;
    }
}