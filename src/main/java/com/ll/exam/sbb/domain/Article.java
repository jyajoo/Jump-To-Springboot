package com.ll.exam.sbb.domain;

import lombok.Getter;

@Getter
public class Article {
    int id;
    String title;
    String body;

    public Article(int id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }
}
