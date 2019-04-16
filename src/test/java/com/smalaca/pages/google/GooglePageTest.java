package com.smalaca.pages.google;

import com.smalaca.pageobject.google.GoogleMainPage;
import com.smalaca.pageobject.google.GoogleResultsPage;
import com.smalaca.pages.BasePageTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GooglePageTest extends BasePageTest {
    @Test
    public void shouldCheckIfCanOpenGoogle() {
        GoogleMainPage mainPage = new GoogleMainPage(getWebDriver());
        mainPage.open();

        GoogleResultsPage resultsPage = mainPage.searchFor("codesprinters");

        Assert.assertTrue(resultsPage.hasResults());
        Assert.assertTrue(resultsPage.hasResult("agileszkolenia.pl/"));
    }
}
