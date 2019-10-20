package com.example.news.services;

import com.example.news.domain.entities.Article;
import com.example.news.domain.models.binding.ArticleBindingModel;
import com.example.news.domain.models.view.ArticleViewModel;
import com.example.news.repositories.ArticleRepository;
import com.example.news.util.PageMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@Service
public class ArticleServiceImpl implements ArticleService {
    private ArticleRepository articleRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository, ModelMapper modelMapper) {
        this.articleRepository = articleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ArticleViewModel getOne(String id){
        Article article = this.articleRepository.findById(id).orElse(null);

        if(article == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Article with that ID not found");
        }

        return this.modelMapper.map(article, ArticleViewModel.class);
    }

    @Override
    public Page<ArticleViewModel> getAll(Pageable pageable, String title, Date date){
        if((title == null || title.equals("")) && date == null){
            return PageMapper.mapPage(this.articleRepository.findAll(pageable),ArticleViewModel.class,modelMapper);
        } else if(date == null){
            return PageMapper.mapPage(this.articleRepository.getAllByTitleContains(pageable,title),ArticleViewModel.class,modelMapper);
        } else if(title == null || title.equals("")){
            return PageMapper.mapPage(this.articleRepository.getAllByDate(pageable,date),ArticleViewModel.class,modelMapper);
        } else {
            return PageMapper.mapPage(this.articleRepository.getAllByTitleContainsAndDate(pageable, title,date),ArticleViewModel.class,modelMapper);
        }
    }

    @Override
    public ArticleViewModel createArticle(ArticleBindingModel model){

        Article newArticle = this.modelMapper.map(model, Article.class);
        newArticle.setDate(new Date());
        Article created;

        try {
            created = this.articleRepository.saveAndFlush(newArticle);
        } catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Article was not created");
        }

        return this.modelMapper.map(created,ArticleViewModel.class);
    }

    @Override
    public String deleteArticle(String id){

        Article article = this.articleRepository.findById(id).orElse(null);

        if(article == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Article with that ID not found");
        }

        try{
            this.articleRepository.deleteById(id);
        } catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Article was not deleted successfully");
        }

        return "Article deleted";
    }

    @Override
    public ArticleViewModel editArticle(String id, ArticleBindingModel model) {
        Article article = this.articleRepository.findById(id).orElse(null);

        if(article == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Article with that ID not found");
        }

        article.setShortDescription(model.getShortDescription());
        article.setText(model.getText());
        article.setTitle(model.getTitle());

        Article edited;

        try{
            edited = this.articleRepository.saveAndFlush(article);
        } catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Article was not edited successfully");
        }


        return this.modelMapper.map(edited,ArticleViewModel.class);
    }
}
