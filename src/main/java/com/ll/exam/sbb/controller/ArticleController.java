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

    List<Article> articleList = new ArrayList<>();

    @GetMapping("/addArticle")
    @ResponseBody
    public String addArticle(String title, String body) {
        Article article = new Article(title, body);
        articleList.add(article);
        return "%d번 글이 등록되었습니다".formatted(article.getId());
    }

    @GetMapping("/article/{id}")
    @ResponseBody
    public Object getArticle(@PathVariable int id) {
        Article article = articleList
                .stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);

        if (article == null) {
            return "%d번 글은 존제하지 않습니다.".formatted(id);
        }

        return article;
    }

    @GetMapping("/modifyArticle/{id}")
    @ResponseBody
    public String modifyArticle(@PathVariable int id, String title, String body) {
        Article article = articleList
                .stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);

        if (article == null) {
            return "%d번 글은 존제하지 않습니다.".formatted(id);
        }
        article.modifyArticle(title, body);
        return "%d번 글이 수정되었습니다".formatted(article.getId());
    }

    @GetMapping("/deleteArticle/{id}")
    @ResponseBody
    public String deleteArticle(@PathVariable int id) {
        Article article = articleList
                .stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);

        if (article == null) {
            return "%d번 글은 존제하지 않습니다.".formatted(id);
        }

        articleList.remove(article);
        return "%d번 글이 삭제되었습니다".formatted(id);
    }
}
