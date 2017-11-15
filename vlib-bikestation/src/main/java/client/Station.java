package client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Station {

	public long number;
	public String name;
	public String address;
	public Position position;
	public boolean banking;
	public boolean bonus;
	public String status;
	public String contract_name;
	public long bike_stands;
	public long available_bike_stands;
	public long available_bikes;
	public long last_update;

	/**
	* No args constructor for use in serialization
	*
	*/
	public Station() {
	}

	/**
	*
	* @param position
	* @param bike_stands
	* @param status
	* @param address
	* @param last_update
	* @param name
	* @param available_bike_stands
	* @param banking
	* @param bonus
	* @param number
	* @param contract_name
	* @param available_bikes
	*/
	public Station(long number, String name, String address, Position position, boolean banking, boolean bonus, String status, String contract_name, long bike_stands, long available_bike_stands, long available_bikes, long last_update) {
		super();
		this.number = number;
		this.name = name;
		this.address = address;
		this.position = position;
		this.banking = banking;
		this.bonus = bonus;
		this.status = status;
		this.contract_name = contract_name;
		this.bike_stands = bike_stands;
		this.available_bike_stands = available_bike_stands;
		this.available_bikes = available_bikes;
		this.last_update = last_update;
	}

	public boolean containsInItsNumber(final long number) {
		return (this.number ==  number);
	}

	@Override
	public String toString() {
		return String.valueOf(number) + name + address + "\n\t/ Position: " + position.toString() + "\n\t/ Banking: " +String.valueOf(banking) + "\n\t/ Bonus: " + String.valueOf(bonus) + "\n\t/ Status: " + status + "\n\t/ Contract: " + contract_name + "\n\t/ Bikes: " + String.valueOf(bike_stands)  + "\n\t/ Available Bikes Stands: " + String.valueOf(available_bike_stands) + "\n\t/ Available Bikes: " + String.valueOf(available_bikes) + "\n\t/ Update: " + String.valueOf(last_update);
	}

	public long getAvailableBikes() {
		return this.available_bikes;
	}

	public long getAvailableBikeStands() {
		return this.available_bike_stands;
	}

}

