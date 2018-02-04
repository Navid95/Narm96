package Trip;

public class Trip {

	private String Payment;
	private Location Location;
	private Rate Rate;

	public Trip(Location Location) {
		this.Location = Location;
	}

	private void EditTrip(String Payment, Location Location) {
		this.Location = Location;
		this.Payment = Payment;
	}

	public String getPayment() {
		return Payment;
	}

	public void setPayment(String payment) {
		Payment = payment;
	}

	public Location getLocation() {
		return Location;
	}

	public void setLocation(Location location) {
		Location = location;
	}

	public Rate getRate() {
		return Rate;
	}

	public void setRate(Rate rate) {
		Rate = rate;
	}

}
