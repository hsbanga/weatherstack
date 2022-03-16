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

public class LRSocialLogin {

	// LR Create Account locators on Hosted page
	public static By loginWithFaceBooklink = By.xpath("//*[@id=\"interfacecontainerdiv\"]/span[1]");
	public static By idHolder = By.xpath("//*[@id='email']");
	public static By passwordHolder = By.xpath("//*[@id='pass']");
	public static By loginButton = By.xpath("//*[@id='loginbutton']");
	
//	public static By loginWithFaceBooklink = By.xpath("//div[@id='lr-social-login']/div/span[contains(@class, 'lr-flat-facebook')]");
//
//	public static By idHolder = By.xpath("//div[@id='email']");
//
//	public static By passwordHolder = By.xpath("//div[@id='pass']");
//	
//	
//	public static By loginButton = By.xpath("//div[@id='loginbutton']");
	
	
	public static By continueButton = By.xpath("//*[@id=\"u_0_12_JQ\"]/div[2]/div[1]/div[2]/div[1]/button");


	public static By unlinkButton = By.xpath("//*[@id='lr-linked-social']/div/span[3]/a");
	
	public static WebElement getLoginWithFaceBooklink() {
		return Base.waitForElementVisibilityLocator(loginWithFaceBooklink, 20);
	}

	public static WebElement getIdHolder() {
		
		return Base.waitForElementVisibilityLocator(idHolder, 20);
	}

	public static WebElement getPasswordHolder() {
		
		return Base.waitForElementVisibilityLocator(passwordHolder, 20);
	}

	public static WebElement getLoginButton() {
		
		return Base.waitForElementVisibilityLocator(loginButton, 20);
	}

	public static WebElement getContinueButton() {
		
		return Base.waitForElementVisibilityLocator(continueButton, 40);
	}
	
	public static WebElement getUnlinkProfileButton() {
		return Base.waitForElementVisibilityLocator(unlinkButton, 50);
		
	}
}
