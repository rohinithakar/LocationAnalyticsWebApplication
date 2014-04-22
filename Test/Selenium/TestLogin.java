package Selenium;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class TestLogin extends SeleniumTestCase {

	private StringBuffer verificationErrors = new StringBuffer();



	public void testLoginValidInput()  throws Exception{
		driver.get(baseUrl);
		waitForElementById("login-user");
		click("login-user");
		fillTextElement("email_login", "jane@yahoo.com");
		fillTextElement("password_login", "123456");
		driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/button[1]/span")).click();
		wait2Sec();
		driver.getPageSource().contains("Add Event");
	}
	
	public void testLoginEmptyInput() throws InterruptedException{
		driver.get(baseUrl);
		waitForElementById("login-user");
		click("login-user");
		fillTextElement("email_login", "");
		fillTextElement("password_login", "");
		driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/button[1]/span")).click();
		wait2Sec();
		driver.getPageSource().contains("Invalid Input");
		
	}
	
	public void testLoginNullInput() throws InterruptedException{
		driver.get(baseUrl);
		waitForElementById("login-user");
		click("login-user");
		fillTextElement("email_login", null);
		fillTextElement("password_login", null);
		driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/button[1]/span")).click();
		wait2Sec();
		driver.getPageSource().contains("Invalid Input");	
	}
	
	public void testLoginInvalidUserName() throws InterruptedException{
		driver.get(baseUrl);
		waitForElementById("login-user");
		click("login-user");
		fillTextElement("email_login", "dsfdsjfsf");
		fillTextElement("password_login", "123456");
		driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/button[1]/span")).click();
		wait2Sec();
		driver.getPageSource().contains("Invalid Input");
		
	}
	
	public void testLoginInvalidPassword() throws InterruptedException{
		driver.get(baseUrl);
		waitForElementById("login-user");
		click("login-user");
		fillTextElement("email_login", "jane@yahoo.com");
		fillTextElement("password_login", "fjgfdajgdnn");
		driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/button[1]/span")).click();
		wait2Sec();
		driver.getPageSource().contains("Invalid Input");
	}

}