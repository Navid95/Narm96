package DAOs;

import users.Driver_Rate;
import users.Rate;

public interface Driver_Rate_DAO {
	
	public Driver_Rate CreateNew(Rate Rate);
	
	public Driver_Rate Show(int id);
	
	public Driver_Rate EditRate(Rate Rate , int id);
	
	public boolean Delete(int id);
}
