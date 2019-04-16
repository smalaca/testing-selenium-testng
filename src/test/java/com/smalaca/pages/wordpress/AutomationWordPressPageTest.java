package com.smalaca.pages.wordpress;

import com.smalaca.pageobject.wordpress.AutomationWordPressPage;
import com.smalaca.pageobject.wordpress.NotePage;
import com.smalaca.pageobject.wordpress.domain.NoteComment;
import com.smalaca.pageobject.wordpress.domain.NoteCommentFactory;
import com.smalaca.pages.BasePageTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class AutomationWordPressPageTest extends BasePageTest {

    @Test
    public void shouldAddNewComment() {
        NoteCommentFactory noteCommentFactory = new NoteCommentFactory();
        NoteComment comment = noteCommentFactory.random();
        AutomationWordPressPage page = new AutomationWordPressPage(getWebDriver());
        page.open();

        NotePage notePage = page.openFirstNote();
        NotePage reloadedNotePage = notePage.addComment(comment);

        assertTrue(reloadedNotePage.hasComment(comment));
    }
}
