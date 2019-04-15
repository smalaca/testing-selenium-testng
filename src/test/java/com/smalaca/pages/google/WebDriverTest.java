package com.smalaca.pages.google;

import com.smalaca.webdriver.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class WebDriverTest {
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeMethod
    public void initDriver() {
        driver.set(WebDriverFactory.driver());
    }

    @AfterMethod
    public void tearDown() {
        driver.get().quit();
    }

    @Test
    public void shouldCheckIfCanOpenGoogle() {
        WebDriver webDriver = driver.get();
        webDriver.get("http://www.google.com");

        List<WebElement> elements = webDriver.findElements(By.name("q"));

        Assert.assertEquals(1, elements.size());
    }

    @Test
    public void shouldCheckIfCanOpenCodeSprinters() {
        WebDriver webDriver = driver.get();
        webDriver.get("http://agileszkolenia.pl");

        List<WebElement> elements = webDriver.findElements(By.id("mce-FNAME"));

        Assert.assertEquals(1, elements.size());
    }
}
