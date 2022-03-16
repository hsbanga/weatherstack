/**
 * @author Harjinder Singh Banga
 * @email harjinder.banga@loginradius.com
 * @create date 19-Nov-2020 
 * @modify By:  date:
 * 
 * @desc This Class contains the log4j methods and data members.
 */

package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogBack {

	// Log4j2 automatically looks for file with name Log4j2.xml or Log4j2.json
	// configuration files in the classpath.

	public static Logger log = LogManager.getLogger(LogBack.class.getName());

	/**
	 * This is to print log for the beginning of the test scenarios
	 * 
	 * @param testScenarioName : Scenarios Name.
	 */
	public static void startScenario(String testScenarioName) {

		log.info("******************************************************************************************************************************************");

		log.info("$$$$$$$$$$$$$$$$$$$$$        -START-" + testScenarioName + "        $$$$$$$$$$$$$$$$$$$$$$$$$");

		log.info("******************************************************************************************************************************************");

	}

	/**
	 * This is to print log for the ending of the test scenarios
	 * 
	 * @param testScenarioName : Scenarios Name.
	 */
	public static void endScenario(String testScenarioName) {
		
		log.info("******************************************************************************************************************************************");

		log.info("XXXXXXXXXXXXXXXXXXXXXXX        -END-" + testScenarioName + "        XXXXXXXXXXXXXXXXXXXXXX");

		log.info("******************************************************************************************************************************************");


	}

}
