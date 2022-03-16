/**
 * @author Harjinder Singh Banga
 * @email hsbanga@yahoo.com
 * @create date 14/03/2022 
 * @modify By:  date:
 * 
 * @desc 
 */

package cucumber.Options;

import configuration.TestData;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
//import utilities.LogBack;



@CucumberOptions
	(
	features="src/test/java/features",
	glue="stepDefinations",
	dryRun=false,
	monochrome=true,
	plugin= {"pretty", "html:target/cucumber-reports", "json:target/cucumber/cucumberReport.json", "testng:target/testng-cucumber-reports/testngReport.xml"},
	tags = "@WeatherStack"

	)
public class TestRunner extends AbstractTestNGCucumberTests {
	// mvn clean test verify -Denv=stag -Dplugin=sendinblue -Dcucumber.filter.tags="@Sendinblue"
	// mvn verify -Denv=stag -Dplugin=sendinblue -Dcucumber.filter.tags="@Sendinblue"	
	
	private static boolean doUploadQmetryReport = Boolean.parseBoolean(TestData.getSystemProperty("UPLOAD_QMETRY_REPORT", "false").toLowerCase());

	/**
	   * This method calls necessary function to upload the test execution report into the Qmetry's respective project folder if 'doUploadQmetryReport'
	   * flag is pass true using 'UPLOAD_QMETRY_REPORT' java system property from CLA else it will be set to false by default.
	 * @throws Exception 
	   *
	   */

		
	
}
 