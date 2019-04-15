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
    private WebDriver driver;

    @BeforeMethod
    public void initDriver() {
        driver = WebDriverFactory.driver();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void shouldCheckIfCanOpenGoogle() {
        driver.get("http://www.google.com");

        List<WebElement> elements = driver.findElements(By.name("q"));

        Assert.assertEquals(1, elements.size());
    }

    @Test
    public void shouldCheckIfCanOpenCodeSprinters() {
        WebDriver driver = WebDriverFactory.driver();

        driver.get("http://agileszkolenia.pl");

        List<WebElement> elements = driver.findElements(By.id("mce-FNAME"));

        Assert.assertEquals(1, elements.size());
    }
}
