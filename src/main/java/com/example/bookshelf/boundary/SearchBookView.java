package com.example.bookshelf.boundary;

import com.example.bookshelf.control.BookControl;
import com.example.bookshelf.model.BookEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Named
@RequestScoped
public class SearchBookView {
    private static final Logger LOG = LoggerFactory.getLogger(SearchBookView.class);
    @Inject     // прописано в конфигурации (standalone)
    private BookControl bookControl;

    private List<BookEntity> books;
    private String term;

    @PostConstruct      // предварительно вызывает конструктор, затем сам метод
    public void init() {
        search();
    }

    public void search() {
        books = bookControl.search(term);
        if (term != null && !term.isBlank()) {
            LOG.trace("Book search '{}' completed - {} results.", term, books.size());
        }
    }

    public List<BookEntity> getBooks() {
        return books;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}






