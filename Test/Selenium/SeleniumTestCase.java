package Selenium;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.http.entity.ByteArrayEntity;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

import junit.framework.TestCase;

public class SeleniumTestCase extends TestCase {

	protected WebDriver driver;
	protected String baseUrl;
	protected final static String hostUrl = "http://localhost:8080/LocationAnalyticsWebApplication/";
	private StringBuffer verificationErrors = new StringBuffer();

	public SeleniumTestCase(){
		baseUrl = getFinalURL("index.jsp");
	}

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	protected void waitForElementById(String id) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
	}
	
	protected void waitForElementByClassName(String className) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("content-for-list")));
	}

	protected void click(String id) {
		driver.findElement(By.id(id)).click();
	}
	
	protected void clickText(String text) {
		driver.findElement(By.linkText("Login")).click();
	}



	protected void waitForPageLoaded() {
		ExpectedCondition<Boolean> expectation = new
				ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
			}
		};

		Wait<WebDriver> wait = new WebDriverWait(driver,10);
		try {
			wait.until(expectation);
		} catch(Throwable error) {
			assertFalse("Timeout waiting for Page Load Request to complete.",true);
		}
	}

	protected String getFinalURL(String path) {
		return hostUrl+path;
	}

	protected void wait2Sec() throws InterruptedException {
		Thread.sleep(2000);
	}
	
	protected void fillTextElement(String id, String value) {
		WebElement webElement = driver.findElement(By.id(id));
		webElement.clear();
		webElement.sendKeys(value);
	}

	protected void fillTextElementByName(String name, String value) {
		WebElement webElement = driver.findElement(By.name(name));
		webElement.clear();
		webElement.sendKeys(value);
	}
	
	protected void login() throws InterruptedException{
		driver.get(baseUrl);
		waitForElementById("login-user");
		click("login-user");
		fillTextElement("email_login", "jane@yahoo.com");
		fillTextElement("password_login", "123456");
		driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/button[1]/span")).click();
		wait2Sec();
		driver.findElement(By.id("category"));
	}

	public String generateString(){
		int length = 5;
		Random r = new Random(); // perhaps make it a class variable so you don't make a new one every time
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < length; i++) {
			char c = (char)(r.nextInt((int)(Character.MAX_VALUE)));
			sb.append(c);
		}
		return sb.toString();
	}
	
	public static void setClipboardData(String string) {
		   StringSelection stringSelection = new StringSelection(string);
		   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		}
	
}

