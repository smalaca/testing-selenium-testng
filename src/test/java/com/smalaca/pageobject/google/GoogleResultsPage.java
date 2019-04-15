package com.smalaca.pageobject.google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GoogleResultsPage {
    private final WebDriver webDriver;

    GoogleResultsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public boolean hasResults() {
        return webDriver.findElements(By.className("g")).size() > 0;
    }

    public boolean hasResult(String urlAddress) {
        return webDriver.findElements(By.linkText(urlAddress)).size() == 0;
    }
}
