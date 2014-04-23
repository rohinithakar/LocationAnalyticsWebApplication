package Selenium;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class TestAddEvent extends SeleniumTestCase {

	public void testAddEventValidInput() throws InterruptedException{
		login();
		driver.findElement(By.id("event")).click();
		driver.findElement(By.id("addpromotionform")).isDisplayed();
		fillTextElement("promoName", "Event1");
		fillTextElement("startDate", "05/01/2014");
		fillTextElement("endsDate", "05/01/2014");
		fillTextElement("desc", "Speaker keynote");
		fillTextElement("address", "1 Washington Square");
		fillTextElement("city", "San Jose");
		fillTextElement("pincode", "95112");
		fillTextElement("dealName0", "CEO speaks");
		fillTextElement("dealDescription0", "Speaker Keynote");
		fillTextElementByName("dealSchedule", "7:00pm at Engineering Building,SJSU");
		fillTextElement("noofDeals0", "100");
		Select select = new Select(driver.findElement(By.xpath("//*[@id='Tags0']")));
		select.deselectAll();
		select.selectByVisibleText("Event");
		driver.findElement(By.id("submitEvent")).click();
		wait2Sec();
		assertTrue(driver.findElement(By.id("viewDealsTable")).isDisplayed());
		
	}


}
