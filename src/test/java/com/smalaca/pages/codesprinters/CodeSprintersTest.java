package com.smalaca.pages.codesprinters;

import com.smalaca.pages.BasePageTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class CodeSprintersTest extends BasePageTest {

    @Test
    public void canSwitchToWindow() {
        getWebDriver().get("http://agileszkolenia.pl");
        getWebDriver().findElement(By.cssSelector(".wp-image-1579")).click();

        getWebDriver().getWindowHandles().stream().anyMatch(this::windowFound);

        WebElement link = new WebDriverWait(getWebDriver(), 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".ProfileHeading-toggleItem")));

        link.click();
        getWebDriver().close();
    }

    private boolean windowFound(String windowId) {
        return getWebDriver().switchTo().window(windowId).getTitle().equals("Code Sprinters (@codesprinters) | Twitter");
    }
}
