/**
 * @author Harjinder Singh Banga
 * @email hsbanga@yahoo.com
 * @create date 14/03/2022 
 * @modify By:  date:
 * 
 * @desc 
 */

package utilities;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	public static WebDriver driver;

	public static WebDriver getDriver(String content) {

		ChromeOptions options = new ChromeOptions();

		options.addArguments("--window-size=1920,1080");
		options.addArguments("--allow-insecure-localhost");
		options.addArguments("--disable-gpu");
		options.addArguments("--no-sandbox");
//		options.addArguments("--headless");
		options.addArguments("--disable-notifications");

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();

		driver.get(content);

		return driver;
	}

	public static WebElement waitForElementVisibilityLocator(By locator, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, timeout);

		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	public static List<WebElement> getDriverfind(By locator) {
		return driver.findElements(locator);
	}

}