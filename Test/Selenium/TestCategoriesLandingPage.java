package Selenium;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;


public class TestCategoriesLandingPage extends SeleniumTestCase  {

	public void testAddEventTab() throws InterruptedException{
		login();
		driver.findElement(By.id("event")).click();
		assertTrue(driver.findElement(By.id("promotiondetails")).isDisplayed());
	}
	
	public void testAddPromotionTab() throws InterruptedException{
		login();
		driver.findElement(By.id("promotion")).click();
		assertTrue(driver.findElement(By.id("addpromotionform")).isDisplayed());
	}
	
	public void testViewMyEventsTab() throws InterruptedException{
		login();
		driver.findElement(By.id("viewDeals")).click();
		assertTrue(driver.findElement(By.id("viewDealsTable")).isDisplayed());
	}
	
	public void testViewReportsTab() throws InterruptedException{
		login();
		driver.findElement(By.id("report")).click();
		assertTrue(driver.getPageSource().contains("Generate Report"));
	}
}
