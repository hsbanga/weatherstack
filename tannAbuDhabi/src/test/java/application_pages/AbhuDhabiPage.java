/**
 * @author Harjinder Singh Banga
 * @email hsbanga@yahoo.com
 * @create date: 16/03/2022
 * @modify By:  date:
 *
 * @desc
 */

package application_pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.UtilsManager;

public class AbhuDhabiPage {

	private WebDriver driver;
	private UtilsManager utilsManager;

	public AbhuDhabiPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Locators Segment
	@FindBy(how = How.XPATH, using = "//h3[@class='ui-lib-questionnaire-question__title' ]/text()")
	WebElement question;

	// Action Segment

	public void clickCategory(String categoryName) {
		String category = "//div[@class='component FeaturedContentList']/descendant::div[@class='row']/descendant::a[@title='"
				+ categoryName + "']";
		System.out.print("cat" + category);
		driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
		WebElement element = driver.findElement(By.xpath(category));

		utilsManager.seleniumUtils.waitForElementVisible(element, 20);
		element.click();

	}

	public boolean pageLoaded(String pageName) {
		driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
		if (utilsManager.seleniumUtils.pageLoaded()) {
			if (driver.getTitle().equals(pageName)) {
				return true;
			}
		}
		return false;
	}

	public void clickCategoryMenu(String categoryName, String menuName) {
		String category = "//div[@class='ui-lib-highlight-card-body__title' and text()='" + categoryName + "']";

		driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
		WebElement catelement = driver.findElement(By.xpath(category));

		utilsManager.seleniumUtils.waitForElementVisible(catelement, 20);
		catelement.click();

	}

	public void checkQuestion() {
		
	}

	
	
	
	
	
	
}