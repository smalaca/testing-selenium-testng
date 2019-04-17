package com.smalaca.pageobject.wordpress;

import com.smalaca.pageobject.wordpress.domain.NoteComment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class NotePage {
    private static final String AUTHOR_FIELD_NAME = "author";
    private static final String MAIL_ADDRESS_FIELD_NAME = "email";
    private static final String COMMENT_FIELD_NAME = "comment";
    private static final String SUBMIT_BUTTON_NAME = "submit";

    private final WebDriver webDriver;

    @FindBy(name = AUTHOR_FIELD_NAME)
    private WebElement authorField;

    @FindBy(name = MAIL_ADDRESS_FIELD_NAME)
    private WebElement mailAddressField;

    @FindBy(name = COMMENT_FIELD_NAME)
    private WebElement commentField;

    @FindBy(name = SUBMIT_BUTTON_NAME)
    private WebElement submitButton;

    NotePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public NotePage addComment(NoteComment comment) {
        fillWithValue(authorField, comment.getAuthor());
        fillWithValue(mailAddressField, comment.getMailAddress());
        fillWithValue(commentField, comment.getComment());

        submitButton.submit();

        return new NotePage(webDriver);
    }

    private void fillWithValue(WebElement element, String value) {
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

    public void addReplyToComment(NoteComment comment, NoteComment reply) {
        openReplyFormFor(comment);
        addComment(reply);
    }

    private void openReplyFormFor(NoteComment comment) {
        String xpath = "//div[@class='comment-content']/p[text()='" + comment.getComment() + "']/../../div[@class='reply']/a";
        webDriver.findElement(By.xpath(xpath)).click();

        new WebDriverWait(webDriver, 1).until(ExpectedConditions.presenceOfElementLocated(By.className("comment-respond")));
    }

    public boolean hasReply(NoteComment comment, NoteComment reply) {
        String xpath = "//div[@class='comment-content']/p[text()='" + comment.getComment() + "']" +
                "/../..//div[contains(@class, 'comment-author')]/b[text()='" + comment.getAuthor() + "']" +
                "/../../../../ul[@class='children']//li[contains(@class,'comment')]";

        List<WebElement> replys = webDriver.findElements(By.xpath(xpath));

        return replys
                .stream()
                .anyMatch(webElement -> containsComment(webElement, reply));
    }
}
