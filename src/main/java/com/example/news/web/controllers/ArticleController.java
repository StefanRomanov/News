package com.example.news.web.controllers;

import com.example.news.domain.models.ArticleViewModel;
import com.example.news.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api/articles")
public class ArticleController {

    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public List<ArticleViewModel> getArticles(){
        return null;
    };

    @RequestMapping(method = RequestMethod.GET, path = "/:id")
    public ArticleViewModel getOneArticle(@PathVariable String id){
        return null;
    };

    @RequestMapping(method = RequestMethod.PUT, path = "/:id")
    public ArticleViewModel editArticle(@PathVariable String id){
        return null;
    };

    @RequestMapping(method = RequestMethod.DELETE, path = "/:id")
    public ArticleViewModel deleteArticle(@PathVariable String id){
        return null;
    };

    @RequestMapping(method = RequestMethod.POST, path = "/")
    public ArticleViewModel createArticle(@PathVariable String id){
        return null;
    };
}
