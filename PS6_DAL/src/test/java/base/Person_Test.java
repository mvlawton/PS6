package base;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;

public class Person_Test {

	private static PersonDomainModel per1;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		Date dDate = null;
		try {
			dDate = new SimpleDateFormat("yyyy-MM-dd").parse("1996-06-11");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		per1 = new PersonDomainModel();
		per1.setFirstName("Matt");
		per1.setLastName("Lawton");
		//per1.setBirthday(dDate);
		per1.setCity("Wilmington");
		per1.setPostalCode(19809);
		per1.setStreet("10 Cragmere Rd");
		per1.setPersonID(UUID.randomUUID());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		PersonDomainModel per;	
		PersonDAL.deletePerson(per1.getPersonID());
		per = PersonDAL.getPerson(per1.getPersonID());
		assertNull("The Person shouldn't have been in the database",per);		
	}
	
	@Test
	public void AddPersonTest()
	{		
		PersonDomainModel per;		
		per = PersonDAL.getPerson(per1.getPersonID());		
		assertNull("The Person shouldn't have been in the database",per);		
		PersonDAL.addPerson(per1);	
		
		per = PersonDAL.getPerson(per1.getPersonID());
		System.out.println(per1.getPersonID() + " found");
		assertNotNull("The Person should have been added to the database",per);
	}
	
	@Test
	public void UpdatePersonTest()
	{		
		PersonDomainModel per;
		final String C_LASTNAME = "Smith";
		
		per = PersonDAL.getPerson(per1.getPersonID());		
		assertNull("The Person shouldn't have been in the database",per);		
		PersonDAL.addPerson(per1);	
		
		per1.setLastName(C_LASTNAME);
		PersonDAL.updatePerson(per1);
		
		per = PersonDAL.getPerson(per1.getPersonID());

		assertTrue("Name Didn't Change",per1.getLastName() == C_LASTNAME);
	}

	@Test
	public void DeletePersonTest()
	{		
		PersonDomainModel per;		
		per = PersonDAL.getPerson(per1.getPersonID());		
		assertNull("The Person shouldn't have been in the database",per);	
		
		PersonDAL.addPerson(per1);			
		per = PersonDAL.getPerson(per1.getPersonID());
		System.out.println(per1.getPersonID() + " found");
		assertNotNull("The Person should have been added to the database",per);
		
		PersonDAL.deletePerson(per1.getPersonID());
		per = PersonDAL.getPerson(per1.getPersonID());		
		assertNull("The Person shouldn't have been in the database",per);	
		
	}
	
}
