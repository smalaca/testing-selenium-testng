package com.smalaca.pageobject.google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

abstract class GooglePage {
    private final WebDriver webDriver;

    GooglePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    protected List<WebElement> findElements(By by) {
        return webDriver.findElements(by);
    }

    protected WebElement findElement(By by) {
        return webDriver.findElement(by);
    }

    protected WebElement waitForPresenceOf(By by) {
        return new WebDriverWait(webDriver, 1).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    protected void open(String url) {
        webDriver.get(url);
    }

    protected WebDriver aWebDriver() {
        return webDriver;
    }
}
