package com.ll.exam.sbb.controller;

import com.ll.exam.sbb.domain.Article;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ArticleController {
    int articleId = 0;
    List<Article> articleList = new ArrayList<>();

    @GetMapping("/addArticle")
    @ResponseBody
    public String addArticle(String title, String body) {
        Article article = new Article(++articleId, title, body);
        articleList.add(article);
        return "%d번 글이 등록되었습니다".formatted(article.getId());
    }

    @GetMapping("/article/{id}")
    @ResponseBody
    public Article getArticle(@PathVariable int id) {
        Article article = articleList
                .stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .get();
        return article;
    }
}
