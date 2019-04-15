package com.smalaca.pages.google;

import com.smalaca.pageobject.google.GoogleMainPage;
import com.smalaca.pageobject.google.GoogleResultsPage;
import com.smalaca.webdriver.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GooglePageTest {
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
        GoogleMainPage mainPage = new GoogleMainPage(driver.get());
        mainPage.open();

        GoogleResultsPage resultsPage = mainPage.searchFor("codesprinters");

        Assert.assertTrue(resultsPage.hasResults());
    }
}
