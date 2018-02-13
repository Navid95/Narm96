package DAOs;

import java.util.List;

import Trip.Payment;

public interface Payment_DAO {
	
//	public Payment CreateNew(Payment payment);
	
	public Payment CreateNew(double Price , boolean State);
	
	public Payment Show(int id);
	
	public Payment EditPrice(double Price , int id);
	
	public Payment Pay(int id);
	
	public boolean Delete(int id);

	public Payment CreateNewEmpty();
	
	public List<Payment> SearchFalseStates();
	
}
