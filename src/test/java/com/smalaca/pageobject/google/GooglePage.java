package com.smalaca.pageobject.google;

import com.smalaca.pageobject.page.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

abstract class GooglePage extends Page {
    GooglePage(WebDriver webDriver) {
        super(webDriver);
    }

    protected List<WebElement> findElements(By by) {
        return aWebDriver().findElements(by);
    }

    protected WebElement findElement(By by) {
        return aWebDriver().findElement(by);
    }

    protected WebElement waitForPresenceOf(By by) {
        return new WebDriverWait(aWebDriver(), 1).until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
