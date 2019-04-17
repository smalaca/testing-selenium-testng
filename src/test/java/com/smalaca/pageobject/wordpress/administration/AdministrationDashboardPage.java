package com.smalaca.pageobject.wordpress.administration;

import com.smalaca.pageobject.page.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdministrationDashboardPage extends Page {

    @FindBy(id = "wp-admin-bar-my-account")
    private WebElement accountBar;

    AdministrationDashboardPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public AdministrationWordPressPage logOut() {
        new Actions(aWebDriver()).moveToElement(accountBar).perform();

        WebElement logoutButton = aLogOutButton();
        logoutButton.click();

        AdministrationWordPressPage administrationWordPressPage = new AdministrationWordPressPage(aWebDriver());
        administrationWordPressPage.open();
        return administrationWordPressPage;
    }

    private WebElement aLogOutButton() {
        return new WebDriverWait(aWebDriver(), 1)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("wp-admin-bar-logout")));
    }
}
