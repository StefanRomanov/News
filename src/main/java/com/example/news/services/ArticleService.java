package com.example.news.services;

import com.example.news.domain.models.binding.ArticleBindingModel;
import com.example.news.domain.models.view.ArticleViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface ArticleService {
    ArticleViewModel getOne(String id);
    boolean createArticle(ArticleBindingModel model);
    boolean deleteArticle(String id);
    boolean editArticle(String id, ArticleBindingModel model);
    Page<ArticleViewModel> getAll(Pageable pageable, String title, Date date);
}
