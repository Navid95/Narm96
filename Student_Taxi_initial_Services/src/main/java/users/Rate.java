package users;

public enum Rate {
	
	star_05(0.5), star_1(1), star_15(1.5), star_2(2), star_25(2.5), star_3(3), 
	star_35(3.5), star_4(4), star_45(4.5), star_5(5);

	private double rate;

	private Rate(double rate) {

		this.rate = rate;

	}
	
	public double getRate() {
		return this.rate;
	}

}
