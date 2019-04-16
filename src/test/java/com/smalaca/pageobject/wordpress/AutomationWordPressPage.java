package com.smalaca.pageobject.wordpress;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AutomationWordPressPage {
    private static final String AUTOMATION_WORDPRESS_URL = "http://www.automation.markowicz.pro/";
    private final WebDriver webDriver;

    public AutomationWordPressPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void open() {
        webDriver.get(AUTOMATION_WORDPRESS_URL);
    }

    public NotePage openFirstNote() {
        webDriver.findElement(By.cssSelector("a.comments-link")).click();
        return new NotePage(webDriver);
    }
}
