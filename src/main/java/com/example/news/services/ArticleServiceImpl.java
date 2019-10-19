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
import org.springframework.stereotype.Service;

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
        return this.modelMapper.map(this.articleRepository.getOne(id), ArticleViewModel.class);
    }

    @Override
    public Page<ArticleViewModel> getAll(Pageable pageable, String title, Date date){
        if((title == null || title.equals("")) && date == null){
            return PageMapper.mapPage(this.articleRepository.findAll(pageable),ArticleViewModel.class,modelMapper);
        } else if(date == null){
            return PageMapper.mapPage(this.articleRepository.getAllByTitle(pageable,title),ArticleViewModel.class,modelMapper);
        } else if(title == null || title.equals("")){
            return PageMapper.mapPage(this.articleRepository.getAllByDate(pageable,date),ArticleViewModel.class,modelMapper);
        } else {
            return PageMapper.mapPage(this.articleRepository.getAllByTitleAndDate(pageable, title,date),ArticleViewModel.class,modelMapper);
        }
    }

    @Override
    public boolean createArticle(ArticleBindingModel model){

        Article newArticle = this.modelMapper.map(model, Article.class);
        newArticle.setDate(new Date());
        try{
            this.articleRepository.saveAndFlush(newArticle);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteArticle(String id){
        try {
            this.articleRepository.deleteById(id);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean editArticle(String id, ArticleBindingModel model) {
        Article article = this.articleRepository.getOne(id);
        article.setShortDescription(model.getShortDescription());
        article.setText(model.getText());
        article.setTitle(model.getTitle());

        try {
            this.articleRepository.saveAndFlush(article);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
