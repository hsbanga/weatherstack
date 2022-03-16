

/**
 * @author Harjinder Singh Banga
 * @email hsbanga@yahoo.com
 * @create date 14/03/2022 
 * @modify By:  date:
 * 
 * @desc 
 */
package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomFunctions {

	public static String email;
	public static String sanitizedEmail;
	public static String secondaryEmail;

	public RandomFunctions() {
		email = "test" + RandomStringUtils.randomAlphanumeric(5) + "@mail7.io";
		sanitizedEmail = RandomStringUtils.randomAlphanumeric(5) + "$&++?%22@mail7.io";
		secondaryEmail = ("secondary" + RandomStringUtils.randomAlphanumeric(5) + "@mail7.io").toLowerCase();
	}

	public static final String getEmail() {
		return (email);
	}

	public static String getFirstName() {
		String generatedstring = RandomStringUtils.randomAlphabetic(4);
		String FirstName = "F" + generatedstring;
		return (FirstName);

	}

	public static String getLastName() {
		String generatedstring = RandomStringUtils.randomAlphabetic(4);
		String LastName = "L" + generatedstring;
		return (LastName);

	}

	public static String getPassword() {
		String password = "p@$$word123";
		return password;

	}

	public String getCompanyName() {

		String generatedstring = RandomStringUtils.randomAlphabetic(2);
		String CompanyName = "CompanyName" + generatedstring;
		return (CompanyName);

	}

	public String getCompanyIndustry() {

		String generatedstring = RandomStringUtils.randomAlphabetic(2);
		String CompanyIndustry = "CompanyIndustry" + generatedstring;
		return (CompanyIndustry);

	}

	public String getAge() {

		String generatedstring = RandomStringUtils.randomNumeric(1);
		return (generatedstring);

	}

	public String getCustomObject() {

		String generatedstring = RandomStringUtils.randomNumeric(2);
		String CustomObjectdata = "test" + generatedstring;
		return (CustomObjectdata);

	}

	// Random address 1
	public String getAddress1() {
		String generatednumericstring = RandomStringUtils.randomNumeric(3);
		String Address1 = "add 1" + " " + generatednumericstring;
		return (Address1);
	}

	// Random address 2
	public String getAddress2() {
		String generatedstring = RandomStringUtils.randomAlphabetic(3);
		String Address1 = "add 2" + " " + generatedstring;
		return (Address1);
	}

	// Random Postal code
	public String getPostalCode() {
		String generatednumericstring = RandomStringUtils.randomNumeric(6);
		String PostalCode = generatednumericstring;
		return (PostalCode);
	}

	// Random Region
	public String getRegion() {
		String generatedstring = RandomStringUtils.randomAlphabetic(4);
		String Region = generatedstring;
		return (Region);
	}

	public long randomWait() {
		long sleepTime = 999 + (long) (Math.random() * 5000);
		// String sleepTime = RandomStringUtils.randomNumeric(1000, 5000);
		System.out.println(sleepTime);
		return sleepTime;

	}
	
	public static String getDate() {
		
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		String date = simpleDateFormat.format(new Date());
		//System.out.println(date);

//		Date date = new Date();
//		SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");
//		String strDate = formatter.format(date);
//		System.out.println(strDate);
		return date;
	}
	
	public static String getUserName() {
		String generatedstring = RandomStringUtils.randomAlphabetic(5);
		String username = "U" + generatedstring;
		return (username);

	}
	
	public static String getPhoneNumber() {
		String generatedstring = RandomStringUtils.randomNumeric(10);
		String phoneNumber = "+91" + generatedstring;
//		String phoneNumber = generatedstring;
		return (phoneNumber);

	}

}
