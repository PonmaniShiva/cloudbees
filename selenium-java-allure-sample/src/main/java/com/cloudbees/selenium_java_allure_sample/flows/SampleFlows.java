package com.cloudbees.selenium_java_allure_sample.flows;

import com.cloudbees.selenium_java_allure_sample.pages.HomePage;

import com.cloudbees.selenium_java_allure_sample.utils.BasePage;
/**
 * Flows with respect to login 
 * @author ponmanip
 *
 */
public class SampleFlows {


	public HomePage gotoCrDoLink() throws Exception {
		HomePage homePage = new HomePage();
		homePage.clickRejectCookies();

        // Click the 'Products' link and then 'CloudBees CD/RO'
        homePage.clickProductsLink();
        homePage.clickCDROLink();
		return homePage;
	}
}
