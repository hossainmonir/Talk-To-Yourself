package com.example.talkyourself.MentalHealthBlog;



public class mHealthBlogModel {
    String title;
    String blog;
    String source;

    public mHealthBlogModel() {
    }

    public mHealthBlogModel(String title, String blog, String source) {
        this.title = title;
        this.blog = blog;
        this.blog = blog;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}