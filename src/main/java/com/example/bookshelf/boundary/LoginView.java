package com.example.bookshelf.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class LoginView {
    private static final Logger LOG = LoggerFactory.getLogger(LoginView.class);
    private String username;
    private String password;
    @Inject
    private HttpServletRequest request;

    public void login() {
        LOG.trace("User {} is trying to log in.", username);
        try {
            request.login(username, password);
            LOG.trace("User {} logged in successfully", username);
        } catch (ServletException e) {
            LOG.warn("Unsuccessful login. Username: " + username, e);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
