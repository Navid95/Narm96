package users;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Driver_Rate {
	
	private int id;
	private Rate Rate;
	
	public Driver_Rate() {
		
		Rate = Rate.star_3;
		
	}

	public Rate getRate() {
		return Rate;
	}
	
	public int getId() {
		return id;
	}
	
	@JsonProperty("Rate")
	public void setRate(Rate rate) {
		Rate = rate;
	}
	
	@JsonProperty("id")
	public void setId(int id) {
		this.id = id;
	}
	
}
