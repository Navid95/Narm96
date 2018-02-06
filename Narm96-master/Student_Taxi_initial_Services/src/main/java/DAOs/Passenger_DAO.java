package DAOs;

import java.util.List;

import users.Passenger;

public interface Passenger_DAO {

	public Passenger CreateNew(Passenger passenger);

	public Passenger CreateNew(String FirstName, String LastName, String UserName, String PassWord, int StuNum,
			int NationalNum, String Gender);
	
	public Passenger Show(int id);
	
	public Passenger EditInfo(String FirstName, String LastName, String UserName, String PassWord, int StuNum,
			int NationalNum, String Gender , int id);
	
	public Boolean Delete(int id);
	
	public List<Passenger> showAll(int offset , int rowNum);

}
