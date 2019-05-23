package com.foguetinho.api;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
        for(int i=0; i<5000; i++) {
        	enviaArquivos();
        }
    }
    
    private void fazUpload(){    	
    	WebElement inputFilePath = this.config.getDriver().findElement(By.id("fileInput"));
    	
    	Random rand = new Random();
    	int n = rand.nextInt(4);
    	
    	String projectPath = System.getProperty("user.dir");
    	    
		inputFilePath.sendKeys(projectPath+"/src/main/resources/static/fatura"+n+".csv");   		
	
    	if(inputFilePath!= null) {
    		this.config.getDriver().findElement(By.id("prepara_arquivo")).click();
    	}
    	
    }
    
    private void enviaArquivos(){    	
    	this.config.getDriver().findElement(By.id("envia_arquivo")).click();
    }   
}
