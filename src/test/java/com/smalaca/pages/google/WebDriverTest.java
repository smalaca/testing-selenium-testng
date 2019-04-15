package com.smalaca.pages.google;

import com.smalaca.webdriver.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class WebDriverTest {

    @Test
    public void shouldCheckIfCanOpenGoogle() {
        WebDriver driver = WebDriverFactory.driver();

        driver.get("http://www.google.com");

        List<WebElement> elements = driver.findElements(By.name("q"));
        driver.findElement(By.name("q"));

        Assert.assertEquals(1, elements.size());

        driver.close();
    }
}
