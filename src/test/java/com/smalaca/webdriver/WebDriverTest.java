package com.smalaca.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class WebDriverTest {

    @Test
    public void shouldCheckIfCanOpenGoogle() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\sebas_000\\Dropbox\\Training\\Automatyzacja Testów\\webdriver\\chrome\\73\\chromedriver.exe");

        WebDriver driver=new ChromeDriver();

        driver.get("http://www.google.com");

        List<WebElement> elements = driver.findElements(By.name("q"));
        driver.findElement(By.name("q"));

        Assert.assertEquals(1, elements.size());

        driver.close();
    }
}
