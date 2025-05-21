package com.cloudbees.selenium_java_allure_sample;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.cloudbees.selenium_java_allure_sample.flows.SampleFlows;
import com.cloudbees.selenium_java_allure_sample.pages.DocumentationPage;
import com.cloudbees.selenium_java_allure_sample.pages.HomePage;

import io.qameta.allure.Description;

public class SampleTest {
	SampleFlows sampleFlows = null;
	HomePage homePage = null;
	DocumentationPage documentationPage = null;
	
	
	@BeforeSuite
	public void beforeSuite() {
		//Open browser and application in class constructor
		sampleFlows = new SampleFlows();
	}
	
	@Test(description ="Verify Cost saving")

    @Description("* Click the link Products on top > Click CloudBees CD/RO under Other Products\n"
    		+ "     * Verify that Cost Savings has a value of $2m\n"
    		+ "     * Scroll down, click Auditors / Security\n"
    		+ "     * Verify the text under Release Governance (Generate single-click audit reports)")
	public void testVerifyCostSaving() throws Exception{
		//Click the link Products on top > Click CloudBees CD/RO under Other Products
	    homePage = sampleFlows.gotoCrDoLink();
		
	    //Verify that Cost Savings has a value of $2m
		String actualCostSavings =  homePage.getCostSavings()  ;    
        Assert.assertTrue(actualCostSavings.contains("$2m"),  "Expected $2m, but found " + actualCostSavings); 
        
        //Verify the text under Release Governance (Generate single-click audit reports)
        homePage.scrollAndClickAuditors();
        Assert.assertTrue(homePage.getReleaseGovernanceElement().isDisplayed(), "Release Governance is not visible");
        
        homePage.takeScreenshot();
    }
	

	@Test(description ="Verify Documentation opens as a new tab")

    @Description("Click the link Resources on top > Click Documentation\n"
    		+ "     * Verify that it opens a new tab\n"
    		+ "	 * Click in the text field Search all CloudBees Resources\n"
    		+ "     * Verify that a new page is opened in this tab\n"
    		+ "     * Search for the word \"Installation\"\n"
    		+ "	 * Verify that we have pagination options at bottom")
	public void testVerifyDocumentationTab() throws Exception{
		//Click the link Resources on top 
        homePage.clickResourcesLink();
        int initialWindowCount = homePage.getNumberOfOpenWindows();
        
        //Click Documentation
        homePage.clickDocumentationLink();
        int currentWindowCount = homePage.getNumberOfOpenWindows();
        
        //Verify that it opens a new tab
        Assert.assertEquals(initialWindowCount+1, currentWindowCount);
        
        //Switch to new tab and Click in the text field Search all CloudBees Resources"
        homePage.SwitchToWindow(currentWindowCount-1);	
        documentationPage = new DocumentationPage(homePage);
        documentationPage.clickSearchAll();
        
        //Verify that a new page is opened in this tab
        Assert.assertTrue(documentationPage.getSearchAllTab().isDisplayed(), "Search all did not open as a new page");
        
        //Search for the word "Installation"
        documentationPage.search("Installation");
        
        //Verify that we have pagination options at bottom
        Assert.assertTrue(documentationPage.getPagination().isDisplayed(), "Pagination not displayed");
	}
	
	@AfterSuite
	public void afterSuite() {
	      documentationPage.closeBrowser();
    }
	
}
