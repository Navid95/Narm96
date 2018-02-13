package Trip;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Trip {

	private int id;
	
	private Payment Payment;

	private Location Location;
	
	private int Sits;
	
	private double SitPrice;
	
	public Trip() {
	}

	public Trip(Location Location , int sits) {
		this.Location = Location;
		this.Sits = sits;
	}
	
	public void calculatePaymentPrice() {
		this.Payment.setPrice(SitPrice*Sits);
	}
	
	public int getId() {
		return id;
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
	
	public double getSitPrice() {
		return SitPrice;
	}
	
	@JsonProperty("Sits")
	public void setSits(int sits) {
		Sits = sits;
	}
	@JsonProperty("SitPrice")
	public void setSitPrice(double price) {
		SitPrice = price;
	}
	
	@JsonProperty("Payment")
	public void setPayment(Payment payment) {
		Payment = payment;
	}

	@JsonProperty("Location")
	public void setLocation(Location location) {
		Location = location;
	}
	
	@JsonProperty("id")
	public void setId(int id) {
		this.id = id;
	}

}
