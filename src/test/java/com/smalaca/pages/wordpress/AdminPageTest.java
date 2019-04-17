package com.smalaca.pages.wordpress;

import com.smalaca.pageobject.wordpress.administration.AdministrationDashboardPage;
import com.smalaca.pageobject.wordpress.administration.AdministrationWordPressPage;
import com.smalaca.pages.BasePageTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminPageTest extends BasePageTest {
    private static final String LOGIN = "jan-automatyczny";
    private static final String PASSWORD = "Cod@Sprint3rs2019";

    @Test
    public void shouldLogInAndLogOut() {
        AdministrationWordPressPage adminPage = new AdministrationWordPressPage(getWebDriver());
        adminPage.open();

        AdministrationDashboardPage dashboardPage = adminPage.authenticate(LOGIN, PASSWORD);

        AdministrationWordPressPage administrationWordPressPage = dashboardPage.logOut();

        Assert.assertFalse(administrationWordPressPage.isLogged());
    }
}
