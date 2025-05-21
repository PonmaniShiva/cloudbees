package com.cloudbees.selenium_java_allure_sample.pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cloudbees.selenium_java_allure_sample.utils.BasePage;

import io.qameta.allure.Step;
/**
 * This page locates all element in home page
 * @author ponmanip
 *
 */
public class HomePage extends BasePage {
	public HomePage() throws Exception {
		try { 
			super.init();
			PageFactory.initElements(driver, this);
			}
		catch(Exception e) { throw e; }

	}
	
	@FindBy(id = "onetrust-reject-all-handler")
	private WebElement rejectButton;
	
	@FindBy(xpath = "//button[text() = 'Products']")
	private WebElement productsLink;
	
	@FindBy(xpath = "//button[text()='Resources']")
	private WebElement resourcesLink;
	
	@FindBy(xpath = "//a[text()='Documentation']")
	private WebElement documentationLink;
	
	@FindBy(linkText = "CloudBees CD/RO")
	private WebElement cdroLink;
	
	@FindBy (xpath = "//p[text() = 'Cost Savings']/following-sibling::div/span")
	private WebElement costSavingsText;
	
	@FindBy (xpath = "//span[text()='Auditors / Security']")
	private WebElement auditorsLink;
	
	@FindBy (xpath = "//p[text()='Release Governance']/following-sibling::h4[text()='Generate single-click audit reports']")
	private WebElement releaseGovernanceText;
	
	@Step("Wait for reject cookies button to load and click on it")
	public void clickRejectCookies(){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(rejectButton));
        rejectButton.click();      
	}
	
	@Step("Click products link")
	public void clickProductsLink(){

		productsLink.click();
	}
	
	@Step("Click CDRO link")
	public void clickCDROLink(){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(cdroLink));
        cdroLink.click();
	}
	
	@Step("Get cost savings value")
	public String getCostSavings(){
		return costSavingsText.getText();
	}
	
	@Step("Scroll down and click on Auditors / Security")
	public void scrollAndClickAuditors() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(auditorsLink));
        Actions actions = new Actions(driver);
        actions.moveToElement(auditorsLink).perform();
        auditorsLink.click();
		
	}
	
	@Step("Click on resources button")
	public void clickResourcesLink() {
		resourcesLink.click();
		
	}
	
	@Step("Click on documentation link")
	public void clickDocumentationLink() {
        documentationLink.click();
	}
	
	@Step("Switch to $index window")
	public void SwitchToWindow(int index) {
        Object[] windowHandles=driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[index]);
	}

	@Step("Find if Generate single-click audit reports) is under release governance ")
	public WebElement getReleaseGovernanceElement() {
        return releaseGovernanceText;
	}

	@Step("Get number of open browser windows")
	public int getNumberOfOpenWindows() {
		return driver.getWindowHandles().size();
	}
	
	
	
}
