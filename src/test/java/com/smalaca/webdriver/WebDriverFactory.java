package com.smalaca.webdriver;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.function.Supplier;

import static com.smalaca.webdriver.BrowserType.*;

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
