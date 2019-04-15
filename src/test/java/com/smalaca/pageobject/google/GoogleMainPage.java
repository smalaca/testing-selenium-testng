package com.smalaca.pageobject.google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleMainPage {
    private static final String GOOGLE_URL = "http://www.google.com";

    private final WebDriver webDriver;

    public GoogleMainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void open() {
        webDriver.get(GOOGLE_URL);
    }

    public GoogleResultsPage searchFor(String query) {
        WebElement searchBox = webDriver.findElement(By.name("q"));
        searchBox.click();
        searchBox.sendKeys(query);
        searchBox.submit();

        return new GoogleResultsPage(webDriver);
    }
}
