package com.smalaca.webdriver;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Map;
import java.util.function.Supplier;

public class WebDriverFactory {
    private static final Map<String, Supplier<WebDriver>> webDrivers = ImmutableMap.of(
            "CHROME", ChromeDriver::new
    );

    public static WebDriver driver() {
        String browser = System.getProperty("browser", System.getProperty("default.browser"));

        return webDrivers.get(browser).get();
    }
}
