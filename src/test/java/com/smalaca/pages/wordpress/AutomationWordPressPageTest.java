package com.smalaca.pages.wordpress;

import com.smalaca.pageobject.wordpress.AutomationWordPressPage;
import com.smalaca.pageobject.wordpress.NotePage;
import com.smalaca.pageobject.wordpress.domain.NoteComment;
import com.smalaca.pageobject.wordpress.domain.NoteCommentFactory;
import com.smalaca.pages.BasePageTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class AutomationWordPressPageTest extends BasePageTest {
    private final NoteCommentFactory noteCommentFactory = new NoteCommentFactory();

    @Test
    public void shouldAddNewComment() {
        NoteComment comment = noteCommentFactory.random();

        NotePage reloadedNotePage = openFirstNotePage().addComment(comment);

        assertTrue(reloadedNotePage.hasComment(comment));
    }

    @Test
    public void shouldAddReplyToComment() {
        NoteComment comment = noteCommentFactory.random();
        NoteComment reply = noteCommentFactory.random();
        NotePage reloadedNotePage = openFirstNotePage().addComment(comment);

        reloadedNotePage.addReplyToComment(comment, reply);

        assertTrue(reloadedNotePage.hasReply(comment, reply));
    }

    private NotePage openFirstNotePage() {
        AutomationWordPressPage page = new AutomationWordPressPage(getWebDriver());
        page.open();
        return page.openFirstNote();
    }
}
