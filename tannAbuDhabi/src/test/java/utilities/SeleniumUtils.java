/**
 * @author Harjinder Singh Banga
 * @email hsbanga@yahoo.com
 * @create date: 16/03/2022
 * @modify By:  date:
 *
 * @desc
 */

package utilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.core.exception.CucumberException;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumUtils {

	// Variables/Objects declaration
	private WebDriver driver;
	public static String browser = JavaUtils.javaUtilsSelfObj.getSystemProperty("BROWSER", "chrome").toLowerCase();
	public static int implicitWait = Integer
			.parseInt(JavaUtils.javaUtilsSelfObj.getSystemProperty("IMPLICIT_WAIT", "0"));
	public static boolean _doNotQuitDriver = false;
	public static boolean _skipScreenshotAfterStep = false;

	// Methods Declaration

	/**
	 * Create a browser driver instance based on the browserName parameter received.
	 * and initialize the selenium implicit wait.
	 *
	 * @param browserName : name of the browser to be used for test execution.
	 */
	public void createDriver(String browserName) {

		if (browserName.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--no-sandbox");
			options.addArguments("enable-automation");
			options.addArguments("--disable-extensions");
			options.addArguments("--dns-prefetch-disable");
			// options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--window-size=1920,1080");
			options.addArguments("--allow-insecure-localhost");
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();

		}

		else if (browserName.equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();

		}

		else if (browserName.equalsIgnoreCase("ie")) {

			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();

		}

		else if (browserName.equalsIgnoreCase("edge")) {

			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.manage().window().maximize();

		}

		else if (browserName.equalsIgnoreCase("opera")) {

			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();
			driver.manage().window().maximize();

		}

		else if (browserName.equalsIgnoreCase("chromeheadless")) {

			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-gpu");
			options.addArguments("enable-automation");
			options.addArguments("--disable-extensions");
			options.addArguments("--dns-prefetch-disable");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--window-size=1920,1080"); // this might also work
																// options.addArguments("--window-size=1325x744");
			options.addArguments("--allow-insecure-localhost");
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			driver = new ChromeDriver(options);

		}

		else {

			LogBack.log.error("Please provide appropriate browser name.");

		}

		driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
	}

	/**
	 * This method returns the instantiated WebDriver if not instantiated and found
	 * null. it will create the WebDriver instance as per the browser passed.
	 *
	 * @return The WebDriver object reference.
	 */
	public WebDriver getDriver() {

		if (driver == null) {

			createDriver(browser);
		}

		return driver;
	}

	/**
	 * This method returns the instantiated WebDriver if not instantiated and found
	 * null. it will create the WebDriver instance as per the browser passed.
	 *
	 * @return The WebDriver object reference.
	 */
	public Boolean pageLoaded() {

			JavascriptExecutor js = (JavascriptExecutor)driver;
			if(js.executeScript("return document.readyState").toString().equals("complete")){
				return true;
			}
		
		return false;
	}

	/**
	 * This Method just close the current window of browser instance but does not
	 * close the browser instance. Close the current window, quitting the browser if
	 * it's the last window currently open.
	 *
	 */
	public void closeCurrentWindow() {

		driver.close();
	}

	/**
	 * This Method just quite the browser instance and initialized it with null
	 * value. Quits this driver, closing every associated window.
	 *
	 */
	public void closeBrowser() {

		driver.quit();
		driver = null;
	}

	/**
	 * This method can be utilized to being not quit the browser at the end of the
	 * particular test scenarios if required.
	 *
	 */
	public void doNotCloseBrowser() {

		_doNotQuitDriver = true;
	}

	/**
	 * This method can be utilized to skip taking and attaching the screenshot in
	 * test report after each steps of scenarios if TAKE_ALL_SCREENSHOTS runtime
	 * environment property pass as true from command line.
	 *
	 */
	public void skipScrnShotAfterStep() {

		_skipScreenshotAfterStep = true;
	}

	/**
	 * This Method Utilize to load a new web page in the current browser window.
	 * 
	 * @param url : The URL to load. It is best to use a fully qualified URL
	 */
	public void getUrl(String url) {

		driver.get(url);
	}

	/**
	 * This Method Utilize to load a new web page in the current browser window with
	 * wait for the web page to load within the given time in second unit.
	 * 
	 * @param url  : The URL to load. It is best to use a fully qualified URL
	 * @param time : The number of seconds to wait
	 */
	public void getUrl(String url, int time) {

		driver.get(url);
		waitForPageLoaded(time);
	}

	/**
	 * This Method Utilize to load a new web page in the current browser window.
	 * 
	 * @param url : The URL to load. It is best to use a fully qualified URL
	 */
	public void navigateToUrl(String url) {

		driver.navigate().to(url);
	}

	/**
	 * This Method Utilize to load a new web page in the current browser window with
	 * wait for the web page to load within the given time in second unit.
	 * 
	 * @param url  : The URL to load. It is best to use a fully qualified URL
	 * @param time : The number of seconds to wait
	 */
	public void navigateToUrl(String url, int time) {

		driver.navigate().to(url);
		waitForPageLoaded(time);

	}

	/**
	 * This Method Utilize to navigate backward in the current browser window.
	 *
	 */
	public void navigateBack() {

		driver.navigate().back();
	}

	/**
	 * This Method Utilize to navigate backward in the current browser window with
	 * wait for the web page to load within the given time in second unit.
	 *
	 * @param time : The number of seconds to wait
	 */
	public void navigateBack(int time) {

		driver.navigate().back();
		waitForPageLoaded(time);

	}

	/**
	 * Get a string representing the current URL that the browser is looking at.
	 * 
	 * @return The URL of the page currently loaded in the browser
	 */
	public String getCurrentUrl() {

		return driver.getCurrentUrl();
	}

	/**
	 * Refresh the current page.
	 * 
	 */
	public void refreshPage() {

		driver.navigate().refresh();
		waitForPageLoaded(120);
	}

	/**
	 * Get a string representing the current URL that the browser is looking at with
	 * wait for the web page to load within the given time in second unit.
	 * 
	 * @param time : The number of seconds to wait
	 * @return The URL of the page currently loaded in the browser
	 */
	public String getCurrentUrl(int time) {

		waitForPageLoaded(time);
		return driver.getCurrentUrl();
	}

	/**
	 * This Method Utilize to implement the explicit wait on element.
	 * 
	 * @param time : The timeout in seconds when an expectation is called
	 * @return WebDriverWait new object
	 */
	public WebDriverWait webDriverWait(int time) {

		return new WebDriverWait(driver, time);
	}

	/**
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 *
	 * @param wE   : element the WebElement
	 * @param time : The timeout in seconds when an expectation is called
	 * @return the (same) WebElement once it is clickable (visible and enabled)
	 */
	public WebElement waitForElementClickable(WebElement wE, int time) {

		return webDriverWait(time).until(ExpectedConditions.elementToBeClickable(wE));

	}

	/**
	 * An expectation for checking an element is not clickable and disabled.
	 *
	 * @param wE   : element the WebElement
	 * @param time : The timeout in seconds when an expectation is called
	 * @return Boolean true when elements is not clickable(not visible and disabled)
	 */
	public boolean waitForElementNotClickable(WebElement wE, int time) {

		return webDriverWait(time).until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(wE)));

	}

	/**
	 * An expectation for checking that an element, known to be present on the DOM
	 * of a page, is visible. Visibility means that the element is not only
	 * displayed but also has a height and width that is greater than 0.
	 *
	 * @param wE   : element the WebElement
	 * @param time : The timeout in seconds when an expectation is called
	 * @return the (same) WebElement once it is visible
	 */
	public WebElement waitForElementVisible(WebElement wE, int time) {

		return webDriverWait(time).until(ExpectedConditions.visibilityOf(wE));

	}

	/**
	 * An expectation for checking the element to be invisible.
	 *
	 * @param wE   : used to check its invisibility
	 * @param time : The timeout in seconds when an expectation is called
	 * @return Boolean true when elements is not visible anymore
	 */
	public boolean waitForElementNotVisible(WebElement wE, int time) {

		return webDriverWait(time).until(ExpectedConditions.invisibilityOf(wE));

	}

	/**
	 * Wait for the web page to load within the given time in second unit.
	 * 
	 * @param time : The number of seconds to wait
	 */
	public void waitForPageLoaded(int time) {

		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		try {
			webDriverWait(time).until(expectation);
		} catch (Throwable e) {
			e.printStackTrace();
			LogBack.log.error("Timeout waiting for Page Load Request to complete.", e);
		}
	}

	/**
	 * This Method returns the new object of Selenium's Interactions' Actions class.
	 * 
	 */
	public Actions actionsClassObj() {

		return new Actions(driver);
	}

	/**
	 * Moves the mouse to the middle of the element. The element is scrolled into
	 * view and it's location is calculated using getBoundingClientRect.
	 * 
	 * @param wE : element to move to.
	 * @return A self reference.
	 */
	public Actions actionsMoveToElement(WebElement wE) {

		return actionsClassObj().moveToElement(wE);
	}

	/**
	 * Select a frame by its (zero-based) index with web driver wait. Once the frame
	 * has been selected, all subsequent calls on the WebDriver interface are made
	 * to that frame.
	 *
	 * @param index : (zero-based) index.
	 * @param time  : The timeout in seconds when an expectation is called.
	 * @return This driver focused on the given frame
	 */
	public WebDriver switchToFrame(int index, int time) {

		WebDriver focusedFrameDriver = webDriverWait(time)
				.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
		LogBack.log.info("Switched to the frame with the given index '" + index + "'");

		return focusedFrameDriver;
	}

	/**
	 * Select a frame by its name or ID with web driver wait. Frames located by
	 * matching name attributes are always given precedence over those matched by
	 * ID.
	 *
	 * @param nameOrId: The name of the frame window, the id of the &lt;frame&gt; or
	 *                  &lt;iframe&gt; element, or the (zero-based) index.
	 * @param time      : The timeout in seconds when an expectation is called.
	 * @return This driver focused on the given frame
	 */
	public WebDriver switchToFrame(String nameOrId, int time) {

		WebDriver focusedFrameDriver = webDriverWait(time)
				.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrId));
		LogBack.log.info("Switched to the frame with the given nameOrId '" + nameOrId + "'");

		return focusedFrameDriver;
	}

	/**
	 * Select a frame using its previously located {@link WebElement} with web
	 * driver wait.
	 *
	 * @param frameElement: The frame element to switch to.
	 * @param time          : The timeout in seconds when an expectation is called.
	 * @return This driver focused on the given frame
	 */
	public WebDriver switchToFrame(WebElement frameElement, int time) {

		WebDriver focusedFrameDriver = webDriverWait(time)
				.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
		LogBack.log.info("Switched to the frame with the given frameElement '" + frameElement + "'");

		return focusedFrameDriver;
	}

	/**
	 * This Method Utilize to switch to default content. Selects either the first
	 * frame on the page, or the main document when a page contains iframes.
	 *
	 * @return This driver focused on the top window/first frame.
	 */
	public WebDriver switchTodefaultContent() {

		return driver.switchTo().defaultContent();
	}

	/**
	 * Return an opaque handle to this window that uniquely identifies it within
	 * this driver instance. This can be used to switch to this window at a later
	 * date.
	 * 
	 * @return the current window handle
	 */
	public String getCurrentWindowId() {

		return driver.getWindowHandle();
	}

	/**
	 * Return a set of window handles which can be used to iterate over all open
	 * windows of this WebDriver instance by passing them to
	 * {@link #switchTo()}.{@link Options#window()}
	 *
	 * @return A set of window handles which can be used to iterate over all open
	 *         windows.
	 */
	public Set<String> getAllWindowIds() {

		return driver.getWindowHandles();
	}

	/**
	 * Switch to the recently opened window only if two window opened including
	 * current one.
	 * 
	 */
	public void switchFromCurrentToNewlyOpenedWindow() {

		String currentWindowId = getCurrentWindowId();
		Set<String> allWindows = getAllWindowIds();
		Iterator<String> iterator = allWindows.iterator();

		if (allWindows.size() == 2) {
			while (iterator.hasNext()) {
				String childWindow = iterator.next();
				if (!currentWindowId.equalsIgnoreCase(childWindow)) {
					driver.switchTo().window(childWindow);
					LogBack.log.info("Switched to newly opened second windowId '" + childWindow + "' of title '"
							+ driver.getTitle() + "'");
				}
			}
		} else {
			LogBack.log.error("No second window found opened.\n");
			throw new CucumberException("Cucumber Step Failed Exception.",
					new NotFoundException("No second window found opened."));
		}
	}

	/**
	 * Switch to the window with given window ID.
	 * 
	 * @param windowNameOrId : The name of the window or the Id as returned by
	 *                       {@link WebDriver#getWindowHandle()}
	 * @return This driver focused on the given window
	 */
	public void switchToGivenWindowId(String windowNameOrId) {

		try {
			driver.switchTo().window(windowNameOrId);
			LogBack.log.info("Switched to the window with the given windowId '" + windowNameOrId + "' of title '"
					+ driver.getTitle() + "'");
		} catch (NoSuchWindowException e) {
			LogBack.log.error("No such window found with the given windowId '" + windowNameOrId + "' to switch.\n");
			throw new CucumberException(
					"Cucumber Step Failed Exception.\nNo such window found with the given windowId '" + windowNameOrId
							+ "' to switch.",
					e);
		}
	}

	/**
	 * Switch to the window with given index.
	 * 
	 * @param index : The index of the window to switch to, index is 0-based number
	 */
	public void switchToWindowByIndex(int index) {

		Set<String> allWindows = getAllWindowIds();
		List<String> windowStrings = new ArrayList<>(allWindows);

		try {
			String reqWindow = windowStrings.get(index);
			driver.switchTo().window(reqWindow);
			LogBack.log.info(
					"Switched to the window with the given index '" + index + "' of title '" + driver.getTitle() + "'");
		} catch (Exception e) {
			LogBack.log.error("No such window found with given index i.e. '" + index + "' to switch.\n");
			throw new CucumberException("Cucumber Step Failed Exception.\nNo such window found with given index i.e. '"
					+ index + "' to switch.", e);
		}
	}

	/**
	 * Switch to the window with given title.
	 * 
	 * @param title : Title of the window to switch to
	 */
	public void switchToWindowByTitle(String title) {

		Set<String> allWindows = getAllWindowIds();
		boolean isSwithced = false;
		for (String childWindow : allWindows) {
			if (driver.switchTo().window(childWindow).getTitle().equals(title)) {
				driver.switchTo().window(childWindow);
				isSwithced = true;
				break;
			}
		}

		if (isSwithced) {
			LogBack.log.info("Switched to window with given title- " + driver.getTitle());
		} else {
			LogBack.log.error("No such window found with '" + title + "' title to switch.\n");
			throw new CucumberException("Cucumber Step Failed Exception.",
					new NoSuchWindowException("No such window found with '" + title + "' title to switch."));
		}
	}

	/**
	 * Switch to the window with given URL.
	 * 
	 * @param url : URL of the window to switch
	 */
	public void switchToWindowByUrl(String url) {

		Set<String> allWindows = getAllWindowIds();
		boolean isSwithced = false;
		for (String childWindow : allWindows) {
			if (driver.switchTo().window(childWindow).getCurrentUrl().equals(url)) {
				driver.switchTo().window(childWindow);
				isSwithced = true;
				break;
			}
		}

		if (isSwithced) {
			LogBack.log.info("Switched to window with given URL- " + driver.getCurrentUrl());
		} else {
			LogBack.log.error("No such window found with '" + url + "' URL to switch.\n");
			throw new CucumberException("Cucumber Step Failed Exception.",
					new NoSuchWindowException("No such window found with '" + url + "' URL to switch."));
		}
	}

	/**
	 * Close window with the given index.
	 * 
	 * @param index : The index of the window to close, index is 0-based number
	 */
	public void closeWindowByIndex(int index) {

		Set<String> allWindows = driver.getWindowHandles();
		List<String> windowStrings = new ArrayList<>(allWindows);

		try {
			String reqWindow = windowStrings.get(index);
			driver.switchTo().window(reqWindow);
			String title = driver.getTitle();
			driver.close();
			LogBack.log.info("Closed the window with the given index '" + index + "' of title '" + title + "'");
		} catch (Exception e) {
			LogBack.log.error("No such window found to close with given index i.e. '" + index + "'\n");
			throw new CucumberException(
					"Cucumber Step Failed Exception.\nNo such window found to close with given index i.e. '" + index
							+ "'",
					e);
		}
	}

	/**
	 * Close the window with given title.
	 * 
	 * @param title : Title of the window to close
	 */
	public void closeWindowByTitle(String title) {

		Set<String> allWindows = driver.getWindowHandles();
		String windowtoClosetitle = null;
		boolean isClosed = false;
		for (String childWindow : allWindows) {
			if (driver.switchTo().window(childWindow).getTitle().equals(title)) {
				driver.switchTo().window(childWindow);
				windowtoClosetitle = driver.getTitle();
				driver.close();
				isClosed = true;
				break;
			}
		}
		if (isClosed) {
			LogBack.log.info("Closed the window with given title- " + windowtoClosetitle);
		} else {
			LogBack.log.error("No such window found to close with '" + title + "' title.");
			throw new CucumberException("Cucumber Step Failed Exception.",
					new NoSuchWindowException("No such window found to close with '" + title + "' title."));
		}
	}

	/**
	 * Close the window with given URL.
	 * 
	 * @param url : URL of the window to close
	 */
	public void closeWindowByUrl(String url) {

		Set<String> allWindows = driver.getWindowHandles();
		String windowtoCloseUrl = null;
		boolean isClosed = false;
		for (String childWindow : allWindows) {
			if (driver.switchTo().window(childWindow).getCurrentUrl().equals(url)) {
				driver.switchTo().window(childWindow);
				windowtoCloseUrl = driver.getCurrentUrl();
				driver.close();
				isClosed = true;
				break;
			}
		}
		if (isClosed) {
			LogBack.log.info("Closed the window with given URL- " + windowtoCloseUrl);
		} else {
			LogBack.log.error("No such window found to close with '" + url + "' URL.");
			throw new CucumberException("Cucumber Step Failed Exception.",
					new NoSuchWindowException("No such window found to close with '" + url + "' URL."));
		}
	}

	/*
	 * ############################### JavaScript Executor Methods for Selenium's
	 * Actions/Operations ###############################
	 */

	/**
	 * Click on dedicated element using JavaScript.
	 *
	 * @param wE - Web Element of intended element to be clicked
	 */
	public void jsExecutorClick(WebElement wE) {

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click()", wE);
	}

	/**
	 * Click on dedicated element using JavaScript with web driver wait.
	 *
	 * @param wE   - Web Element of intended element to be clicked
	 * @param time - Integer wait in seconds
	 */
	public void jsExecutorClick(WebElement wE, int time) {

		waitForElementVisible(wE, time);
		waitForElementClickable(wE, time);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click()", wE);
	}

	/**
	 * Insert text on dedicated element using JavaScript.
	 *
	 * @param wE   - Web Element of intended element where text has to insert
	 * @param text - value of text to insert
	 */
	public void jsExecutorSetText(WebElement wE, String text) {

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].value='" + text + "';", wE);
	}

	/**
	 * Insert text on dedicated element using JavaScript with web driver wait.
	 *
	 * @param wE   - Web Element of intended element where text has to insert
	 * @param text - value of text to insert
	 * @param time - Integer wait in seconds
	 */
	public void jsExecutorSetText(WebElement wE, String text, int time) {

		waitForElementVisible(wE, time);
		waitForElementClickable(wE, time);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].value='" + text + "';", wE);
	}

	/**
	 * Returns text String from dedicated element using JavaScript.
	 *
	 * @param wE - Web Element of intended element where text has to get
	 * @return Available text in element
	 */
	public String jsExecutorGetText(WebElement wE) {

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		return (String) jsExecutor.executeScript("return arguments[0].text;", wE);
	}

	/**
	 * Returns text String from dedicated element using JavaScript with web driver
	 * wait.
	 *
	 * @param wE   - Web Element of intended element where text has to get
	 * @param time - Integer wait in seconds
	 * @return Available text in element
	 */
	public String jsExecutorGetText(WebElement wE, int time) {

		waitForElementVisible(wE, time);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		return (String) jsExecutor.executeScript("return arguments[0].text;", wE);
	}

	/**
	 * Returns innerText String from dedicated element using JavaScript.
	 *
	 * @param wE - Web Element of intended element where innerText has to get
	 * @return Available innerText in element
	 */
	public String jsExecutorGetInnerText(WebElement wE) {

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		return (String) jsExecutor.executeScript("return arguments[0].innerText;", wE);
	}

	/**
	 * Returns innerText String from dedicated element using JavaScript with web
	 * driver wait.
	 *
	 * @param wE   - Web Element of intended element where innerText has to get
	 * @param time - Integer wait in seconds
	 * @return Available innerText in element
	 */
	public String jsExecutorGetInnerText(WebElement wE, int time) {

		waitForElementVisible(wE, time);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		return (String) jsExecutor.executeScript("return arguments[0].innerText;", wE);
	}

	/**
	 * Opens new window in already opened browser session using JavaScript.
	 *
	 */
	public void jsExecutorOpenNewWindow() {

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.open();");
	}

	/**
	 * Set the attribute value against the given attribute name using JavaScript.
	 *
	 * @param wE       - Web Element of intended element where innerText has to get.
	 * @param attName  - Name of the Attribute to be set or change.
	 * @param attValue - Value, which need to set or change against the Attribute
	 *                 Name provided.
	 */
	public void jsExecutorSetAttribute(WebElement wE, String attName, String attValue) {

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", wE, attName, attValue);
	}

	/**
	 * Set the attribute value against the given attribute name using JavaScript
	 * with web driver wait.
	 *
	 * @param wE       - Web Element of intended element where innerText has to get.
	 * @param attName  - Name of the Attribute to be set or change.
	 * @param attValue - Value, which need to set or change against the Attribute
	 *                 Name provided.
	 * @param time     - Integer wait in seconds
	 */
	public void jsExecutorSetAttribute(WebElement wE, String attName, String attValue, int time) {

		waitForElementVisible(wE, time);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", wE, attName, attValue);
	}

	/**
	 * Scroll up/down to the page in order to reach that element.
	 *
	 * @param wE - Web Element of intended element where text has to get
	 */
	public void jsExecutorScrollIntoView(WebElement wE) {

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", wE);
	}

	/**
	 * Scroll up/down to the page in order to reach that element with web driver
	 * wait.
	 *
	 * @param wE   - Web Element of intended element where text has to get
	 * @param time - Integer wait in seconds
	 */
	public void jsExecutorScrollIntoView(WebElement wE, int time) {

		waitForElementVisible(wE, time);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", wE);
	}

	/**
	 * Refresh the current page using JavascriptExecutor.
	 * 
	 */
	public void jsExecutorRefreshPage() {

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("location.reload();");
		waitForPageLoaded(120);
	}

	/**
	 * Hard refresh the current page using JavascriptExecutor.
	 * 
	 */
	public void jsExecutorHardRefreshPage() {

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("location.reload(true);");
		waitForPageLoaded(120);
	}

	/**
	 * Clear text field value using JavaScript.
	 *
	 * @param wE - Web Element of the text field
	 * @return Available innerText in element
	 */
	public void jsExecutorClearValue(WebElement wE) {

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		jsExecutor.executeScript("arguments[0].value = '';", wE);
	}
}
