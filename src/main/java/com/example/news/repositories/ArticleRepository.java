package com.example.news.repositories;

import com.example.news.domain.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, String> {
    List<Article> getAll();
    List<Article> getAllByTitleAndDate(String title, Date date);
    List<Article> getAllByTitle(String title);
    List<Article> getAllByDate(Date date);
}
