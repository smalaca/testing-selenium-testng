package com.smalaca.pageobject.wordpress;

import com.smalaca.pageobject.wordpress.domain.NoteComment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class NotePage {
    private static final String AUTHOR_FIELD_NAME = "author";
    private static final String MAIL_ADDRESS_FIELD_NAME = "email";
    private static final String COMMENT_FIELD_NAME = "comment";
    public static final String SUBMIT_BUTTON_NAME = "submit";

    private final WebDriver webDriver;

    NotePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public NotePage addComment(NoteComment comment) {
        fillWithValue(AUTHOR_FIELD_NAME, comment.getAuthor());
        fillWithValue(MAIL_ADDRESS_FIELD_NAME, comment.getMailAddress());
        fillWithValue(COMMENT_FIELD_NAME, comment.getComment());

        WebElement button = webDriver.findElement(By.name(SUBMIT_BUTTON_NAME));
        button.submit();

        return new NotePage(webDriver);
    }

    private void fillWithValue(String fieldName, String value) {
        WebElement element = webDriver.findElement(By.name(fieldName));
        element.click();
        element.sendKeys(value);
    }

    public boolean hasComment(NoteComment comment) {
        List<WebElement> comments = webDriver.findElements(By.cssSelector("li[class*=comment]"));

        return comments
                .stream()
                .anyMatch(webElement -> containsComment(webElement, comment));
    }

    private boolean containsComment(WebElement webElement, NoteComment comment) {
        return
                containsValueUnder(webElement, ".comment-content p", comment.getComment()) &&
                containsValueUnder(webElement, ".comment-author b", comment.getAuthor());
    }

    private boolean containsValueUnder(WebElement webElement, String cssSelector, String value) {
        return webElement.findElement(By.cssSelector(cssSelector)).getText().equals(value);
    }

    public void addReplayToComment(NoteComment comment, NoteComment replay) {
        List<WebElement> comments = webDriver.findElements(By.cssSelector("li[class*=comment]"));
        WebElement existingComment = comments.stream().filter(webElement -> containsComment(webElement, comment)).findFirst().get();

        String xpath = "//div[@class='comment-content']/p[text()='" + comment.getComment() + "']/../../div[@class='reply']/a";
        webDriver.findElement(By.xpath(xpath)).click();

        new WebDriverWait(webDriver, 1).until(ExpectedConditions.presenceOfElementLocated(By.className("comment-respond")));

        addComment(replay);
    }

    public boolean hasReplay(NoteComment comment, NoteComment replay) {
        String xpath = "//div[@class='comment-content']/p[text()='" + comment.getComment() + "']" +
                "/../..//div[contains(@class, 'comment-author')]/b[text()='" + comment.getAuthor() + "']" +
                "/../../../../ul[@class='children']//li[contains(@class,'comment')]";

        List<WebElement> replys = webDriver.findElements(By.xpath(xpath));

        return replys
                .stream()
                .anyMatch(webElement -> containsComment(webElement, replay));
    }
}
