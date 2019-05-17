package com.foguetinho.api;

import static org.junit.Assert.assertTrue;

import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.annotations.Test;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = UploadConcurrentTest.SimpleConfiguration.class)
public class UploadConcurrentTest implements ApplicationContextAware, InitializingBean {
 
    @Configuration
    public static class SimpleConfiguration {}
 
    private ApplicationContext applicationContext;
 
    private boolean beanInitialized = false;
 
    @Override
    public void afterPropertiesSet() throws Exception {
        this.beanInitialized = true;
    }
 
    @Override
    public void setApplicationContext(
      final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
 
    @Test(threadPoolSize = 5, invocationCount = 10, timeOut = 1000)
    public void givenMethod_whenRunInThreads_thenCorrect() {
        int count = Thread.activeCount();
        
        System.out.println(Thread.currentThread().getId());
        
        assertTrue(count > 1);
    }
}