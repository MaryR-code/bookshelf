package com.example.bookshelf.boundary;

import com.example.bookshelf.control.ReaderControl;
import com.example.bookshelf.model.ReaderEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@Named
@ViewScoped
public class ReaderView implements Serializable {
    private static final Logger LOG = LoggerFactory.getLogger(ReaderView.class);
    @Inject
    private ReaderControl control;
    private String loginName;
    private ReaderEntity reader;

    public void open() {
        if (loginName == null) {
            reader = new ReaderEntity();
        } else {
            reader = control.findOrCreateReader(loginName);
        }
    }

    public String save() {
        if (loginName == null) {
            control.findOrCreateReader(null);
        } else {
            control.update(reader);
        }
        return "/index?faces-redirect=true";
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public ReaderEntity getReader() {
        return reader;
    }
}
