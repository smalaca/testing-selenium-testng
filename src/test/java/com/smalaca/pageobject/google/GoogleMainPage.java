package com.smalaca.pageobject.google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleMainPage extends GooglePage {
    private static final String GOOGLE_URL = "http://www.google.com";

    public GoogleMainPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void open() {
        open(GOOGLE_URL);
    }

    public GoogleResultsPage searchFor(String query) {
        WebElement searchBox = findElement(By.name("q"));
        searchBox.click();
        searchBox.sendKeys(query);
        searchBox.submit();

        waitForPresenceOf(By.id("search"));

        return new GoogleResultsPage(aWebDriver());
    }
}
