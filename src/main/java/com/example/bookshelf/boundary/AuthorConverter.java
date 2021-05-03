package com.example.bookshelf.boundary;

import com.example.bookshelf.control.AuthorControl;
import com.example.bookshelf.model.AuthorEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class AuthorConverter implements Converter<AuthorEntity> {
    @Inject
    private AuthorControl authorControl;

    @Override
    public AuthorEntity getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        try {
            var id = Long.parseLong(value);
            return authorControl.find(id);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, AuthorEntity value) {
        if (value == null) {
            return null;
        }
        return value.getId().toString();
    }
}
