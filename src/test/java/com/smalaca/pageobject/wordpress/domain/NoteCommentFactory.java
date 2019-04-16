package com.smalaca.pageobject.wordpress.domain;

import java.util.UUID;

public class NoteCommentFactory {
    private static int counter = 0;

    public NoteComment random() {
        String author = UUID.randomUUID().toString();
        String mailAddress = author + "@test.com";
        String comment = "test-" + counter + "-" + UUID.randomUUID().toString();
        counter++;

        return new NoteComment(author, mailAddress, comment);
    }
}
