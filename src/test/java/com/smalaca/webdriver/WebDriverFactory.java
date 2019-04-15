package com.smalaca.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverFactory {
    public static WebDriver driver() {
        BrowserType browserType = aBrowserType();
        Browser browser = new BrowserFactory().create(browserType);
        String gridAddress = System.getProperty("grid.address");
        String gridPort = System.getProperty("grid.port");

        if (gridAddress != null && gridPort != null) {
            URL gridUrl = anUrl(gridAddress, gridPort);
            return new RemoteWebDriver(gridUrl, browser.capabilities());
        }

        return browser.webDriver();
    }

    private static BrowserType aBrowserType() {
        String browserProperty = System.getProperty("browser", System.getProperty("default.browser")).toUpperCase();
        return BrowserType.valueOf(browserProperty);
    }

    private static URL anUrl(String gridAddress, String gridPort) {
        String url = "http://" + gridAddress + ":" + gridPort + "/wd/hub";

        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Given url is invalid: " + url, e);
        }
    }
}
