package core;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestKayakFlights {

	// Create a new instance of the FireFox driver
	WebDriver driver = new FirefoxDriver();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void testSearch_Flight() throws FileNotFoundException, IOException {
		
		//Create new object of KayakFlights class
		KayakFlights kayak = new KayakFlights();
		
		//Extracting data from CSV file
		String csvFile = "./src/main/resources/Kayak_flights.csv";
		BufferedReader br = null;
		String line = null;
		String SplitBy = " , ";

		try {
			br = new BufferedReader(new FileReader(csvFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			//Create while loop for test cases iteration
			while ((line = br.readLine()) != null) {

				String[] csv = line.split(SplitBy);
				
				//Extracting data from CSV file
				String test_case_id = csv[0];
				String url = csv[1];
				String airport_from = csv[2];
				String airport_to = csv[3];
				String date_from = csv[4];
				String date_to = csv[5];
				
				//Invoking "search" method of the "KayakFlights" class
				kayak.search(driver, airport_from, airport_to, date_from,
						date_to, url, test_case_id);
				
				//Assert the Origin and Destination Details in the Results screen and Main screen
				assertEquals(airport_from, kayak.res_from);

				assertEquals(airport_to, kayak.res_to);

			}

			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
