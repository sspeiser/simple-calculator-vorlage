package de.hftstuttgart.wip2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageCalculator {

	@FindBy(name = "firstNumber")
	WebElement firstNumber;
	
	@FindBy(name = "secondNumber")
	WebElement secondNumber;
	
	@FindBy(name = "result")
	WebElement result;
	
	
	@FindBy(css = "button[text='Add']")
	WebElement addButton;
	
	@SuppressWarnings("unused")
	private WebDriver driver;
	
	public PageCalculator(WebDriver driver) {
		this.driver = driver ;
		driver.switchTo().window("Simple Calculator");
		PageFactory.initElements(driver, this);
	}
	
	public void setFirstNumber(String number) {
		firstNumber.clear();
		firstNumber.sendKeys(number);
	}
	
	public void setSecondNumber(String number) {
		secondNumber.clear();
		secondNumber.sendKeys(number);
	}
	
	public String getResult() {
		return result.getText();
	}
	
	public void add() {
		addButton.click();
	}
}
