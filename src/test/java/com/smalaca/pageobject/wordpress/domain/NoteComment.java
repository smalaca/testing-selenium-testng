package com.smalaca.pageobject.wordpress.domain;

public class NoteComment {
    private final String author;
    private final String mailAddress;
    private final String comment;

    NoteComment(String author, String mailAddress, String comment) {
        this.author = author;
        this.mailAddress = mailAddress;
        this.comment = comment;
    }

    public String getAuthor() {
        return author;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public String getComment() {
        return comment;
    }
}
