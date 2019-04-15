package com.smalaca.webdriver;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
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
        String gridAddress = System.getProperty("grid.address");
        String gridPort = System.getProperty("grid.port");

        if (gridAddress != null && gridPort != null) {
            URL gridUrl = anUrl(gridAddress, gridPort);
            Capabilities capabalities = new ChromeOptions();
            return new RemoteWebDriver(gridUrl, capabalities);
        }

        String browserProperty = System.getProperty("browser", System.getProperty("default.browser")).toUpperCase();
        Browser browser = Browser.valueOf(browserProperty);

        return webDrivers.get(browser).get();
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
