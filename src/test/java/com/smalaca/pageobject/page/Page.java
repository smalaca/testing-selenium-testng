package com.smalaca.pageobject.page;

import org.openqa.selenium.WebDriver;

public abstract class Page {
    private final WebDriver webDriver;

    protected Page(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    protected void open(String url) {
        webDriver.get(url);
    }

    protected WebDriver aWebDriver() {
        return webDriver;
    }
}
