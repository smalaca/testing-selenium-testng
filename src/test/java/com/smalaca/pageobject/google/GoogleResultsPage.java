package com.smalaca.pageobject.google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GoogleResultsPage extends GooglePage {
    GoogleResultsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean hasResults() {
        return findElements(By.className("g")).size() > 0;
    }

    public boolean hasResult(String urlAddress) {
        return hasResult(By.linkText(urlAddress));
    }

    private boolean hasResult(By by) {
        return findElements(by).size() == 0;
    }
}
