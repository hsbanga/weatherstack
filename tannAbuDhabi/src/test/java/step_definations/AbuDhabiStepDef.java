/**
 * @author Harjinder Singh Banga
 * @email hsbanga@yahoo.com
 * @create date: 16/03/2022
 * @modify By:  date:
 *
 * @desc
 */
package step_definations;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.Assert;

import application_pages.AbhuDhabiPage;
import io.cucumber.java.en.*;
import utilities.LogBack;
import utilities.UtilsManager;

public class AbuDhabiStepDef {
	
	private UtilsManager utilsManager = null;
	AbhuDhabiPage abuPage;
	
	public AbuDhabiStepDef(UtilsManager utilsManager) {
		this.utilsManager = utilsManager;
	}
	
	
	@Given("launch the url")
	public void launch_the_url() {
		utilsManager.seleniumUtils.getDriver().get(utilsManager.javaUtils.get_Property("baseURL"));
		LogBack.log.info("Lanching the URL" + utilsManager.javaUtils.get_Property("baseURL"));
	}

	@When("click on the category {string}")
	public void click_on_the_category(String categoryName) throws InterruptedException {
		abuPage.clickCategory(categoryName);
	}

	@Then("verify {string} page is loaded")
	public void verify_page_is_loaded(String pageName) {
	    Assert.assertEquals(abuPage.pageLoaded(pageName), true);
	}

	@When("click on the {string} from {string} Category")
	public void click_on_the_from_category(String menu, String category) {
	   abuPage.clickCategoryMenu(category, menu);
	}

	@Then("verify {string} with all options and its content")
	public void verify_with_all_options_and_its_content(String question) {
		
	}

	@When("Select {string}")
	public void select(String optionName) {
	    
	}

	@When("click on {string} button")
	public void click_on_button(String buttonName) {
	    
	}

	@Then("verify {string} answers")
	public void verify_answers(String answers) {
	    
	}

	@Then("click on {string} option")
	public void click_on_option(String option) {
	    
	}

}
