package client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Position {

	public double lat;
	public double lng;

	/**
	* No args constructor for use in serialization
	*
	*/
	public Position() {
	}

	/**
	*
	* @param lng
	* @param lat
	*/
	public Position(double lat, double lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}

	@Override
	public String toString() {
		String out = "";
		return out + " Lat " + String.valueOf(lat) + " - Lng " + String.valueOf(lng);
	}

}
