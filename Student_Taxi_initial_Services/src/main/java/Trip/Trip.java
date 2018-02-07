package Trip;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Trip {

	private int id;
	
	private Payment Payment;

	private Location Location;
	
	private int Sits;

	public Trip() {
	}

	public Trip(Location Location , int sits) {

		this.Location = Location;
		this.Sits = sits;

	}
	
	public int getSits() {
		return this.Sits;
	}
	
	public Payment getPayment() {

		return Payment;
	}

	public Location getLocation() {
		return Location;
	}
	
	@JsonProperty("Sits")
	public void setSits(int sits) {
		Sits = sits;
	}
	
	@JsonProperty("Payment")
	public void setPayment(Payment payment) {

		Payment = payment;

	}

	@JsonProperty("Location")
	public void setLocation(Location location) {
		Location = location;
	}

}
