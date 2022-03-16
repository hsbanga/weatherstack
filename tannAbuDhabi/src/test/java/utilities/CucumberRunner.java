/**
 * @author Harjinder Singh Banga
 * @email hsbanga@yahoo.com
 * @create date: 16/03/2022
 * @modify By:  date:
 *
 * @desc
 */

package utilities;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features= {"src/test/resources/feature_files"},
        glue= {"step_definations"},
        tags= "",
        monochrome=true, dryRun=false,
        plugin= {"pretty", "timeline:target/cucumber-parallel-report", "testng:target/testng-cucumber-reports/testngReport.xml", "json:target/cucumberReport.json"}
		)
public class CucumberRunner extends AbstractTestNGCucumberTests {
	
	// Variables/Objects declaration
	private UtilsManager utilsManager = new UtilsManager(new SeleniumUtils(), new JavaUtils());
	
  /**
   * This method, overriding the scenarios method to execute all the scenarios in parallel
   * with by default 10 threads count by setting the dataprovider parallel option to true.
   * or keep it false to execute in sequence.
   *
   */
	@Override
	@DataProvider(parallel = false)
	public Object[][] scenarios() {
		
		return super.scenarios();
	}

  /**
   * This Method is created to do the initial one time setup for entire test execution.
   * Example: Loading the Properties file or connecting to database etc.
   *
   */
	@BeforeClass
	private void initSetup() throws IOException {
		
		utilsManager.javaUtils.load_Properties();
		
	}
	
  /**
   * This Method is created to take the necessary action after completing the entire test execution.
   * Example: Closing the database connection etc.
   *
   */
	@AfterClass
	private void finalTearDown() {
		
	}
	
}
