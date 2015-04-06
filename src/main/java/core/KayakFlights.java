package core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Timestamp;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class KayakFlights {

	// Find the all WebElements
	@FindBy(id = "origin")
	@CacheLookup
	static WebElement airport_from_field;

	@FindBy(id = "destination")
	@CacheLookup
	static WebElement airport_to_field;

	@FindBy(id = "travel_dates-start-wrapper")
	@CacheLookup
	static WebElement date_from_field;

	@FindBy(id = "travel_dates-end-display")
	@CacheLookup
	static WebElement date_to_field;

	@FindBy(id = "nearbyO")
	@CacheLookup
	static WebElement from_nearby_button;

	@FindBy(id = "nearbyD")
	@CacheLookup
	static WebElement to_nearby_button;
	
	@FindBy(id = "travel_dates-start-wrapper")
	@CacheLookup
	static WebElement date_start1;

	@FindBy(id = "fdimgbutton")
	@CacheLookup
	static WebElement search_button;

	static String res_from = null;
	static String res_to = null;

	// Creating "search" method
	public static void search(WebDriver driver, String airport_from,
			String airport_to, String date_from, String date_to, String url,
			String test_case_id) {

		// Go to www.kayak.com
		driver.get(url);

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		// driver.manage().window().maximize();
		PageFactory.initElements(driver, KayakFlights.class);

		// Entering data
		
		airport_from_field.clear();
		airport_from_field.sendKeys(airport_from);		
		from_nearby_button.click();
		// System.out.println("Origin Airport selected");

		airport_to_field.clear();
		airport_to_field.sendKeys(airport_to);		
		to_nearby_button.click();
		// System.out.println("Destination Airport selected");
		
		date_from_field.click();
		driver.findElement(By.xpath(date_from)).click();
		//System.out.println("Origin date selected");

		date_to_field.clear();
		driver.findElement(By.id("travel_dates-end-wrapper")).click();
		driver.findElement(By.xpath(date_to)).click();		
		//System.out.println("End date selected");
		
		search_button.click();

		// ################### Results screen ####################

		String result_from = driver
				.findElement(
						By.xpath("//div[1]/div/div/div/div[2]/div[1]/div[1]/div[1]/span[1]"))
				.getText();
		String result_to = driver.findElement(
				By.xpath("//div[1]/div[1]/div[1]/span[3]")).getText();

		//System.out.println(result_from);
		//System.out.println(result_to);
		
		//Actual result
		res_from = result_from;
		//System.out.println(res_from);
		res_to = result_to;
		//System.out.println(res_to);

		// Write testing results in a report file
		String test_report = "# " + test_case_id
				+ " Airport Origin entered in the main screen: " + airport_from
				+ "\r\n" + "# " + test_case_id
				+ " Airport Origin on Results screen: " + res_from + "\r\n"
				+ "# " + test_case_id
				+ " Airport Destination entered in the main screen: "
				+ airport_to + "\r\n" + "# " + test_case_id
				+ " Airport Destination on Results screen: " + res_to + "\r\n";

		Calendar calendar = Calendar.getInstance();
		java.util.Date now = calendar.getTime();

		try {
			String header = "# ==================================================================="
					+ "\r\n"
					+ "# Username:      [Aleh Shybaila]"
					+ "\r\n"
					+ "# Email:         [ashybaila@gmail.com]"
					+ "\r\n"
					+ "# Date:          "
					+ "["
					+ now
					+ "]"
					+ "\r\n"
					+ "#"
					+ "\r\n"
					+ "# OS:            [Windows 7]"
					+ "\r\n"
					+ "# Java version:  [1.8.0_25]"
					+ "\r\n"
					+ "#"
					+ "\r\n"
					+ "# Script name:   [KayakFlights]"
					+ "\r\n"
					+ "# Description:   [Assert the Origin and Destination Details are the same as the one in the main screen]"
					+ "\r\n"
					+ "# Output file:   [report_01.txt]"
					+ "\r\n"
					+ "# ==================================================================="
					+ "\r\n";

			File file = new File("./src/test/reports/report_01.txt");

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			//append header to the report file
			bw.write(header); 
			
			// append body to the report file
			bw.append(test_report); 

			bw.append("# ===================================================================");
			bw.close();
		}

		catch (IOException e) {
			e.printStackTrace();
		}

	}

}
