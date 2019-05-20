package com.foguetinho.api;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SeleniumUploadExample {
			 
    private SeleniumConfig config;
    private String url = "http://localhost:8090/";
 
    public SeleniumUploadExample() {
        config = new SeleniumConfig();
        config.getDriver().get(url);
    }
    
    public void closeWindow() {
        this.config.getDriver().close();
    }
     
    public String getTitle() {
        return this.config.getDriver().getTitle();
    }
 
    public void enviandoFatura(){
        fazUpload();
        enviaArquivos();
    }
    
    private void fazUpload(){    	
    	//https://stackoverflow.com/questions/9665021/selenium-upload-file-in-google-chrome
    	WebElement inputFilePath = this.config.getDriver().findElement(By.id("fileInput"));
    	inputFilePath.sendKeys("C:/Users/1690289/Desktop/telefonia_faturas/fatura.csv");
    	if(inputFilePath!= null) {
    		this.config.getDriver().findElement(By.id("prepara_arquivo")).click();
    	}
    }
    
    private void enviaArquivos(){    	
    	this.config.getDriver().findElement(By.id("envia_arquivo")).click();
    }
    /* 
    private void closeOverlay() {
        List<WebElement> webElementList = this.config.getDriver()
          .findElements(By.tagName("a"));
        if (webElementList != null) {
           webElementList.stream()
             .filter(webElement -> "Close".equalsIgnoreCase(webElement.getAttribute("title")))
             .filter(WebElement::isDisplayed)
             .findAny()
             .ifPresent(WebElement::click);
        }
    }
     
    private void clickAboutLink() {
        this.config.getDriver().findElement(By.partialLinkText("About")).click();
    }
     
    private void clickAboutUsLink() {
        Actions builder = new Actions(config.getDriver());
        WebElement element = this.config.getDriver()
          .findElement(By.partialLinkText("About Baeldung."));
        builder.moveToElement(element)
          .build()
          .perform();
    }
    
    public boolean isAuthorInformationAvailable() {
        return this.config.getDriver()
          .findElement(By.cssSelector("article > .row > div"))
          .isDisplayed();
    }
     */
}
