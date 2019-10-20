package com.example.news.domain.models.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class ArticleBindingModel {
    private String title;
    private String shortDescription;
    private String text;

    public ArticleBindingModel() {
    }

    @NotEmpty
    @Length(max = 255, min = 10)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Length(max = 512)
    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @NotEmpty
    @Length(min = 128)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
