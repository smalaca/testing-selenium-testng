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

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.smalaca.webdriver.BrowserType.*;

class BrowserFactory {
    private static final Map<BrowserType, Function<Capabilities, WebDriver>> webDrivers = ImmutableMap.of(
            CHROME, ChromeDriver::new,
            FIREFOX, FirefoxDriver::new,
            EDGE, EdgeDriver::new,
            MSIE, InternetExplorerDriver::new
    );

    private static final Map<BrowserType, Supplier<Capabilities>> capabilities = ImmutableMap.of(
            CHROME, () -> {
                ChromeOptions chromeOptions = new ChromeOptions();
//                chromeOptions.addArguments("--headless");
                return chromeOptions;
            },
            FIREFOX, () -> {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("-headless");
                return firefoxOptions;
            },
            EDGE, EdgeOptions::new,
            MSIE, InternetExplorerOptions::new
    );

    Browser create(BrowserType browserType) {
        return new Browser(webDrivers.get(browserType), capabilities.get(browserType));
    }
}
