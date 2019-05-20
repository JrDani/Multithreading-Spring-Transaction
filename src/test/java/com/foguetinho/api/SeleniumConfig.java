package com.foguetinho.api;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SeleniumConfig {
	
	private WebDriver driver;
		
	public SeleniumConfig() {
	
		//String projectPath = System.getProperty("user.dir");
						
		System.setProperty("webdriver.chrome.driver", "C:/Program Files (x86)/Google/Chrome/Application/chromedriver.exe");

		ChromeOptions options = new ChromeOptions();

		options.setBinary("C:/Program Files (x86)/Google/Chrome/Application/chrome.exe");

		DesiredCapabilities capabilities = DesiredCapabilities.chrome();

		capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		driver = new ChromeDriver(capabilities);
		
	}
	
	public WebDriver getDriver() {
		return driver;
	}

}
