package users;

public enum Rate {

	star_05(0.5), star_1(1), star_15(1.5), star_2(2), star_25(2.5), star_3(3), star_35(3.5), star_4(4), star_45(
			4.5), star_5(5);

	private double rate;

	private Rate(double rate) {

		this.rate = rate;

	}
	
	public double getRate() {
		return this.rate;
	}

	public static Rate calculateRate(double rate) {

		if (rate <= star_05.getRate()) {
			
			return Rate.star_05;
			
		} else if (star_05.getRate() < rate && rate <= star_1.getRate()) {
			
			return star_1;
			
		} else if (star_1.getRate() < rate && rate <= star_15.getRate()) {
			
			return star_15;
			
		} else if (star_15.getRate() < rate && rate <= star_2.getRate()) {
			
			return star_2;
			
		} else if (star_2.getRate() < rate && rate <= star_25.getRate()) {
			
			return star_25;
			
		} else if (star_25.getRate() < rate && rate <= star_3.getRate()) {
			
			return star_3;
			
		} else if (star_3.getRate() < rate && rate <= star_35.getRate()) {
			
			return star_35;
			
		} else if (star_35.getRate() < rate && rate <= star_4.getRate()) {
			
			return star_4;
			
		} else if (star_4.getRate() < rate && rate <= star_45.getRate()) {
			
			return star_45;
			
		} else if (star_45.getRate() < rate) {
			
			return star_5;
		}
		return star_35;
	}

}
