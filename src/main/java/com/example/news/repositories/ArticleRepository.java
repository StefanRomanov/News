package com.example.news.repositories;

import com.example.news.domain.entities.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ArticleRepository extends JpaRepository<Article, String> {
    Page<Article> getAllByTitleAndDate(Pageable pageable, String title, Date date);
    Page<Article> getAllByTitle(Pageable pageable, String title);
    Page<Article> getAllByDate(Pageable pageable, Date date);
}
