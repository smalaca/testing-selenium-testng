package com.smalaca.pageobject.wordpress.administration;

import com.smalaca.pageobject.page.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdministrationWordPressPage extends Page {
    private static final String ADMINISTRATION_URL = "http://www.automation.markowicz.pro/wp-admin";

    @FindBy(id = "user_login")
    private WebElement loginField;

    @FindBy(id = "user_pass")
    private WebElement passwordField;

    @FindBy(id = "wp-submit")
    private WebElement submitButton;

    public AdministrationWordPressPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public void open() {
        open(ADMINISTRATION_URL);
    }

    public AdministrationDashboardPage authenticate(String login, String password) {
        loginField.sendKeys(login);
        passwordField.sendKeys(password);
        submitButton.click();

        return new AdministrationDashboardPage(aWebDriver());
    }

    public boolean isLogged() {
        return !loginField.isDisplayed();
    }
}
