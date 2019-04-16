package com.smalaca.pageobject.wordpress.domain;

import java.util.UUID;

public class NoteCommentFactory {
    public NoteComment random() {
        String author = UUID.randomUUID().toString();
        String mailAddress = author + "@test.com";
        String comment = UUID.randomUUID().toString();

        return new NoteComment(author, mailAddress, comment);
    }
}
