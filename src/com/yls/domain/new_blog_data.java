package com.yls.domain;

public class new_blog_data {
    private String id;
    private String Context;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContext() {
        return Context;
    }

    public void setContext(String context) {
        Context = context;
    }

    public new_blog_data(String id, String context) {
        this.id = id;
        Context = context;
    }

    @Override
    public String toString() {
        return "new_blog_data{" +
                "id='" + id + '\'' +
                ", Context='" + Context + '\'' +
                '}';
    }
}
