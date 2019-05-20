package com.foguetinho.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = UploadConcurrentTest.SimpleConfiguration.class)
public class UploadConcurrentTest implements ApplicationContextAware, InitializingBean {
 
    @Configuration
    public static class SimpleConfiguration {}
 
    private ApplicationContext applicationContext; 
    private boolean beanInitialized = false;
    
    private static SeleniumUploadExample seleniumExample;
    private String expectedTitle = "Baeldung | Java, Spring and Web Development tutorials";    
    
    @BeforeClass
    public static void setUp() {
        seleniumExample = new SeleniumUploadExample();
    }
    
    @AfterClass
    public static void tearDown() {
        seleniumExample.closeWindow();
    }
 
    @Override
    public void afterPropertiesSet() throws Exception {
        this.beanInitialized = true;
    }
 
    @Override
    public void setApplicationContext(
      final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }   
    /*
    @Test
    public void whenAboutBaeldungIsLoaded_thenAboutEugenIsMentionedOnPage() {
        seleniumExample.getAboutBaeldungPage();
        String actualTitle = seleniumExample.getTitle();
      
        assertNotNull(actualTitle);
        assertEquals(expectedTitle, actualTitle);
        assertTrue(seleniumExample.isAuthorInformationAvailable());
    }
    */
    /* teste na pagina google
    @Test
    public void test() {    
    	SeleniumConfig sc = new SeleniumConfig();
    	driver = sc.getDriver();
    	
    	driver.get("https://google.com");
    	String titulo = driver.getTitle();
    	assertEquals(titulo, "Google");
    }
*/
    @Test(threadPoolSize = 1, invocationCount = 10, timeOut = 50000)
    public void givenMethod_whenRunInThreads_thenCorrect() {
        int count = Thread.activeCount();
        
        System.out.println(Thread.currentThread().getId());
        seleniumExample.enviandoFatura();
        
        assertTrue(count > 1);
    }
   
}