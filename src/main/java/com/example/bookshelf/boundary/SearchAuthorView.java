package com.example.bookshelf.boundary;


import com.example.bookshelf.control.AuthorControl;
import com.example.bookshelf.model.AuthorEntity;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class SearchAuthorView {
    @Inject
    private AuthorControl authorControl;

    private List<AuthorEntity> authors;
    private String term;

    @PostConstruct      // предварительно вызывает конструктор, затем сам метод
    public void init() {
        search();
    }

    public void search() {
        authors = authorControl.search(term);
    }

    public List<AuthorEntity> getAuthors() {
        return authors;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
