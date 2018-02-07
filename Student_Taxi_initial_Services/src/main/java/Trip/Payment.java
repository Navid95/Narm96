package Trip;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Payment {

	private int id;
	private boolean State;
	private double Price;

	public Payment() {

		State = false;

		Price = 0;

	}
	
	@JsonProperty("id")
	public void setId(int id) {
		this.id = id;
	}
	
	@JsonProperty("State")
	public void setState(boolean state) {
		
		this.State = state;
		
	}

	@JsonProperty("Price")
	public void setPrice(double price) {
		
		this.Price = price;
		
	}

	public int getId() {
		
		return this.id;
		
	}
	
	public boolean getState() {

		return this.State;

	}

	public double getPrice() {
		return this.Price;
	}
}
