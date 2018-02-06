package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import users.Driver;

public class DrivetTest {

	static String FirstName, LastName, UserName, PassWord, Gender, Car;
	static int StuNum, NationalNum, Role;
	static Driver driver;

	@BeforeClass
	public static void beforeClass() {

		FirstName = "John";
		LastName = "Doe";
		UserName = "john";
		PassWord = "1234";
		Gender = "Male";
		Car = "Toyota Yaris";
		StuNum = 123456789;
		NationalNum = 987654321;
		driver = new Driver(FirstName, LastName, UserName, PassWord, StuNum, NationalNum, Gender, Car);
	}

	@Test
	public void testGetFirstName() {
		assertEquals(FirstName, driver.getFirstName());
	}

	@Test
	public void testGetLastName() {
		assertEquals(LastName, driver.getLastName());
	}

	@Test
	public void testGetUserName() {
		assertEquals(UserName, driver.getUserName());
	}

	@Test
	public void testGetPassWord() {
		assertEquals(PassWord, driver.getPassWord());
	}

	@Test
	public void testGetGender() {
		assertEquals(Gender, driver.getGender());
	}

	@Test
	public void testGetStuNUm() {
		assertEquals(StuNum, driver.getStuNum());
	}

	@Test
	public void testGetNationalNum() {
		assertEquals(NationalNum, driver.getNationalNum());
	}

	@Test
	public void testGetCar() {
		assertEquals(Car, driver.getCar());
	}

	@Test
	public void testEditInfo() {
		String FirstName, LastName, UserName, PassWord, Gender, Car;
		int StuNum, NationalNum, Role;
		FirstName = "Jack";
		LastName = "Daniels";
		UserName = "jack";
		PassWord = "4321";
		Gender = "Male";
		Car = "Toyota corrolla";
		StuNum = 987654321;
		NationalNum = 123456789;
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.UserName = UserName;
		this.PassWord = PassWord;
		this.Gender = Gender;
		this.Car = Car;
		this.StuNum = StuNum;
		this.NationalNum = NationalNum;
		driver.EditInfo(FirstName, LastName, UserName, PassWord, StuNum, NationalNum, Gender, Car);
		testGetFirstName();
		testGetLastName();
		testGetUserName();
		testGetPassWord();
		testGetStuNUm();
		testGetNationalNum();
		testGetCar();
		testGetGender();
	}

}
