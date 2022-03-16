/**
 * @author Harjinder Singh Banga
 * @email hsbanga@yahoo.com
 * @create date 14/03/2022 
 * @modify By:  date:
 * 
 * @desc 
 */

package stepDefinations;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import configuration.TestData;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.LogBack;


public class Hooks extends TestData {

	public static HashMap<String, String> bodyParameters = new HashMap<String, String>();
	public static String name;
	public static String[] splName;

	@Before(order = 1)
	public void scnStartup(Scenario scenario) {

		LogBack.startScenario(scenario.getName());
	}

	@Before(order = 2)
	public void getScenarioID(Scenario scenario) {

		name = null;
		name = scenario.getName();

		int i;
		for (i = 0; i < name.length(); i++) {
			char c = name.charAt(i);

			if ('0' <= c && c <= '9')
				break;
		}
		String alphaPart = name.substring(0, i);

		String numberPart = name.substring(i);

		int j;
		for (j = 0; j < numberPart.length(); j++) {

			Boolean flag = Character.isDigit(numberPart.charAt(j));
			if (flag) {
				continue;
			} else {
				break;
			}
		}
		String alphaPart1 = numberPart.substring(j);
		name = alphaPart + numberPart.substring(0, j);
		splName = name.split("_");
		System.out.println("splName" + splName[0]);

	}

	@Before(order = 3)
	public static void writeDir() {
		try {
			// Create Directory
			String fileName = "./Logs";
			Path path = Paths.get(fileName);
			Files.createDirectories(path);
			// Create Sub Directory
			String subFile = "./Logs/" + splName[0] + "./";
			Path newpath = Paths.get(subFile);
			Files.createDirectories(newpath);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
