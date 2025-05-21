package com.cloudbees.selenium_java_allure_sample.pages;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cloudbees.selenium_java_allure_sample.utils.BasePage;

public class DocumentationPage extends BasePage {
/**
 * This page locates all the element in the home page after a user logs in	
 * @param basePage
 */
	
public DocumentationPage(BasePage basePage){
	inheritSession(basePage);
	PageFactory.initElements(driver, this);
	//basePage.validatePage("Discount Coupons & Extra Cashback on 500+ Sites -CashKaro");
	
}

@FindBy(xpath = "//input[@placeholder='Search all CloudBees Resources']")
private WebElement searchField;

@FindBy(xpath = "//div[@class='col position-static d-md-flex bg-white dark-mode-bg px-0']")
private WebElement searchAllTab;

@FindBy(xpath = "//input[@placeholder='Search']")
private WebElement searchInput;

@FindBy(xpath = "//ul[contains(@class,'pagination')]/li/span[@class='page-link' and contains(text(), '1')]")

private WebElement paginationArea;
public void clickSearchAll() {
	searchField.click();
	
}

public WebElement getSearchAllTab() {
	return searchAllTab;

}

public void search(String searchterm) {
    searchInput.sendKeys("Installation");
    searchInput.sendKeys(Keys.RETURN);
	
}

public WebElement getPagination() {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	return wait.until(ExpectedConditions.elementToBeClickable(paginationArea));
    
}

public void closeBrowser() {
	if (driver != null) {
		driver.quit();
	}
}
}
