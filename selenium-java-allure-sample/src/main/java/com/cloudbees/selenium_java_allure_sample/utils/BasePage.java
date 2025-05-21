package com.cloudbees.selenium_java_allure_sample.utils;

import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	String HOMEURL = "https://www.cloudbees.com/";
	public static WebDriver driver = null;
	
	@Step("Open browser and load cloudbees url")
	protected void init() throws Exception {
		
		WebDriverManager.chromedriver().setup();
  		driver = new ChromeDriver();
		driver.manage().window().maximize();
		System.out.println("opening " + HOMEURL + " in chrome browser");
		driver.get(HOMEURL);
	
}
	public void inheritSession(BasePage basePage){
		driver = basePage.getSelenium();
	}

	protected WebDriver getSelenium() { return driver; }
	

	@Attachment(value = "Page Screenshot", type = "image/png")
    public byte[] takeScreenshot() {
		System.out.println("screenshot taken");
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
	
}
