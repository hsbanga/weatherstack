/**
 * @author Harjinder Singh Banga
 * @email hsbanga@yahoo.com
 * @create date 14/03/2022 
 * @modify By:  date:
 * 
 * @desc 
 */

package configuration;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import stepDefinations.StepDefination;
import utilities.LogBack;
import utilities.RandomFunctions;

public class TestData {
	public HashMap<String, String> weatherstackProd = new HashMap<String, String>();

	public HashMap<String, String> parameterValue = new HashMap<String, String>();
	public static HashMap<String, String> attrValueMap = new HashMap<String, String>();

	String plugin = getSystemProperty("plugin", "");
	public static String env = getSystemProperty("env", "");
	public static String attrvalue;

	public HashMap<String, String> parameterData() {

		if (env.equalsIgnoreCase("prod")) {
			if (plugin.equalsIgnoreCase("weatherstack")) {
				weatherstackProd.put("access_key", "cab194398c666477bb7f434e08f71cf3");
				weatherstackProd.put("BaseURI", "http://api.weatherstack.com");
				
				parameterValue.putAll(weatherstackProd);
			}
		}
		return parameterValue;

	}

	public String getStringBetweenTwoChars(String input) {
		String startChar = "(";
		String endChar = ")";

		try {
			int start = input.indexOf(startChar);
			if (start != -1) {
				int end = input.indexOf(endChar, start + startChar.length());
				if (end != -1) {
					return input.substring(start + startChar.length(), end);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return input;
	}

	public void getValuefromresponse(Response response, String key) {

		JsonPath js = new JsonPath(getStringBetweenTwoChars(response.asString()));
		attrvalue = js.get(key).toString();
		System.out.println("attrvalue =" + attrvalue);

		attrValueMap.put(key, attrvalue);

		for (String keys : attrValueMap.keySet()) {

			System.out.println(keys + " " + attrValueMap.get(keys));
		}
	}
	
	public void getValuefromresponse(Response response, String key, String searchTerm) {
		 String complete = response.asString();
		 
		 System.out.println("complete =" + complete);
		JsonPath js = new JsonPath(getStringBetweenTwoChars(response.asString()));
		attrvalue = js.get(searchTerm).toString();
		System.out.println("attrvalue =" + attrvalue);

		attrValueMap.put(key, attrvalue);

		for (String keys : attrValueMap.keySet()) {

			System.out.println(keys + " " + attrValueMap.get(keys));
		}
	}

	public void getIntegerfromresponse(Response response, String key) {

		JsonPath js = new JsonPath(response.asString());
		attrvalue = js.get(key).toString();
		System.out.println("attrvalue" + attrvalue);
		String numOnly = attrvalue.replaceAll("[^0-9]", "");

		System.out.println(numOnly);

		attrValueMap.put("OTP", numOnly);

	}

	public static String getSystemProperty(String propertyName, String defaultValue) {

		String propertyValue = null;

		if (defaultValue.isEmpty() || defaultValue == "") {
			if (System.getProperty(propertyName) == null) {
				LogBack.log.error("Please define the " + propertyName + " Property");
				System.exit(1);
			} else {
				propertyValue = System.getProperty(propertyName);
				if (propertyValue.isEmpty()) {
					LogBack.log.error("Please define the " + propertyName + " Value");
					System.exit(1);
				}
			}
		} else {

			propertyValue = (System.getProperty(propertyName) != null) ? System.getProperty(propertyName)
					: defaultValue;

		}

		return propertyValue;
	}

}
