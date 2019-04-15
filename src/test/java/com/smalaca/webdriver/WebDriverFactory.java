package com.smalaca.webdriver;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.Map;
import java.util.function.Supplier;

public class WebDriverFactory {
    private static final Map<Browser, Supplier<WebDriver>> webDrivers = ImmutableMap.of(
            Browser.CHROME, ChromeDriver::new,
            Browser.FIREFOX, FirefoxDriver::new,
            Browser.EDGE, EdgeDriver::new,
            Browser.MSIE, InternetExplorerDriver::new
    );

    public static WebDriver driver() {
        String browserProperty = System.getProperty("browser", System.getProperty("default.browser")).toUpperCase();
        Browser browser = Browser.valueOf(browserProperty);

        return webDrivers.get(browser).get();
    }
}
