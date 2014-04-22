package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class TestAddPromotion extends SeleniumTestCase  {

	public void testAddPromotionValidInput() throws InterruptedException{
		login();
		driver.findElement(By.id("promotion")).click();
		driver.findElement(By.id("addpromotionform")).isDisplayed();
		fillTextElement("promoName", "Ike's Sandwich");
		fillTextElement("startDate", "05/01/2014");
		fillTextElement("endsDate", "05/31/2014");
		fillTextElement("desc", "Sandwich Deal");
		fillTextElement("address", "1 Washington Square");
		fillTextElement("city", "San Jose");
		fillTextElement("pincode", "95112");
		fillTextElement("dealName0", "Free Sandwich");
		fillTextElement("dealDescription0", "Buy 15 sandwich get 1 free");
		fillTextElement("noofDeals0", "100");
		Select select = new Select(driver.findElement(By.xpath("//*[@id='Tags0']")));
		select.deselectAll();
		select.selectByVisibleText("Shopping");
		driver.findElement(By.id("submit")).click();
		wait2Sec();
		assertTrue(driver.findElement(By.id("viewDealsTable")).isDisplayed());
		
	}

}
