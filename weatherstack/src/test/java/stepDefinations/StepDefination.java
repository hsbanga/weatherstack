/**
 * @author Harjinder Singh Banga
 * @email hsbanga@yahoo.com
 * @create date 14/03/2022 
 * @modify By:  date:
 * 
 * @desc 
 */

package stepDefinations;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.awaitility.Awaitility;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import configuration.TestData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LRHostedPage;
import pageObjects.LRSocialLogin;
import pojo.pojoweatherstack;
import resources.APIResources;
import utilities.Base;
import utilities.LogBack;
import utilities.Mail7Api;
import utilities.RandomFunctions;
import utilities.Utils;

public class StepDefination {

	Utils util = new Utils();
	Base base = new Base();
	String randomString = null;
	public WebDriver driver;
	ObjectMapper objectMapper;
	TestData data = new TestData();
	Mail7Api mail7 = new Mail7Api();
	HashMap<String, String> apiResponse = util.getapiResponse();
	RandomFunctions random = new RandomFunctions();

	@Given("add {string} {string} in {string}")
	public void add_in(String condition, String parameterName, String place) {

		util.setParameters(condition, parameterName, place);

	}

	@Given("add {string} as {string} in {string}")
	public void add_as_in(String parameterValue, String parameterName, String place) {
		util.setParametersWithSelfValue(parameterValue, parameterName, place);
	}

	@When("user calls {string} {string} with {string} http request")
	public void user_calls_with_http_request(String apiName, String apiType, String methodType) {

		try {
			Awaitility.await().atMost(500, TimeUnit.SECONDS).until(() -> util.isStatus(apiName, apiType, methodType));
			System.out.print("responsse" + util.response.asString());
			apiResponse.put(apiName, util.response.asString());

		} catch (Exception e) {
			e.printStackTrace();
			LogBack.log.error(e);
		} finally {
			util.queryParameters.clear();
			util.headerParameters.clear();
			util.pathParameters.clear();
			Utils.bodyParameters.clear();
		}

	}

	@When("user calls {string} {string} with {string} http request in browser")
	public void user_calls_with_http_request_in_browser(String apiName, String apiType, String methodType) {
		try {
			util.resource = APIResources.valueOf(apiName);
			driver = Base.getDriver(util.callApiFromBrowser(util.resource.getResource(), apiType));
			util.queryParameters.clear();
			util.headerParameters.clear();
			util.pathParameters.clear();
			Utils.bodyParameters.clear();
		} catch (Exception e) {
			LogBack.log.error(e);
			e.printStackTrace();
		}
	}

	@Then("verify if {string} doesn't contain {string}")
	public void verify_if_doesn_t_contain(String apiName, String attribute) {
		try {
			Assert.assertEquals(util.getattributevalue(apiName, attribute), null);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(int StatusCode) {
		Assert.assertEquals(util.response.getStatusCode(), StatusCode);
	}

	@Then("get {string} from the API response")
	public void get_from_the_api_response(String attribute) {
		System.out.println(attribute);
		data.getIntegerfromresponse(util.response, attribute);
	}

	@Then("verify if {string} {string} is {string}")
	public void verify_if_is(String apiName, String attribute, String value) {

		try {
			Assert.assertEquals(util.getattributevalue(apiName, attribute), null);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Then("{string} is {string} in response")
	public void is_in_response(String key, String value) {

		Assert.assertEquals(util.getJsonPath(util.response, key), value);
	}

	@Then("verify if {string} {string} is equal to {string} {string}")
	public void verify_if_is_equal_to(String apiName1, String attribute1, String apiName2, String attribute2) {

		Assert.assertEquals(util.getattributevalue(apiName1, attribute1), util.getattributevalue(apiName2, attribute2));

	}

	@Then("verify {string} response key {string} and value {string}")
	public void verify_response_key_and_value(String apiName, String key, String value) {
		Assert.assertEquals(util.getattributevalue(apiName, key), value );
	}

	@Then("verify {string} response")
	public void verify_response(String apiName) throws JsonProcessingException {

		pojoweatherstack pws = new pojoweatherstack();
		ObjectMapper mapper = new ObjectMapper();
		String str = mapper.writeValueAsString(pws);
		JSONObject jsonObject = new JSONObject(str);

	}

	@Then("get {string} of the user")
	public void get_of_the_user(String key) {
		data.getValuefromresponse(util.response, key);

	}

	@Then("get {string} with search term {string} of the user")
	public void get_with_search_term_of_the_user(String key, String searchTerm) {

		data.getValuefromresponse(util.response, key, searchTerm);

	}

	@Given("get the {string} from mail7")
	public void get_the_from_mail7(String attribute) {
		TestData.attrValueMap.put("VerificationToken", mail7.getQueryParameterValue(attribute));

	}

	@Then("close the browser")
	public void close_the_browser() {
		driver.close();
		driver.quit();
	}

	@Given("user is on hosted page")
	public void user_is_on_hosted_page() {
		driver = Base.getDriver(data.parameterData().get("HOSTED_PAGE_URL"));
	}

	@Given("user login with valid credentials")
	public void user_login_with_valid_credentials() {

		LRHostedPage.getEmailIdHolderLogin().sendKeys(RandomFunctions.email);
		LRHostedPage.getPasswordHolderLogin().sendKeys(RandomFunctions.getPassword());
		LRHostedPage.getloginButton().submit();
	}

	@When("user clicks on Link account")
	public void user_clicks_on_link_account() {
		WebElement element = LRHostedPage.getFacebookLinkAccountButton();
		Assert.assertEquals(element.isDisplayed(), true);
		element.click();

	}

	@Then("account must be linked successfully")
	public void account_must_be_linked_successfully() {
		String parent = driver.getWindowHandle();
		System.out.println(driver.getTitle());
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
			System.out.println(driver.getTitle());
		}

		LRSocialLogin.getIdHolder().sendKeys(data.parameterData().get("SOCIAL_EMAILID"));
		LRSocialLogin.getPasswordHolder().sendKeys(data.parameterData().get("SOCIAL_PASSWORD"));
		LRSocialLogin.getLoginButton().click();

		driver.switchTo().window(parent);
		System.out.println(driver.getTitle());

		WebElement element = LRSocialLogin.getUnlinkProfileButton();
		Assert.assertEquals(element.isDisplayed(), true);

	}

	@Then("wait for sometime in miliseconds {int}")
	public void wait_for_sometime_in_miliseconds(Integer timeInMiliSec) {
		try {
			Thread.sleep(timeInMiliSec);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
