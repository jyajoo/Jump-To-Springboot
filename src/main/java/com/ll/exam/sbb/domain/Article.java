package com.ll.exam.sbb.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Article {
    private static int articleId = 0;
    private int id;
    private String title;
    private String body;

    public Article(String title, String body) {
        this.id = ++articleId;
        this.title = title;
        this.body = body;
    }

    public void modifyArticle(String title, String body) {
        this.title = title;
        this.body = body;
    }
}
