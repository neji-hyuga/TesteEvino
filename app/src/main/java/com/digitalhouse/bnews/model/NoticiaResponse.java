package com.digitalhouse.bnews.model;

import java.util.List;

public class NoticiaResponse {

    private List<Noticia> articles;

    public List<Noticia> getArticles() {
        return articles;
    }

    public void setArticles(List<Noticia> articles) {
        this.articles = articles;
    }
}
