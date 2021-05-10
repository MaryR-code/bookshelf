package com.example.bookshelf.boundary;

import com.example.bookshelf.control.ReaderControl;
import com.example.bookshelf.model.ReaderEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class SearchReaderView {
    private static final Logger LOG = LoggerFactory.getLogger(SearchReaderView.class);
    @Inject
    private ReaderControl readerControl;

    private List<ReaderEntity> readers;
    private String term;

    @PostConstruct      // предварительно вызывает конструктор, затем сам метод
    public void init() {
        search();
    }

    public void search() {
        readers = readerControl.search(term);
        if (term != null && !term.isBlank()) {
            LOG.trace("Reader search '{}' completed - {} results.", term, readers.size());
        }
    }

    public List<ReaderEntity> getReaders() {
        return readers;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
