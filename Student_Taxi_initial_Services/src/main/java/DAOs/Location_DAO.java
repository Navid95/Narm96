package DAOs;

import Trip.Location;

public interface Location_DAO {
	
	public Location CreateNew(String origin , String destination);

	public Location EditLocation(String origin , String destination , int id);
	
	public Location Show(int id);
	
	public boolean Delete(int id);

}
