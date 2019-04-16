package com.smalaca.pages;

import com.smalaca.webdriver.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BasePageTest {
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeMethod
    public void initDriver() {
        driver.set(WebDriverFactory.driver());
    }

    @AfterMethod
    public void tearDown() {
        driver.get().quit();
    }

    protected WebDriver getWebDriver() {
        return driver.get();
    }
}
