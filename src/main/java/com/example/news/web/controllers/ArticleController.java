package com.example.news.web.controllers;

import com.example.news.domain.models.binding.ArticleBindingModel;
import com.example.news.domain.models.view.ArticleViewModel;
import com.example.news.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;


@RestController
@RequestMapping("/api")
public class ArticleController {

    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping(value = "/articles", produces = "application/json")
    public @ResponseBody Page<ArticleViewModel> getArticles(Pageable pageable,
                                                            @RequestParam(name = "title", required = false) String title,
                                                            @RequestParam(name = "date", required = false) Date date){
        return this.articleService.getAll(pageable,title,date);
    };

    @GetMapping(value = "/articles/{id}", produces = "application/json")
    public @ResponseBody ArticleViewModel getOneArticle(@PathVariable String id){
        return this.articleService.getOne(id);
    };

    @PutMapping(value = "/articles/{id}", produces = "application/json")
    public @ResponseBody ArticleViewModel editArticle(@PathVariable String id, @Valid @RequestBody ArticleBindingModel model){
        return this.articleService.editArticle(id,model);
    };

    @DeleteMapping(value = "/articles/{id}", produces = "application/json")
    public @ResponseBody String deleteArticle(@PathVariable String id){
        return this.articleService.deleteArticle(id);
    };

    @PostMapping(value = "/articles", produces = "application/json")
    public @ResponseBody ArticleViewModel  createArticle(@Valid @RequestBody ArticleBindingModel model){
        return this.articleService.createArticle(model);
    };
}
