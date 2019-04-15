package com.smalaca.webdriver;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.util.function.Function;
import java.util.function.Supplier;

class Browser {
    private final Function<Capabilities, WebDriver> webDriver;
    private final Supplier<Capabilities> capabilities;

    Browser(Function<Capabilities, WebDriver> webDriver, Supplier<Capabilities> capabilities) {
        this.webDriver = webDriver;
        this.capabilities = capabilities;
    }

    WebDriver webDriver() {
        return webDriver.apply(capabilities());
    }

    Capabilities capabilities() {
        return capabilities.get();
    }
}
