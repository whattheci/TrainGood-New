package com.sehyeon.trainGood;

public class Sit {
	String _id;
	String trainnumber;
	String destination;
	String chairnumber;
	String occupieduser;
	String reservationuser;

	public Sit(String _id, String trainnumber, String dest, String chairNum) {
		this._id = _id;
		this.trainnumber = trainnumber;
		this.destination = dest;
		this.chairnumber = chairNum;
		this.occupieduser = "";
		this.reservationuser = "";
	}

	public Sit(String _id, String trainnumber, String destination, String chairnumber, String occupieduser,
			String reservationuser) {
		this._id = _id;
		this.trainnumber = trainnumber;
		this.destination = destination;
		this.chairnumber = chairnumber;
		this.occupieduser = occupieduser;
		this.reservationuser = reservationuser;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getTrainnumber() {
		return trainnumber;
	}

	public void setTrainnumber(String trainnumber) {
		this.trainnumber = trainnumber;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getChairnumber() {
		return chairnumber;
	}

	public void setChairnumber(String chairnumber) {
		this.chairnumber = chairnumber;
	}

	public String getOccupieduser() {
		return occupieduser;
	}

	public void setOccupieduser(String occupieduser) {
		this.occupieduser = occupieduser;
	}

	public String getReservationuser() {
		return reservationuser;
	}

	public void setReservationuser(String reservationuser) {
		this.reservationuser = reservationuser;
	}

	@Override
	public String toString() {
		return "[" + _id + ", " + trainnumber + ", " + destination + ", " + chairnumber + ", " + occupieduser + ", "
				+ reservationuser + "]";
	}
}
