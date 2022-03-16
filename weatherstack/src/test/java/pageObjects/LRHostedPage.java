/**
 * @author Akanksha Jain
 * @email akanksha.jain@loginradius.com
 * @create date 19-Nov-2020 
 * @modify By:  date:
 * 
 * @desc 
 */

package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utilities.Base;

public class LRHostedPage {

	// LR Login locators on Hosted page
	public static By emailIdHolderLogin = By.xpath("//*[@id='loginradius-login-emailid']");
	public static By passwordHolderLogin = By.xpath("//*[@id='loginradius-login-password']");
	public static By loginButton = By.xpath("//*[@id='loginradius-submit-login']");
	
//	public static By emailIdHolderLogin = By.xpath("//input[@id='loginradius-login-emailid']");
//	public static By passwordHolderLogin = By.xpath("//input[@id='loginradius-login-password']");
//	public static By loginButton = By.xpath("//input[@id='loginradius-submit-login']");

	// Link Account
	public static By facebookLinkAccountButton = By.xpath("//div[@id='lr-social-login']/div/span[contains(@class, 'lr-flat-facebook')]");

	// Login methods
	public static WebElement getEmailIdHolderLogin() {
		return Base.waitForElementVisibilityLocator(emailIdHolderLogin, 20);
	}

	public static WebElement getPasswordHolderLogin() {
		return Base.waitForElementVisibilityLocator(passwordHolderLogin, 20);
	}

	public static WebElement getloginButton() {
		return Base.waitForElementVisibilityLocator(loginButton, 50);
	}

	// Link Account Methods
	public static WebElement getFacebookLinkAccountButton() {
		return Base.waitForElementVisibilityLocator(facebookLinkAccountButton, 20);
	}
}
