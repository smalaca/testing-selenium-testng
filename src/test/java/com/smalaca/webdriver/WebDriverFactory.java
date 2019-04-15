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
    private static final Map<BrowserType, Supplier<WebDriver>> webDrivers = ImmutableMap.of(
            CHROME, ChromeDriver::new,
            FIREFOX, FirefoxDriver::new,
            EDGE, EdgeDriver::new,
            MSIE, InternetExplorerDriver::new
    );

    public static WebDriver driver() {
        BrowserType browserType = aBrowserType();
        String gridAddress = System.getProperty("grid.address");
        String gridPort = System.getProperty("grid.port");

        if (gridAddress != null && gridPort != null) {
            URL gridUrl = anUrl(gridAddress, gridPort);
            Capabilities capabalities = aCapabilities(browserType);
            return new RemoteWebDriver(gridUrl, capabalities);
        }

        return webDrivers.get(browserType).get();
    }

    private static BrowserType aBrowserType() {
        String browserProperty = System.getProperty("browser", System.getProperty("default.browser")).toUpperCase();
        return BrowserType.valueOf(browserProperty);
    }

    private static Capabilities aCapabilities(BrowserType browserType) {
        switch (browserType) {
            case EDGE:
                return new EdgeOptions();
            case MSIE:
                return new InternetExplorerOptions();
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                return chromeOptions;
            case FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("-headless");
                return firefoxOptions;
        }

        return null;
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
